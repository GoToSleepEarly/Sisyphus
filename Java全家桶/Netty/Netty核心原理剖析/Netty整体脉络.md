 ![image ](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/CgqCHl-NAQaABGcDAAZa0pmBs40719.png) 

## Q：同步/异步 & 阻塞/非阻塞IO？

A： 我们所关心的的是对于**IO操作(如read)的整体状态**，NIO调用需要等待结果返回（而不是Future类的凭证），而当无数据可读时不会阻塞，所以是同步非阻塞。

同步和异步关注的是**消息通信机制**或者说是**调用方式** (synchronous communication/ asynchronous communication) ；阻塞和非阻塞关注的是**程序在等待调用结果（消息，返回值）时的状态.** 

- 阻塞，非阻塞：**进程/线程要访问的数据是否就绪，（当未就绪时）进程/线程是否需要等待；**
- 同步，异步：**访问数据的方式，同步需要主动读写数据，在读写数据的过程中还是会阻塞；异步只需要I/O操作完成的通知，并不主动读写数据，由操作系统内核完成数据的读写。**

TIPS:**注意，同步是由调用方进行操作，而异步通常是非调用方进行操作**

I/O 请求可以分为两个阶段，分别为调用阶段和执行阶段。

- 第一个阶段为**I/O 调用阶段**，即用户进程向内核发起系统调用。

- 第二个阶段为**I/O 执行阶段**。此时，内核等待 I/O 请求处理完成返回。该阶段分为两个过程：首先等待数据就绪，并写入内核缓冲区；随后将内核缓冲区数据拷贝至用户态缓冲区。

   ![Drawing 0.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/Ciqc1F-NAZ6Ae3bPAAHigveMsIQ514.png) 

1. #### 同步阻塞IO （BIO）

    ![1.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/CgqCHl-OnUKAeEELAAEnHU3FHGA343.png)

    如上图所表现的那样，应用进程向内核发起 I/O 请求，发起调用的线程一直等待内核返回结果。一次完整的 I/O 请求称为BIO（Blocking IO，阻塞 I/O），所以 BIO 在实现异步操作时，**只能使用多线程模型**，一个请求对应一个线程。但是，线程的资源是有限且宝贵的，创建过多的线程会增加线程切换的开销。  

2. ##### 同步非阻塞 I/O（NIO）

    ![2.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/Ciqc1F-OnTeAFLNhAAFptS-OxRY266.png)

     应用进程向内核发起 I/O 请求后不再会同步等待结果，而是会立即返回，通过轮询的方式获取请求结果。NIO 相比 BIO 虽然大幅提升了性能，但是轮询过程中大量的系统调用导致上下文切换开销很大。所以，**单独使用非阻塞 I/O 时效率并不高**，而且随着并发量的提升，非阻塞 I/O 会存在严重的性能浪费。 

3. #####  I/O 多路复用

    ![3.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/CgqCHl-OnV2ADXBhAAFUZ6oiz6U529.png)

     多路复用实现了**一个线程处理多个 I/O 句柄的操作**。多路指的是多个**数据通道**，复用指的是使用一个或多个固定线程来处理每一个 Socket。select、poll、epoll 都是 I/O 多路复用的具体实现，线程一次 select 调用可以获取内核态中多个数据通道的数据状态。多路复用解决了同步阻塞 I/O 和同步非阻塞 I/O 的问题，是一种非常高效的 I/O 模型。 

4. ##### 信号驱动 I/O

    ![4.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/CgqCHl-OnWqAddLWAAFUtZ6YHDA683.png)

     信号驱动 I/O 并不常用，它是一种半异步的 I/O 模型。在使用信号驱动 I/O 时，当数据准备就绪后，内核通过发送一个 SIGIO 信号通知应用进程，应用进程就可以开始读取数据了。 

5. ##### 异步 I/O

    ![5.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/Ciqc1F-OnXSAHOGVAACvxV3_3Mk188.png)

     异步 I/O 最重要的一点是从内核缓冲区拷贝数据到用户态缓冲区的过程也是由系统异步完成，应用进程只需要在指定的数组中引用数据即可。**异步 I/O 与信号驱动 I/O 这种半异步模式的主要区别**：信号驱动 I/O 由内核通知何时可以开始一个 I/O 操作，而异步 I/O 由内核通知 I/O 操作何时已经完成。 

