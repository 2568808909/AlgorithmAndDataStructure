package algorithm.sort;

import util.CommonUtils;

import java.util.List;

/**
 * 冒泡排序
 * 最佳情况：T(n) = O(n^2)
 * 最差情况：T(n) = O(n^2)
 * 平均情况：T(n) = O(n^2)
 * 空间复杂度：O(1)
 * 内排序
 * 稳定
 * 思想：
 * 取出一个元素，不断向后比较，不符合排序规则就交换，
 */
public class BubbleSort implements Sort {

    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                T a = list.get(i);
                T b = list.get(j);
                if (CommonUtils.compare(a, b, desc)) {
                    CommonUtils.swap(list, i, j);
                }
            }
        }
        return list;
    }
}
