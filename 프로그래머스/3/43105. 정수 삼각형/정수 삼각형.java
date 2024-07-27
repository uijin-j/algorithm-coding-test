class Solution {
    // 항상 큰 숫자로 내려간다고 전체 합이 가장 큰 것 X
    // dp를 쓰자!
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n]; // dp[i][j] : i행 j열까지 오는데 최댓값
        dp[0][0] = triangle[0][0];
        
        for(int i = 1; i < n; ++i) {
            for(int j = 0; j <= i; ++j) {
                if(j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                    continue;
                }
                
                if(j == i) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                    continue;
                }
                
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
        }
        
        int answer = 0;
        for(int i = 0; i < n; ++i) {
            answer = Math.max(answer, dp[n-1][i]);
        }
        
        return answer;
    }
}