## Q：Netty逻辑架构

A： ![Drawing 1.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/Ciqc1F-NO9KAUOtaAAE1S5uRlDE275.png)

- ####  网络通信层

   网络通信层的**核心组件**包含**BootStrap、ServerBootStrap、Channel**三个组件。 

  | 事件                | 说明                                          |
  | ------------------- | --------------------------------------------- |
  | channelRegistered   | Channel 创建后被注册到 EventLoop 上           |
  | channelUnregistered | Channel 创建后未注册或者从 EventLoop 取消注册 |
  | channelActive       | Channel 处于就绪状态，可以被读写              |
  | channelInactive     | Channel 处于非就绪状态                        |
  | channelRead         | Channel 可以从远端读取到数据                  |
  | channelReadComplete | Channel 读取数据完成                          |

- #### 事件调度层

   事件调度层的**核心组件**包括 **EventLoopGroup、EventLoop**。 

   ![Drawing 4.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/CgqCHl-NPG6APzDfAAbX5ACAFh8001.png)

  1. 一个 EventLoopGroup 往往包含一个或者多个 EventLoop。EventLoop 用于处理 Channel 生命周期内的所有 I/O 事件，如 accept、connect、read、write 等 I/O 事件。
  2. EventLoop 同一时间会与一个线程绑定，每个 EventLoop 负责处理多个 Channel。
  3. 每新建一个 Channel，EventLoopGroup 会选择一个 EventLoop 与其绑定。该 Channel 在生命周期内都可以对 EventLoop 进行多次绑定和解绑。

- #### 服务编排层

   服务编排层的**核心组件**包括 **ChannelPipeline**、**ChannelHandler、ChannelHandlerContext**。 

  1.   ChannelPipeline 是 Netty 的核心编排组件，**负责组装各种 ChannelHandler** ，是一个双向链表，ChannelPipeline 是线程安全的，因为每一个新的 Channel 都会对应绑定一个新的 ChannelPipeline。一个 ChannelPipeline 关联一个 EventLoop，一个 EventLoop 仅会绑定一个线程。 

     ![Drawing 7.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/CgqCHl-NPKaASxvgAAFHMPYQFhM940.png)

  2. ChannelHandler负责开放给用户自定义编程，是业务处理的核心逻辑

  3.  ChannelHandlerContext 用于保存 ChannelHandler 上下文 ，封装了 bind、read、flush、write、close  各种底层逻辑，同时ChannelHandler可能共享，但是Context和Channel不共享，可以保存所需线程安全的上下文（推荐用Channel）。

     ![Drawing 8.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/CgqCHl-NPK-ADq0pAABb1k5Zwu8681.png)

   

    

  ### 整体逻辑：

   ![Drawing 9.png](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Netty%20%E6%A0%B8%E5%BF%83%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90%E4%B8%8E%20RPC%20%E5%AE%9E%E8%B7%B5-%E5%AE%8C/assets/Ciqc1F-NPLeAPdjRAADyud16HmQ759.png) 

  - 服务端启动初始化时有 Boss EventLoopGroup 和 Worker EventLoopGroup 两个组件，其中 Boss 负责监听网络连接事件。当有新的网络连接事件到达时，则将 Channel 注册到 Worker EventLoopGroup。
  - Worker EventLoopGroup 会被分配一个 EventLoop 负责处理该 Channel 的读写事件。每个 EventLoop 都是单线程的，通过 Selector 进行事件循环。
  - 当客户端发起 I/O 读写事件时，服务端 EventLoop 会进行数据的读取，然后通过 Pipeline 触发各种监听器进行数据的加工处理。
  - 客户端数据会被传递到 ChannelPipeline 的第一个 ChannelInboundHandler 中，数据处理完成后，将加工完成的数据传递给下一个 ChannelInboundHandler。
  - 当数据写回客户端时，会将处理结果在 ChannelPipeline 的 ChannelOutboundHandler 中传播，最后到达客户端。