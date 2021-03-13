# [极客时间：设计模式之美](https://time.geekbang.org/column/article/176075)



## **先设计，再编写**

先设计好整体架构，写的时候才能逻辑更清晰，不要图快。

###  [理论汇总](https://time.geekbang.org/column/article/193093)

![img](https://static001.geekbang.org/resource/image/f3/d3/f3262ef8152517d3b11bfc3f2d2b12d3.png) 

 ![img](https://static001.geekbang.org/resource/image/f4/e7/f4ce06502a9782d200e8e10a90bf2ce7.jpg) 

 ![img](https://static001.geekbang.org/resource/image/fb/9f/fbf1ae0ce08d4ea890b80944c2b8309f.jpg) 

 ![img](https://static001.geekbang.org/resource/image/fc/8a/fc56f7c2b348d324c93a09dd0dee538a.jpg) 

------



## 设计阶段：

目标是：高内聚，低耦合的**设计图**

### 一、划分职责进而识别出有哪些类

### 二、定义类及其属性和方法

### 三、定义类与类之间的交互关系

### 四、将类组装起来并提供执行入口

练习：[设计模式之美——实战二](https://time.geekbang.org/column/article/171767)

------



## 编写阶段：

### 一、时刻具备扩展意识、抽象意识、封装意识

从类的角度去思考能否抽离。

这段代码是：

1、面向过程的吗（平铺直叙）？

2、是否属于这个类（低耦合）？

3、能否抽象成一个类（高内聚）？

4、能否分离出不同接口（面对接口）？

### 二、设计原则

1.  单一职责原则 （SRP  Single Responsibility Principle ）： 一个类只负责完成一个职责或者功能。 
2.  对扩展开放、修改关闭 （OCP  Open Closed Principle  ）：不是不能修改，而是最小代价修改
3.  里式替换原则 （ LSP   Liskov Substitution Principle ）： design by contract ，子类不能违背协议
4.  **接口隔离原则** （ISP  Interface Segregation Principle ）：核心为依赖一组接口（Interface，API等）时，不会依赖到多余的Part。参照[理论四](https://time.geekbang.org/column/article/177442)，当发现子类复写了不需要的方法，API提供了多余的方法时，需要把共有的部分重新抽象。
5.  依赖反转原则 （DIP  Dependency Inversion Principle ）：高层次依赖低层次的抽象层，而非实现层。构造函数如果依赖其他成员，不应该直接new，而是通过接口传入。
6.  KISS & YAGNI （ Keep It Simple and Stupid & You Ain’t Gonna Need It ）：可读性及不要过度设计
7.  DRY 原则（Don’t Repeat Yourself） ： Rule of Three ，当第二次遇到时考虑复用性。
8.  **迪米特法则** （LOD  Law of Demeter  ）： The Least Knowledge Principle，最小知识原则，奥卡姆剃刀。参考[理论8](https://time.geekbang.org/column/article/179615)的2个实战。

### 三、实战

* 参考[实战一中三种方案的对比](https://time.geekbang.org/column/article/182001)， **如果一个功能的修改或添加，经常要跨团队、跨项目、跨系统才能完成，那说明模块划分的不够合理，职责不够清晰，耦合过于严重。** 这适用于OCP原则的最小代价修改，因为不管是上游，下游还是抽象层，都需要有人去修改，这个时候判断**最小代价**即可——哪个更容易发生改变，改变的工作量更大。
* **一般来讲，我们不希望下层系统（也就是被调用的系统）包含太多上层系统（也就是调用系统）的业务信息，但是，可以接受上层系统包含下层系统的业务信息。** 理由同上，因为上游的业务明显更容易改变。
* 系统之间调用**，一种是同步接口调用，另一种是利用消息中间件异步调用****。第一种方式简单直接，第二种方式的解耦效果更好。**上下层系统之间的调用倾向于通过同步接口，同层之间的调用倾向于异步消息调用** 。其实这么说也不太对，一般而言，同步依赖结果的时候用同步接口，异步不依赖的时候用异步，这个角度看，依赖结果的上下游，不依赖的是同层，相互依赖是错误的设计。
*  **接口设计、数据库设计和业务模型设计**（也就是业务逻辑）。由于数据库和接口的改动，都会带来大量的适配，所以要**优先设计数据库和接口**。
* 

------

## 重构阶段：

1、重构：避免**破窗效应**，不要过度设计，也不要让代码烂到无以复加，要保证可持续性。

2、解耦：当UML图非常混乱时，就需要重构解耦了。无外乎三种策略：封装与抽象+**中间层**+模块化，1和3其实很相似，维度区别的问题，但是中间层的解耦是需要学习的。 

 ![img](https://static001.geekbang.org/resource/image/cb/52/cbcefa78026fd1d0cb9837dde9adae52.jpg) 

👆和这个图类似，很多时候解耦的核心方法就是封装一层👆

当Extract Method的时候发现功能是重复的，可以抽象一层封装层！

3、命名与注解：关于命名👉[如何命名](https://zhuanlan.zhihu.com/p/96100037) 及[如何查找命名]( https://unbug.github.io/codelf/ ) ；关于注释👉如果注释写完后紧跟代码，那么完全可以通过函数名来体现，具体见仁见智。

```java
// 当名字为admin且密码为password时，XXXX
if("admin".equals(input.getName()) && "password".equals(input.getPwd())){
    //业务代码
}}
👇
// 不需要注释，
if(isValidAdminUser()){
    //业务代码
}} 
```

4、**Checklist：**

 ![img](https://static001.geekbang.org/resource/image/04/c9/041e22cac6ce2ba3481e246c119adfc9.jpg) 

 ![img](https://static001.geekbang.org/resource/image/98/98/9894233257994a69102afa960692ce98.jpg) 

**[参考实战一（下）](https://time.geekbang.org/column/article/191621)**：对面向接口编程更进一步的理解。

接口：behaves like，命名为功能，注意不要范围过大。

实现类：how to work，命名为特性，注意不要暴露细节。

**接口之间相互隔离，而同一个接口的实现类是可以相互替代**，如果不行，则抽出一个中间层接口。

**[参考实战二（上）](https://time.geekbang.org/column/article/191642)**：异常情况的处理

1. 返回错误码 ：Java不适用
2. 返回 NULL 值：如果语义是返回NULL即可，如DAO层查找不到单个对象
3.  返回空对象 ：集合查询等推荐使用空对象，如DAO层查找不到List
4.  抛出异常对象 ： 可以直接吞掉（转换成默认值）、直接往上抛出（调用方感知）、包裹成新的异常抛出（调用方不感知）。**取决于调用方是否感知底层实现。** 

------



# 加油，多练。


