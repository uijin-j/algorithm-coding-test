class Solution {
    public int solution(int n, int[] tops) {
        // dp[n][0] (가운데가 왼/오와 연결 X) / dp[n][1] (가운데가 왼쪽이랑 연결) / dp[n][2] (가운데가 오른쪽이랑 연결)
        // dp[n][0] = (2 or 1) * (dp[n-1][0] + dp[n-1][1] + dp[n-1][2])
        // dp[n][1] = dp[n-1][0] + dp[n-1][1]
        // dp[n][2] = dp[n-1][0] + dp[n-1][1] + dp[n-1][2]
            
        int[][] dp = new int[n+1][3];
        dp[0][0] = (tops[0] == 1) ? 2 : 1;
        dp[0][1] = 1;
        dp[0][2] = 1;
        
        for(int i = 1; i < n; ++i) {
            dp[i][0] = (((tops[i] == 1) ? 2 : 1) * (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])) % 10007;
            dp[i][1] = (dp[i-1][0] + dp[i-1][1]) % 10007;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 10007;
        }
        
    
        return (dp[n-1][0] + dp[n-1][1] + dp[n-1][2]) % 10007;
    }
}