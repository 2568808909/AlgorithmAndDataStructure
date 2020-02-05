package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {

    public static void swap(List list, int i, int j) {
        Object o = list.get(i);
        list.set(i, list.get(j));
        list.set(j, o);
    }

    public static List arrayToList(Object[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }

    public static boolean compare(Comparable a, Comparable b, boolean desc) {
        return (desc && a.compareTo(b) < 0) || (!desc && a.compareTo(b) > 0);
    }

    public static boolean compareEq(Comparable a, Comparable b, boolean desc) {
        return (desc && a.compareTo(b) <= 0) || (!desc && a.compareTo(b) >= 0);
    }
}
