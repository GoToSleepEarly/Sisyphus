##  [MySQL 加锁处理分析 - 云+社区 - 腾讯云 (tencent.com)](https://cloud.tencent.com/developer/article/1033697)

- 针对一条当前读的SQL语句，InnoDB与MySQL Server的交互，是一条一条进行的，因此，加锁也是一条一条进行的。先对一条满足条件的记录加锁，返回给MySQL Server，做一些DML操作；然后在读取下一条加锁，直至读取完毕。 
-  2PL就是将加锁/解锁分为两个完全不相交的阶段。加锁阶段：只加锁，不放锁。解锁阶段：只放锁，不加锁。 
-  在实际的实现中，MySQL有一些改进，在MySQL Server过滤条件，发现不满足后，会调用unlock_row方法，把不满足条件的记录放锁 (违背了2PL的约束)。这样做，保证了最后只会持有满足条件记录上的锁，但是每条记录的加锁操作还是不能省略的。 

## 解决死锁之路系列

###   1、[解决死锁之路 - 学习事务与隔离级别 - aneasystone's blog](https://www.aneasystone.com/archives/2017/10/solving-dead-locks-one.html)

- 脏读：读取了未提交的数据；不可重复读：针对update，对同一结果两次查询不一致；幻读：很对insert或delete，对同一结果集两次查询不一致；丢失更新：先查询，利用查询值（快照读）进行结果更新，覆盖其他事务的提交，需要业务代码保证。
-  ![isolation-levels.png](https://www.aneasystone.com/usr/uploads/2017/10/2204872863.png) 
-  ![mysql-isolation.png](https://www.aneasystone.com/usr/uploads/2017/10/1389685905.png) 
- 解决读读问题需要加锁，读未提交也需要加锁，防止rollback覆盖更新；串行化需要加表锁；MVCC解决读写并发效率问题。

### 2、 [解决死锁之路 - 了解常见的锁类型 - aneasystone's blog](https://www.aneasystone.com/archives/2017/11/solving-dead-locks-two.html)

-  ![innodb-locks-multi-lines.png](https://www.aneasystone.com/usr/uploads/2017/10/201797556.png) 

-  ![table-locks-compatible-matrix.png](https://www.aneasystone.com/usr/uploads/2017/10/1431433403.png) 

  意向锁是表锁，互相不冲突；S锁只和S/IS兼容；X和所有的都兼容；AI锁只和意向锁兼容

- **第一行表示已有的锁，第一列表示要加的锁**。    ![row-locks-compatible-matrix.png](https://www.aneasystone.com/usr/uploads/2017/11/3404508090.png) 

   **插入意向锁只会和间隙锁或 Next-key 锁冲突**，不影响其他事务加任何其他锁。正如上面所说，间隙锁唯一的作用就是防止其他事务插入记录造成幻读，那么间隙锁是如何防止幻读的呢？**正是由于在执行 INSERT 语句时需要加插入意向锁，而插入意向锁和间隙锁冲突，从而阻止了插入操作的执行。** （插入意向锁只是为了探测是否能插入）

  ※ **间隙锁不和其他锁（除插入意向锁）冲突** ※

  **记录锁和记录锁冲突，Next-key锁和Next-key锁冲突，记录锁和Next-key锁冲突。**

- 悲观锁（for update 或者内存lock）；乐观锁（Compare And Set； **悲观锁需要使用锁机制来实现，而乐观锁是通过程序的手段来实现**

### 3、 [解决死锁之路 - 常见 SQL 语句的加锁分析 - aneasystone's blog](https://www.aneasystone.com/archives/2017/12/solving-dead-locks-three.html)

- **需要根据版本实验！**核心是：把扫描到的数据加锁，返回给server层，这之间多余的锁是否会加，是否会释放，取决于版本，有些奇怪的BUG已修复。
- 对于主键范围查询，不会锁下一条的Gap或Next-Key；
- **二级索引会用到索引下推（ICP）**，在扫描数据时，如果不满足，加上锁后直接下一条，这就导致可能会多锁右边界的Next-key，此时主键该对象不加锁。（从逻辑上讲，唯一索引和非唯一索引不同，唯一索引可以提前结束，但是似乎并不会）
- delete记录存在时只加记录锁（唯一的情况，非唯一也加gap和next-key）。由于MySQL会标记数据为删除（等待purge），当遇到已标记为删除的数据时，会重新添加锁。
- ![1635867394526](C:\Users\10622\AppData\Roaming\Typora\typora-user-images\1635867394526.png)

### 4、 [解决死锁之路（终结篇） - 再见死锁 - aneasystone's blog](https://www.aneasystone.com/archives/2018/04/solving-dead-locks-four.html)

- 开启锁检测以及查看死锁日志
-  如果在 supremum record 上加锁，`locks gap before rec` 会省略掉，间隙锁会显示成 `lock_mode X`，插入意向锁会显示成 `lock_mode X insert intention`。（MySQL为了复用结构）
- 根据下面的MySQL死锁案例熟悉常见定位流程，比如索引扫描不会在主键上加间隙锁，比如S锁和插入意向锁的冲突等

####      解决死锁的思路

1.  索引加锁顺序的不一致很可能会导致死锁，所以如果可以，尽量以相同的顺序来访问索引记录和表。 
2.  Gap 锁往往是程序中导致死锁的真凶，可以改成RC
3. 为表添加合理的索引，如果不走索引将会为表的每一行记录加锁，死锁的概率就会大大增大；
4.  在事务中一次锁定所需要的所有资源，减少死锁概率，如表锁，分布式锁
5. 避免大事务，尽量将大事务拆成多个小事务来处理；因为大事务占用资源多，耗时长，与其他事务冲突的概率也会变高；
6.  避免在同一时间点运行多个对同一表进行读写的脚本，特别注意加锁且操作数据量比较大的语句； 
7.  设置锁等待超时参数：`innodb_lock_wait_timeout` 

### 5、 [读 MySQL 源码再看 INSERT 加锁流程 - aneasystone's blog](https://www.aneasystone.com/archives/2018/06/insert-locks-via-mysql-source-code.html)

-  `insert` 加的是隐式锁。什么是隐式锁？隐式锁的意思就是没有锁！ 
- 执行 `insert` 语句，对要操作的页加 RW-X-LATCH，然后判断是否有和插入意向锁冲突的锁，如果有，加插入意向锁，进入锁等待；如果没有，直接写数据，不加任何锁，结束后释放 RW-X-LATCH；
- 执行 `select ... lock in share mode` 语句，对要操作的页加 RW-S-LATCH，如果页面上存在 RW-X-LATCH 会被阻塞，没有的话则判断记录上是否存在活跃的事务，如果存在，则为 `insert` 事务创建一个排他记录锁，并将自己加入到锁等待队列，最后也会释放 RW-S-LATCH；

##  [超全面 MySQL 语句加锁分析系列](https://learnku.com/articles/40624)

- 索引下推会导致添加额外的锁
- update不走索引下推，扫描二级索引时，会额外添加下一个数据的Next-key Lock
- 基本理论不会出错，具体还是需要根据版本进行实验
- next-key有时候先加gap在加record，这导致奇怪的死锁

## [GitHub - aneasystone/mysql-deadlocks: 收集一些常见的 MySQL 死锁案例](https://github.com/aneasystone/mysql-deadlocks)

- ACTIVE 0 sec 表示事务活动时间，inserting 为事务当前正在运行的状态，可能的事务状态有：fetching rows，updating，deleting，inserting 等。 

- 2 row locks 表示行锁个数，LOCK_RECORD类型的锁

- undo log entries为已更新的 聚集索引记录数

- index 'idx' of table 能看出索引，lock_mode X能看到锁类型，waiting表示该线程正在等待

- 记录锁（LOCK_REC_NOT_GAP）: lock_mode X locks rec but not gap

  间隙锁（LOCK_GAP）: lock_mode X locks gap before rec

  Next-key 锁（LOCK_ORNIDARY）: lock_mode X

  插入意向锁（LOCK_INSERT_INTENTION）: lock_mode X locks gap before rec insert intention

- 先看已经执行多少条命令，看持有哪些锁，分析锁（比如走索引不会给主键加gap锁），然后分析冲突情况