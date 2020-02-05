package algorithm.sort;

import util.CommonUtils;

import java.util.List;

/**
 * 插入排序
 * 最佳情况：T(n) = O(n^2)
 * 最差情况：T(n) = O(n^2)
 * 平均情况：T(n) = O(n^2)
 * 空间复杂度 O(1)
 * 稳定
 * 内排序
 * 描述：以前半部分是已经排好序为前提，从后半部分取出第一个元素，不断向前比较，
 * 如果不符合排序规则，则将前面的元素往后移，直至符合排序规则为止。
 */
public class InsertionSort implements Sort {
    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            int index = i - 1;
            while (index >= 0 && CommonUtils.compare(current, list.get(index), desc)) {
                list.set(index + 1, list.get(index));
                index--;
            }
            list.set(index + 1, current);
        }
        return list;
    }
}
