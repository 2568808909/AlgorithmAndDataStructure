package algorithm.sort;

import java.util.List;

/**
 * 计数排序
 * 最佳情况：T(n) = O(n+k)
 * 最差情况：T(n) = O(n+k)
 * 平均情况：T(n) = O(n+k)
 * 空间复杂度，为跟传入数据里的最大值和最小值之差
 * 描述：
 * 因为不通用，所以实现，基本思想是开辟一个数组对列表内的数据进行计数，
 * 然后按顺序和计数取出元素，生成一个有序的新列表
 * 稳不稳定：谁知道呢，可以说稳定也可以说不稳定？但网上都说是稳定
 */
public class CountingSort implements Sort{
    @Override
    public <T extends Comparable> List<T> sort(List<T> list, boolean desc) {
        return null;
    }
}
