package algorithm.sort;

import java.util.List;

/**
 * 提供统一的排序接口
 */
public interface Sort {

    /**
     * 排序
     * @param list 待排序列表
     * @param desc 是否降序
     * @param <T> 排序元素，要实现Comparable接口
     * @return 返回排序完成的列表
     */
    <T extends Comparable> List<T> sort(List<T> list, boolean desc);
}
