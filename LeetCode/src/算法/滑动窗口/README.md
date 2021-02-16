

# 滑动窗口

滑动窗口题型关键字：子序列

## 滑动窗口模板

### 解题步骤：

1. 给定窗口，总结出判断窗口是否合法的条件
2. 其余随意

```java
public int solve(String input) {
    int left = 0;
    int right = 0;
    while (right < input.length()) {
        // 把right加入窗口
        addRight();
        right++;
        // 判断需要收缩
        while (!checkWindow()) {
            // 将left移出窗口
            removeLeft();
        }
        // 更新结果
        updateResult();
    }
    return max;
}
```
### Tips：

1. checkWindow()是核心难点，优化经常基于此，需要优先考虑。
2. left和right移动的地方、updateResult()、是否需要预填充窗口等，都以代码方便为主，不影响解题过程。

## 滑动窗口的优化

1. 频数题：用Map或者count[]来优化，见No76，No424，No567
2. 窗口变形题：以"平移"代替”收缩“来优化，此时while替换为if，见No424；当收缩的幅度由题目定义给出时，需要枚举出所有窗口变化的情况，见No978
3. 思路转换题：将非连续的取值转换成子数组问题，见No1423；滑动窗口一般求最多及最少的问题，那么"恰好K个"则转化成"最多K个"-"最多K-1个"的子问题，见No992

