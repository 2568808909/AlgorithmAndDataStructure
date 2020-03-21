package algorithm.dp;

/**
 * leetcode 5. 最长回文子串
 */
public class LongestPalindrome {
    /**
     * 动态规划解法：
     * P(i,j)表示i到j之间是否为回文串
     * P(i,j)=s[i]==s[j]&&P(i+1,j-1)
     *
     * 出口为: 当i==j时返回true，当i+1==j&&s[i]==s[j]时返回true，否则返回false
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if ("".equals(s)) return s;
        int len = s.length();
        boolean[][] lp = new boolean[len][len];
        lp[len - 1][len - 1] = true;
        int start = 0, end = 0, max = -1;
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    lp[i][j] = true;
                } else if (i + 1 == j) {
                    lp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    lp[i][j] = s.charAt(i) == s.charAt(j) && lp[i + 1][j - 1];
                }
                if (lp[i][j]) {
                    int res = j - i;
                    if (res > max) {
                        start = i;
                        end = j;
                        max = res;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

}
