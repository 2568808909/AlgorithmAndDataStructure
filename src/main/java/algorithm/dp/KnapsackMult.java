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

//    /**
//     * 一维
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
//            int[] opt = new int[W + 1];
//            for (int i = 1; i <= n; i++) {
//                for (int j = W; j >= w[i]; j--) {
//                    for (int k = 0; k * w[i] <= j && k <= s[i]; k++) {  //增加一个数量的限制k<=s[i]
//                        opt[j] = Math.max(opt[j], opt[j - k * w[i]] + k * v[i]);
//                    }
//                }
//            }
//            System.out.println(opt[W]);
//        }
//    }

    /**
     * 二进制优化，将物品的数量进行拆分打包，打包成2^0 2^1 2^2 ……2^k 件物品
     * 因为这些数的二进制表示分别是 1 10 100 1000 ……这样就可以组合出所有数字
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] v = new int[15000], w = new int[15000];
        int s, pos, a, b;
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int W = scanner.nextInt();
            pos = 1;
            for (int i = 1; i <= n; i++) {
                a = scanner.nextInt();
                b = scanner.nextInt();
                s = scanner.nextInt();
                for (int k = 1; k <= s; k <<= 1) {
                    w[pos] = a * k;
                    v[pos++] = b * k;
                    s -= k;
                }
                if (s > 0) {
                    w[pos] = a * s;
                    v[pos++] = b * s;
                }
            }
            int[] opt = new int[W + 1];
            for (int i = 1; i <= pos; i++) {
                for (int j = W; j >= w[i]; j--) {
                    opt[j] = Math.max(opt[j], opt[j - w[i]] + v[i]);
                }
            }
            System.out.println(opt[W]);
        }
    }

}
