class Solution {
    // 항상 가장 큰 숫자를 떼어간다고 최댓값이 아님!
    // DP! => ❗️ 문제는 순환한다는 것..
    public int solution(int sticker[]) {
        int n = sticker.length;
        
        if(n == 1) return sticker[0];
        
        // 첫번째 스티커를 뗐을 때
        int[] dp = new int[n];
        dp[0] = sticker[0];
        dp[1] = sticker[0];
        for(int i = 2; i < n-1; ++i) {
            dp[i] = Math.max(dp[i-1], sticker[i] + dp[i-2]);
        }
        
        int answer = dp[n-2];
        
        // 첫번째 스티커를 떼지 않았을 때
        dp = new int[n];
        dp[0] = 0;
        dp[1] = sticker[1];
        for(int i = 2; i < n; ++i) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }
        
        return Math.max(answer, dp[n-1]);
    }
}