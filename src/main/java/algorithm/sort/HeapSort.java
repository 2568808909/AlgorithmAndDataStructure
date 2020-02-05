package algorithm.sort;

import util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆排序
 */
public class HeapSort implements Sort {

    private int len;

    private List<? extends Comparable> list;

    private boolean desc;

    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        int size = list.size();
        len = size;
        this.desc = desc;
        this.list = list;
        for (int i = (size - 1) / 2; i >= 0; i--) {
            int left = i * 2 + 1, right = i * 2 + 2;
            adjust(left, right, i);
        }
        return out();
    }

    private <T extends Comparable> T get() {
        if (len == 0) {
            throw new RuntimeException("empty");
        }
        Comparable result = list.get(0);
        CommonUtils.swap(list, --len, 0);
        int index = 0, left = 1, right = 2, tmp;
        while (left < len) {
            tmp = index;
            if (right < len) {
                index = adjust(left, right, index);
            } else if (CommonUtils.compare(list.get(left), list.get(index), desc)) {
                CommonUtils.swap(list, index, left);
            }
            if (index == tmp) {
                break;
            }
            left = index * 2 + 1;
            right = index * 2 + 2;
        }

        return ((T) result);
    }

    private int adjust(int left, int right, int i) {
        int index = i;
        if (left < len && CommonUtils.compare(list.get(left), list.get(index), desc)) {
            index = left;
        }
        if (right < len && (CommonUtils.compare(list.get(right), list.get(index), desc))) {
            index = right;
        }
        if (index != i) {
            CommonUtils.swap(list, index, i);
            return adjust(index * 2 + 1, index * 2 + 2, index);
        } else {
            return index;
        }
    }

    private <T extends Comparable> List<T> out() {
        List<T> list = new ArrayList<>();
        while (len > 0) {
            list.add(get());
        }
        return list;
    }

}
