import java.io.*;
import java.util.*;

// 23:36 시작!
public class Main {
    /**
     * N개의 색 중에 K개의 색을 고르는 경우의 수
     * - 1_000_000_003 의 나머지로 고르기
     * 
     * 1 2 3 4
     * 2
     * 
     * DP?
     */
    static int MOD = 1_000_000_003;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    int k = Integer.parseInt(bf.readLine());
	    
	    int[][] dp = new int[n+1][n+1];
	    for(int i = 0; i <= n; ++i) {
	        dp[i][1] = i;
	        dp[i][0] = 1;
	    }
	    
	    for(int i = 4; i <= n; ++i) {
	        for(int j = 2; j <= (i+1)/2; ++j) {
	            dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % MOD;
	        }
	    }
	    
	    System.out.println(dp[n][k]);
	}
}