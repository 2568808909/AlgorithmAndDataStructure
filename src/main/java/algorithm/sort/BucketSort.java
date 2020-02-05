package algorithm.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 桶排序
 * 最佳情况：T(n) = O(n+k)
 * 最差情况：T(n) = O(n^2)
 * 平均情况：T(n) = O(n+k)
 * 空间复杂度O(k)
 * 原理和计数排序差不多，都是用开辟空间存放数据，然后按顺序取出。不再是计数，而是对每个桶进行插入排序，
 * 形成一个个链表，然后按顺序将桶里的元素取出来
 * 稳定
 * 外排序
 */
public class BucketSort implements Sort {

    /**
     * 没有实现的兴趣，就不实现了,就返回一个null吧
     * @param list 待排序列表
     * @param desc 是否降序
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        return null;
    }
}
