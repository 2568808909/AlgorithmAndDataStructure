package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 工具类，提供一些常用的操作
 */
public class CommonUtils {

    /**
     * 交换列表内的两个元素
     * @param list 列表
     * @param i 下标1
     * @param j 下标2
     */
    public static void swap(List list, int i, int j) {
        Object o = list.get(i);
        list.set(i, list.get(j));
        list.set(j, o);
    }

    /**
     * 将数组转为列表
     * @param array 数组
     * @return 转换后的列表
     */
    public static List arrayToList(Object[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }

    /**
     * 为排序提供比较的判断，不判断相等的情况
     * @param a 元素1
     * @param b 元素2
     * @param desc 是否降序
     * @return 返回比较结果
     */
    public static boolean compare(Comparable a, Comparable b, boolean desc) {
        return (desc && a.compareTo(b) < 0) || (!desc && a.compareTo(b) > 0);
    }

    /**
     * 为排序提供比较的判断，相等也判断
     * @param a 元素1
     * @param b 元素2
     * @param desc 是否降序
     * @return 返回比较结果
     */
    public static boolean compareEq(Comparable a, Comparable b, boolean desc) {
        return (desc && a.compareTo(b) <= 0) || (!desc && a.compareTo(b) >= 0);
    }
}
