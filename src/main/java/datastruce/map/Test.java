package datastruce.map;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>(9);
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            map.put(random.nextInt(), random.nextInt());
        }
        System.out.println(map.put(1, 2));
        System.out.println(map.put(1, 1));
        System.out.println(map);
        System.out.println(map.get(1));
    }
}
