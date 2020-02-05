package algorithm.sort;

import util.CommonUtils;

import java.util.List;

/**
 * 选择排序
 * 在冒泡排序的基础上做了一个小优化，即发现顺序不对时，不用立即交换位置，而是继续比较,看后面还有没有更合适的位置
 * 最佳情况：T(n) = O(n^2)
 * 最差情况：T(n) = O(n^2)
 * 平均情况：T(n) = O(n^2)
 * 空间复杂度：O(1)
 * 不稳定
 */
public class SelectionSort implements Sort {
    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            int pos = i;
            for (int j = i + 1; j < size; j++) {
                T a = list.get(pos);
                T b = list.get(j);
                if (CommonUtils.compare(a,b,desc)) {
                    pos = j;
                }
            }
            if (pos != i) {
                CommonUtils.swap(list, i, pos);
            }
        }
        return list;
    }
}
