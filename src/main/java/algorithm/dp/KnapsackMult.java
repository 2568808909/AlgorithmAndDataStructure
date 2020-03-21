package algorithm.dp;

import java.util.Scanner;

/**
 * 多重背包1，完全背包的变形，完全背包的物品都是无限的，而这个多重背包问题中的物品是有限的
 */
public class KnapsackMult {
//    /**
//     * 最暴力的解法，在完全背包暴力解法的基础上增加了一个数量的限制即可
//     * @param args
//     */
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            int n = scanner.nextInt();
//            int W = scanner.nextInt();
//            int[] v = new int[n + 1], w = new int[n + 1], s = new int[n + 1];
//            for (int i = 1; i <= n; i++) {
//                w[i] = scanner.nextInt();
//                v[i] = scanner.nextInt();
//                s[i] = scanner.nextInt();
//            }
//            int[][] opt = new int[n + 1][W + 1];
//            for (int i = 1; i < opt.length; i++) {
//                for (int j = 0; j < opt[i].length; j++) {
//                    for (int k = 0; k * w[i] <= j && k <= s[i]; k++) {  //增加一个数量的限制k<=s[i]
//                        opt[i][j] = Math.max(opt[i][j], opt[i - 1][j - k * w[i]] + k * v[i]);
//                    }
//                }
//            }
//            System.out.println(opt[n][W]);
//        }
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int W = scanner.nextInt();
            int[] v = new int[n + 1], w = new int[n + 1], s = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                w[i] = scanner.nextInt();
                v[i] = scanner.nextInt();
                s[i] = scanner.nextInt();
            }
            int[] opt = new int[W + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = W; j >= w[i]; j--) {
                    for (int k = 0; k * w[i] <= j && k <= s[i]; k++) {  //增加一个数量的限制k<=s[i]
                        opt[j] = Math.max(opt[j], opt[j - k * w[i]] + k * v[i]);
                    }
                }
            }
            System.out.println(opt[W]);
        }
    }

}
