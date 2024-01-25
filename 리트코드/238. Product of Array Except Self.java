class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] multiply = new int[n]; // multiply[i]: 첫번째 숫자부터 i번째 숫자까지의 곱
        
        multiply[0] = nums[0];
        for(int i = 1; i < n; ++i) {
            multiply[i] = multiply[i-1] * nums[i];
        }

        int[] result = new int[n];
        int multiplyReverse = 1;

        for(int i = n - 1; i > 0; --i) {
            result[i] = multiply[i-1] * multiplyReverse;
            multiplyReverse *= nums[i];
        }

        result[0] = multiplyReverse;

        return result;
    }
}
