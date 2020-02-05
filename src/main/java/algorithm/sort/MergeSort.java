package algorithm.sort;

import util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 归并排序
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
