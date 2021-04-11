# LSM-Tree (Log Structured Merge Tree)

##  基本思想

- 更新数据并不一定需要原地替换（ update-in-place ），保证读到最新数据即可。
- 磁盘的顺序写速度远大于随机写
- 批量写入数据速度大于单个写
- 通过排序加速查找过程

所以，LSM-Tree通常适合于写多读少，key值有序范围查找的场景。 

## 存储模型

- **WAL**

WAL（write ahead log）也称预写log，包括mysql的Binlog等,在设计数据库的时候经常被使用，当插入一条数据时，数据先顺序写入 WAL 文件中，之后插入到内存中的 MemTable 中。这样就保证了数据的持久化，不会丢失数据，并且都是顺序写，速度很快。当程序挂掉重启时，可以从 WAL 文件中重新恢复内存中的 MemTable。

- **MemTable**

MemTable 对应的就是 WAL 文件，是该文件内容在内存中的存储结构，通常用 [SkipList](https://mp.weixin.qq.com/s?__biz=MzAxMzE4MDI0NQ==&mid=2650336541&idx=1&sn=641646d7ebb267f59fd2d39c9c143411&chksm=83aac127b4dd4831a6ed788675455e88975f5ac64813108e033d47c6fbe03f2090d171f21b00&scene=21#wechat_redirect) 来实现。MemTable 提供了 k-v 数据的写入、删除以及读取的操作接口。其内部将 k-v 对按照 **key 值有序**存储，这样方便之后快速序列化到 SSTable 文件中，仍然保持数据的有序性。

- **Immutable Memtable**

顾名思义，Immutable Memtable 就是在**内存中只读**的 MemTable，由于内存是有限的，通常我们会设置一个阀值，当 MemTable 占用的内存达到阀值后就自动转换为 Immutable Memtable，Immutable Memtable 和 MemTable 的区别就是它是只读的，系统此时会生成新的 MemTable 供写操作继续写入。之所以要使用 Immutable Memtable，就是为了避免将 MemTable 中的内容序列化到磁盘中时会阻塞写操作。

- **SSTable**

SSTable(Sorted String Table) 就是 MemTable 中的数据在磁盘上的有序存储，其内部数据是根据 key 从小到大排列的。通常为了加快查找的速度，需要在 SSTable 中加入数据索引，可以快读定位到指定的 k-v 数据。

 ![clipboard.png](https://segmentfault.com/img/bVbyoow) 

 ![img](https://img-blog.csdnimg.cn/20190521200208933.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTA0NTQwMzA=,size_16,color_FFFFFF,t_70) 

## LSM-Tree的写入流程

1. 当收到一个写请求时，会先把该条数据记录在WAL Log里面，用作**故障恢复**。
2. 当写完WAL Log后，会把该条数据写入内存的SSTable里面（删除是墓碑标记，更新是新记录一条的数据），也称Memtable。注意为了维持**有序性**在内存里面可以采用**红黑树或者跳跃表**相关的数据结构。
3. 当Memtable超过一定的大小后，会在内存里面冻结，变成不可变的Memtable，同时为了不阻塞写操作需要新生成一个Memtable继续提供服务。
4. 把内存里面不可变的Memtable给dump到到硬盘上的SSTable层中，此步骤也称为Minor Compaction，这里需要注意在L0层的SSTable是没有进行合并的，所以这里的**key range在多个SSTable中可能会出现重叠**，在层数大于0层之后的SSTable，不存在重叠key。
5. 当每层的磁盘上的SSTable的体积超过一定的大小或者个数，也会周期的进行合并。此步骤也称为Major Compaction，这个阶段会真正 的清除掉被标记删除掉的数据以及多版本数据的合并，避免浪费空间，注意由于SSTable都是有序的，我们可以直接采用**merge sort**进行高效合并。

## LSM-Tree的读取流程

LSM Tree 的读取效率并不高，当需要读取指定 key 的数据时

1. 先在内存中的 MemTable 和 Immutable MemTable 中查找
2. 如果没有找到，则继续从 Level 0 层开始，找不到就从更高层的 SSTable 文件中查找，如果查找失败，说明整个 LSM Tree 中都不存在这个 key 的数据。如果中间在任何一个地方找到这个 key 的数据，那么按照这个路径找到的数据都是最新的。

在每一层的 SSTable 文件的 key 值范围是不重复的，所以只需要查找其中一个 SSTable 文件即可确定指定 key 的数据是否存在于这一层中。Level 0 层比较特殊，因为数据是 Immutable MemTable 直接写入此层的，所以 Level 0 层的 SSTable 文件的 key 值范围可能存在重复，查找数据时有可能需要查找多个文件。

## 读取优化

 因为这样的读取效率非常差，通常会进行一些优化，例如 LevelDB 中的 Mainfest 文件，这个文件记录了 SSTable 文件的一些**关键信息**，例如 Level 层数，文件名，最小 key 值，最大 key 值等，这个文件通常不会太大，可以放入内存中，可以帮助快速定位到要查询的 SSTable 文件，避免频繁读取。 

1. **压缩**

   SSTable 是可以启用压缩功能的，并且这种压缩不是将整个 SSTable 一起压缩，而是根据 locality 将数据分组，每个组分别压缩，这样的好处当读取数据的时候，我们不需要解压缩整个文件而是解压缩部分 Group 就可以读取。

2. **缓存**

   因为SSTable在写入磁盘后，除了Compaction之外，是不会变化的，所以我可以将Scan的Block进行缓存，从而提高检索的效率

3. **索引，Bloom filters**

   正常情况下，一个读操作是需要读取所有的 SSTable 将结果合并后返回的，但是对于某些 key 而言，有些 SSTable 是根本不包含对应数据的，因此，我们可以对每一个 SSTable 添加 Bloom Filter，因为布隆过滤器在判断一个SSTable不存在某个key的时候，那么就一定不会存在，利用这个特性可以减少不必要的磁盘扫描。

4. **合并**

   这个在前面的写入流程中已经介绍过，通过定期合并瘦身， 可以有效的清除无效数据，缩短读取路径，提高磁盘利用空间。但Compaction操作是非常消耗CPU和磁盘IO的，尤其是在业务高峰期，如果发生了Major Compaction，则会降低整个系统的吞吐量，这也是一些NoSQL数据库，比如Hbase里面常常会禁用Major Compaction，并在凌晨业务低峰期进行合并的原因。


**为什么LSM不直接顺序写入磁盘，而是需要在内存中缓冲一下**？

 这个问题其实很容易解答，单条写的性能肯定没有批量写来的块，这个原理其实在Kafka里面也是一样的，虽然kafka给我们的感觉是写入后就落地，但其实并不是，本身是 可以根据条数或者时间比如200ms刷入磁盘一次，这样能大大提升写入效率。此外在LSM中，在磁盘缓冲的另一个好处是，针对新增的数据，可以直接查询返回，能够避免一定的IO操作。

## B+Tree VS LSM-Tree

传统关系型数据采用的底层数据结构是B+树，那么同样是面向磁盘存储的数据结构LSM-Tree相比B+树有什么异同之处呢？

1. **本质思路上的区别**：在数据的更新和删除方面，B+Tree可以做到**原地更新和删除**，但由于LSM-Tree只能追加写，并且在L0层key的rang会重叠，所以对事务支持较弱，只能在Segment Compaction的时候进行真正地更新和删除。

2. **写入数据的区别：**LSM-Tree的设计思路是，将数据拆分为几百M大小的Segments，并是**顺序写入**。

   B+Tree则是将数据拆分为**固定大小的Block或Page**, 一般是4KB大小，和磁盘一个扇区的大小对应，Page是读写的最小单位，在读取和写入时是随机的。

3. **对事务处理的区别：**因为B+Tree每一个数据都在一个页上，因为加锁会方便，同时因为技术发展，B+Tree对的处理更工程化。而LSM-Tree一般只用做NoSql的存储，同一个数据在Level0多处出现，故对事务的支持较弱。

4. **读写性能的区别：**因此LSM-Tree的优点是支持高吞吐的写（可认为是O（1）），这个特点在分布式系统上更为看重，当然针对读取普通的LSM-Tree结构，读取是O（N）的复杂度，在使用索引或者缓存优化后的也可以达到O（logN）的复杂度。

   而B+tree的优点是支持高效的读（稳定的OlogN），但是在大规模的写请求下（复杂度O(LogN)），效率会变得比较低，因为随着insert的操作，为了维护B+树结构，节点会不断的分裂和合并。操作磁盘的随机读写概率会变大，故导致性能降低。

5. **LSM-Tree额外的缺点：**LSM-Tree分层存储能够做到写的高吞吐，带来的副作用是整个系统必须频繁的进行compaction，写入量越大，Compaction的过程越频繁。而compaction是一个compare & merge的过程，非常消耗CPU和存储IO，在高吞吐的写入情形下，大量的compaction操作占用大量系统资源，必然带来整个系统性能断崖式下跌，对应用系统产生巨大影响，当然我们可以禁用自动Major Compaction，在每天系统低峰期定期触发合并，来避免这个问题。阿里为了优化这个问题，在X-DB引入了异构硬件设备FPGA来代替CPU完成compaction操作，使系统整体性能维持在高水位并避免抖动，是存储引擎得以服务业务苛刻要求的关键。
