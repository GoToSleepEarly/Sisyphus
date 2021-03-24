

# 前缀和

前缀和题型关键字：**子序列**及**区间和**

------

## 滑动窗口和前缀和

当**滑动窗口**收缩条件不确定时（即右边界不一定右扩时），可以转换为**前缀和**，再不行就尝试**动态规划**

------



## 前缀和模板

### 解题步骤：

1. 根据题目，找到前缀和所需要的**满足的条件**。
2. 利用Hash的方法，加速查找

```java
public int solve(int[] nums, int k) {
   // 坐标i的sum不包括i
   int[] preSum = new int[nums.length + 1];
   for (int i = 1; i <= nums.length; i++) {
      preSum[i] = preSum[i - 1] + nums[i - 1];
   }
   int res = 0;
   Map<Integer, Integer> helperMap = new HashMap<>();
   for (int curPreSum : preSum) {
       if (helperMap.containsKey(value2Search)) {
          res += helperMap.get(value2Search);
        }
       helperMap.merge(curPreSum, 1, (o, n) -> o + 1);
   }
   return res;
}
```
### Tips：

1. 前缀和满足的条件不一定是**子序列的和**，也可能是**某种特征**

## 前缀和练习题

### 常规前缀和

**1、No560和为K的子数组：**

得出前缀和公式即可

**2、No974和可被K整除的子数组：**

得出前缀和公式即可，注意负数的取余

**3、No523连续的子数组和：**

思想比较简单，但是细节很考究

------

### 前缀和变形

**1、No1248统计优美子数组：**

统计的不是和，而是奇数的个数

**2、No437路径总和3：**

不是数组的前缀和，而是树的前缀和

**3、No1314矩阵区域和：**

不是一维的前缀和，而是二维的前缀和

