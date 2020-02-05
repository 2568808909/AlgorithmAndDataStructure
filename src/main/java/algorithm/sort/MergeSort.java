package algorithm.sort;

import util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 归并排序
 * 最佳情况：T(n) = O(nlogn)
 * 最差情况：T(n) = O(nlogn)
 * 平均情况：T(n) = O(nlogn)
 * 空间复杂度：O(n)
 * 外排序
 * 稳定
 * 思想：
 * 运用了分治的思想，将列表分成两半，这两半再分，不可分时，每一部分的元素都是有序的，
 * 则在返回的时候，组合这些有序的元素(将两部分有序的列表组成一个有序的列表)
 */
public class MergeSort implements Sort {
    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        if (list.size() == 1) {
            return list;
        }
        int size = list.size();
        int half = size / 2;
        List<T> left = sort(list.subList(0, half), desc);
        List<T> right = sort(list.subList(half, size), desc);
        int index = 0;
        List<T> result = new ArrayList<>();
        for (int i = 0, j = 0; index < list.size(); index++) {
            if (i >= left.size()) {
                result.add(right.get(j++));
            } else if (j >= right.size()) {
                result.add(left.get(i++));
            } else {
                T a = left.get(i);
                T b = right.get(j);
                if (CommonUtils.compare(a, b, desc)) {
                    result.add(a);
                    i++;
                } else {
                    result.add(b);
                    j++;
                }
            }

        }
        return result;
    }
}
