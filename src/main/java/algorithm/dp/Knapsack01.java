package algorithm.dp;

import java.util.Scanner;

/**
 * 01背包问题
 */
public class Knapsack01 {
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
                    if (j >= w[i]) {
                        opt[i][j] = opt[i - 1][j];
                    } else {
                        opt[i][j] = Math.max(opt[i - 1][j - w[i]] + v[i], opt[i - 1][j]);
                    }
                }
            }
            System.out.println(opt[n][W]);
        }
    }
}
