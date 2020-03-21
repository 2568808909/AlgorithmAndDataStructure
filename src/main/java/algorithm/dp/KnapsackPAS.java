package algorithm.dp;

import java.util.Scanner;

/**
 * 完全背包问题，与01背包的区别是：物品的数量时无限的。
 */
public class KnapsackPAS {
    //使用三层循环，最暴力的解法
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            int n = scanner.nextInt();
//            int W = scanner.nextInt();
//            int[] v = new int[n + 1], w = new int[n + 1];
//            for (int i = 1; i <= n; i++) {
//                w[i] = scanner.nextInt();
//                v[i] = scanner.nextInt();
//            }
//            int[][] opt = new int[n + 1][W + 1];
//            for (int i = 1; i < opt.length; i++) {
//                for (int j = 0; j < opt[i].length; j++) {
//                    for (int k = 0; k * w[i] <= j; k++) {
//                        opt[i][j] = Math.max(opt[i][j], opt[i - 1][j - k * w[i]] + k * v[i]);
//                    }
//                }
//            }
//            System.out.println(opt[n][W]);
//        }
//    }

    /**
     * 对以上解放优化了一下
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int W = scanner.nextInt();
            int[] v = new int[n + 1], w = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                w[i] = scanner.nextInt();
                v[i] = scanner.nextInt();
            }
            int[][] opt = new int[n + 1][W + 1];
            for (int i = 1; i < opt.length; i++) {
                for (int j = 1; j < opt[i].length; j++) {
                    opt[i][j] = opt[i - 1][j]; //不选
                    //在选择了第i个物品的情况下继续将第i个物品加入到背包中，所以这不需要i-1
                    if (j >= w[i]) opt[i][j] = Math.max(opt[i][j], opt[i][j - w[i]] + v[i]);
                }
            }
            System.out.println(opt[n][W]);
        }
    }
}
