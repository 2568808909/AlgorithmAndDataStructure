package algorithm.sort;

import util.CommonUtils;

import java.util.List;

/**
 * 希尔排序
 * 时间复杂度O(n^1.3)~O(n^2)
 * 空间复杂度O(1)
 * 本质上也是插入排序，经过改良之后更加高效。
 */
public class ShellSort implements Sort {
    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        int size = list.size();
        int gap = size / 2;
        while (gap > 0) {
            for (int i = gap; i < size; i++) {
                T current = list.get(i);
                int index = i - gap;
                while (index >= 0 && CommonUtils.compare(current, list.get(index), desc)) {
                    list.set(index + gap, list.get(index));
                    index -= gap;
                }
                list.set(index + gap, current);
            }
            gap /= 2;
        }
        return list;
    }
}
