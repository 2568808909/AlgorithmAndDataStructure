package algorithm.sort;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基数排序
 * 最佳情况：T(n) = O(n * k)
 * 最差情况：T(n) = O(n * k)
 * 平均情况：T(n) = O(n * k)
 * k为最大数字的位数
 * 空间复杂度O(n+k)
 */
public class RadixSort implements Sort {

    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        if (!(list.get(0) instanceof Integer)) {
            throw new RuntimeException("计数排序只能对整数排序");
        }
        List<Integer> data = list.stream().map(item -> (Integer) item).collect(Collectors.toList());
        Integer maxValue = data.stream().max(Integer::compareTo).orElse(null);
        if (maxValue == null) {
            throw new RuntimeException("empty");
        }
        int capacity = getCapacity(maxValue);
        Integer param = 10, divisor = 1;
        Map<Integer, List<Integer>> numberMap = new HashMap<>();
        for (int i = 0; i < capacity; i++) {
            numberMap.clear();
            for (Integer number : data) {
                Integer remainder = number / divisor % param;
                List<Integer> integers = numberMap.computeIfAbsent(remainder, k -> new LinkedList<>());
                integers.add(number);
            }
            data.clear();
            for (int j = 0; j <= 9; j++) {
                List<Integer> integers = numberMap.get(j);
                if(integers!=null) {
                    data.addAll(integers);
                }
            }
            divisor = param;
            param *= 10;
        }
        return data.stream().map(item -> (T) item).collect(Collectors.toList());
    }

    private int getCapacity(int number) {
        int count = 0;
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count;
    }
}
