class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int[][] dp = new int[2][n]; // dp[0][i]: 0번째 집을 훔치고, i까지 있을 때 / dp[1][i]: 0번째집을 훔치지 않고, i까지 있을 때
        dp[1][1] = money[1];
        for(int i = 2; i < n; ++i) {
            dp[0][i] = Math.max(dp[0][i-2] + money[i], dp[0][i-1]);
            dp[1][i] = Math.max(dp[1][i-2] + money[i], dp[1][i-1]);
        }
        
        return Math.max(dp[0][n-2] + money[0], dp[1][n-1]);
    }
}