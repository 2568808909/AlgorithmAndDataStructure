package algorithm.dp;

import java.util.Scanner;

public class Coin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] v = {0, 1, 4, 5};
        int[][] opt;
        int[] cnt = new int[4];
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            opt = new int[4][n + 1];
            for (int i = 1; i <= n; i++) opt[1][i] = i;
            for (int i = 2; i <= 3; i++) {
                for (int j = 1; j <= n; j++) {
                    opt[i][j] = opt[i - 1][j];
                    if (j >= v[i]) {
                        opt[i][j] = Math.min(opt[i][j], opt[i][j - v[i]] + 1);
                    }
                }
            }
            //计算各个硬币的使用情况
            int row = 3, col = n;
            while (true) {
                if (opt[row][col] < opt[row - 1][col]) {
                    cnt[row]++;
                    col -= v[row];
                } else {
                    if (row == 1) {
                        cnt[row] += opt[row][col];
                        break;
                    }
                    row--;
                }
                if (col == 0) {
                    break;
                }
            }
            for (int i = 1; i <= 3; i++) {
                System.out.print(cnt[i]);
                if(i!=3){
                    System.out.print(",");
                }
                cnt[i] = 0;
            }
            System.out.println();
        }
    }

// 这样只能计算最少哦需要几个硬币，无法倒回去搜索各个硬币的使用情况
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int[] v = {0, 1, 4, 5};
//        int[] opt;
//        while (scanner.hasNext()) {
//            int n = scanner.nextInt();
//            opt = new int[n + 1];
//            for (int i = 1; i <= n; i++) opt[i] = i;
//            for (int i = 2; i <= 3; i++) {
//                for (int j = 1; j <= n; j++) {
//                    if(j>=v[i]) {
//                        opt[j] = Math.min(opt[j], opt[j - v[i]] + 1);
//                    }
//                }
//            }
//            System.out.println(opt[n]);
//        }
//    }

}
