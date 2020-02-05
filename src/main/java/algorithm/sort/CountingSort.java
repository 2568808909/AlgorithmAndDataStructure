package algorithm.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 计数排序
 * 最佳情况：T(n) = O(n+k)
 * 最差情况：T(n) = O(n+k)
 * 平均情况：T(n) = O(n+k)
 * 空间复杂度，为跟传入数据里的最大值和最小值之差 O(k)
 * 外排序
 * 描述：
 * 因为不通用，所以实现，基本思想是开辟一个数组对列表内的数据进行计数，
 * 然后按顺序和计数取出元素，生成一个有序的新列表
 * 稳不稳定：谁知道呢，可以说稳定也可以说不稳定？但网上都说是稳定
 */
public class CountingSort implements Sort{
    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        List<Integer> data = list.stream().map(item -> (Integer) item).collect(Collectors.toList());
        int bias, min = data.get(0), max = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            Integer number = data.get(i);
            if (number > max)
                max = number;
            if (number < min)
                min = number;
        }
        bias = 0 - min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < data.size(); i++) {
            bucket[data.get(i) + bias]++;
        }
        int index = 0, i = 0;
        while (index < data.size()) {
            if (bucket[i] != 0) {
                data.set(index, i - bias);
                bucket[i]--;
                index++;
            } else
                i++;
        }
        return data.stream().map(item -> (T) item).collect(Collectors.toList());
    }
}
