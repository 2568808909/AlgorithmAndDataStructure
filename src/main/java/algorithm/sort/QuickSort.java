package algorithm.sort;

import util.CommonUtils;

import java.util.List;

/**
 * 快速排序
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
