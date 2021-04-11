package 算法.双指针.二分查找;

public class No1095山脉数组中查找目标值 {
    interface MountainArray {
        int get(int index);

        int length();
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int mountainTop = getMountainTop(mountainArr);
        int left = searchLeft(mountainArr, 0, mountainTop, target);
        if (left == -1) {
            return searchRight(mountainArr, mountainTop, mountainArr.length() - 1, target);
        }
        return left;
    }

    private int searchRight(MountainArray mountainArr, int l, int r, int target) {
        // 在后有序且降序数组中找 target 所在的索引
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 与 findFromSortedArr 方法不同的地方仅仅在于由原来的小于号改成大于好
            if (mountainArr.get(mid) > target) {
                l = mid + 1;
            } else {
                r = mid;
            }

        }
        // 因为不确定区间收缩成 1个数以后，这个数是不是要找的数，因此单独做一次判断
        if (mountainArr.get(l) == target) {
            return l;
        }
        return -1;

    }

    private int searchLeft(MountainArray mountainArr, int l, int r, int target) {
        // 在前有序且升序数组中找 target 所在的索引
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }

        }
        // 因为不确定区间收缩成 1个数以后，这个数是不是要找的数，因此单独做一次判断
        if (mountainArr.get(l) == target) {
            return l;
        }
        return -1;
    }

    private int getMountainTop(MountainArray mountainArr) {
        // 寻找山脉
        int left = 0;
        int right = mountainArr.length() - 1;
        while (left < right) {

            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) > mountainArr.get(mid + 1) &&
                    mountainArr.get(mid) > mountainArr.get(mid - 1)) {
                return mid;
            } else if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return left;
    }
}
