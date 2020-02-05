package algorithm.sort;

import util.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Test {
    public static void main(String[] args) {
        Integer[] array = {5, 10, 4, 69, 2, 46, 8, 2, 1, 4, 68, 5};
        String[] strs = {"cied", "abc", "abcd", "c", "bbbbbbb", "b", "bbb"};
        List<Integer> integers = Arrays.stream(array).collect(Collectors.toList());
        Sort sort = new RadixSort();
        System.out.println(sort.sort(integers, false));
        System.out.println(sort.sort(CommonUtils.arrayToList(strs), false));
        System.out.println(sort.sort(integers, true));
        System.out.println(sort.sort(CommonUtils.arrayToList(strs), true));
    }
}
