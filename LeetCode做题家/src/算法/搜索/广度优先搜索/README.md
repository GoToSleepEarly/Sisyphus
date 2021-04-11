# 广度优先遍历

广度优先遍历题型关键字：**无权图的最短路径 **或 **按层的循环遍历**。

------

思考：

DFS和BFS都能求最短路径，用BFS的好处是什么？

答案：

BFS**空间复杂度虽然高**，但是每一次迭代都是最终结果，故找到答案时可以**直接返回**。

而DFS需要遍历**所有结果**。

------



## BFS模板

### 解题步骤：

关键在于找寻**状态转移**以及相应的**路径处理**：

queue代表着每一轮**已经访问**的节点，所以在下一轮入队列前，需要将其标记为已访问。

```java
// 计算从起点 start 到终点 target 的最近距离
int BFS(Node start, Node target) {
    Queue<Node> q; // 核心数据结构
    Set<Node> visited; // 避免走回头路
    
    q.offer(start); // 将起点加入队列
    visited.add(start);
    int step = 0; // 记录扩散的步数

    while (q not empty) {
        int sz = q.size();
        /* 将当前队列中的所有节点向四周扩散 */
        for (int i = 0; i < sz; i++) {
            Node cur = q.poll();
            /* 划重点：这里判断是否到达终点 */
            if (cur is target)
                return step;
            /* 将 cur 的相邻节点加入队列 */
            for (Node x : cur.adj())
                if (x not in visited) {
                    q.offer(x);
                    visited.add(x);
                }
        }
        /* 划重点：更新步数在这里 */
        step++;
    }
}
```

### BFS的优化

1. 如果知道终点  ——> 双向BFS
2. 分支状态很多 ——> 多维变量状态压缩

------



## BFS练习题

按照难度依次排列如下：

**1、No695岛屿的最大面积：**

基础DFS

**2、No547省份数量：**

基础DFS

**3、No113路径总和2**

基础DFS

**4、No417太平洋大西洋水流问题**

多源DFS，需要转换思路

**5、No337打家劫舍3**

记忆化递归

**6、No979在二叉树中分配硬币**

**难题**，思路转换，在递归过程中求解。

**7、No332重新安排行程**

**难题**，回溯的细节很难（如何遍历List的同时插入修改呢？）



