package algorithm.sort;

import util.CommonUtils;

import java.util.List;

/**
 * 希尔排序
 * 时间复杂度O(n^1.3)~O(n^2)
 * 空间复杂度O(1)
 * 内排序
 * 思想：
 * 本质上也是插入排序，经过改良之后更加高效。对列表元素按照一定的增量进行分组，每组使用插入排序进行操作。
 * 随着增量的减少，每组的关键词会越来越多，当增量为1时，所有元素都被分为一组，算法结束。
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
