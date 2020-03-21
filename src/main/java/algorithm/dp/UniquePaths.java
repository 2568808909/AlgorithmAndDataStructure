package algorithm.dp;

public class UniquePaths {
    public static int uniquePaths(int m, int n) {
        int[][] opt = new int[m][n];
        for (int i = 0; i < m; i++) opt[i][0] = 1;
        for (int i = 0; i < n; i++) opt[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                opt[i][j] = opt[i][j - 1] + opt[i - 1][j];
            }
        }
        return opt[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));
    }
}
