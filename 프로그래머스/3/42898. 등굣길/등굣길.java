import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // [n, m]까지 가는 방법 => [n-1, m]까지 가는 방법 + [n, m-1]까지 가는 방법
        boolean[][] blocked = new boolean[n+1][m+1];
        for(int[] puddle : puddles) {
            blocked[puddle[1]][puddle[0]] = true; // 행/열 반대 주의!
        }
        
        int[][] dp = new int[n+1][m+1];
        dp[1][1] = 1;
        
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                if(i == 1 && j == 1) continue;
                if(blocked[i][j]) continue;
                
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_007;
            }
        }
        
        return dp[n][m];
    }
}