package algorithm.sort;

import util.CommonUtils;

import java.util.List;

/**
 * 快速排序
 * 最佳情况：T(n) = O(nlogn)
 * 最差情况：T(n) = O(n^2)
 * 平均情况：T(n) = O(nlogn)
 * 空间复杂度：O(1)
 * 内排序
 * 不稳定
 * 思想：
 * 选择一个数作为基数，将列表中比这个基数大的元素放在其左边，比这个元素大的放在其右边(增序)。
 * 然后继续对左右两部分数据进行上述操作。
 */
public class QuickSort implements Sort {

    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        quickSort(list, 0, list.size() - 1, desc);
        return list;
    }

    private void quickSort(List<? extends Comparable> list, int left, int right, boolean desc) {
        if (left > right) {
            return;
        }
        Comparable pivot = list.get(left);
        int i = left, j = right;
        while (i != j) {
            while (CommonUtils.compareEq(pivot, list.get(j), desc) && i < j) {
                j--;
            }
            while (CommonUtils.compareEq(list.get(i), pivot, desc) && i < j) {
                i++;
            }
            if (i < j) {
                CommonUtils.swap(list, i, j);
            }
        }
        CommonUtils.swap(list, left, i);
        quickSort(list, left, i - 1, desc);
        quickSort(list, i + 1, right, desc);
    }
}
