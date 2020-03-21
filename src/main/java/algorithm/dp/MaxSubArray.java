package algorithm.dp;


/**
 * LeetCode 53 最大子序和
 */
public class MaxSubArray {
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i], nums[i] + nums[i - 1]);
            max = Math.max(nums[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] numbers = {-2, 1, -2, 4, -1, 2, -5, 4};
        System.out.println(maxSubArray(numbers));
    }
}
