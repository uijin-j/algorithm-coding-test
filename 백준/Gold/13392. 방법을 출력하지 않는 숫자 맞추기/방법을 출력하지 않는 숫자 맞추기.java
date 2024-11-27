import java.io.*;
import java.util.*;

// 21:45 시작!
public class Main
{
    /**
     * 모든 나사는 한 방향으로만 이동
     * DP 일 것 같다..
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    
	    int n = Integer.parseInt(bf.readLine());
	    int[] nums = new int[n + 1];
	    int[] target = new int[n + 1];
	    
	    int idx = 1;
	    for(char ch : bf.readLine().toCharArray()) {
	        nums[idx++] = ch - '0';
	    }

	    idx = 1;
	    for(char ch : bf.readLine().toCharArray()) {
	        target[idx++] = ch - '0';
	    }

	    int[][] dp = new int[n + 1][10];
	    for(int i = 0; i <= n; i++) {
	        Arrays.fill(dp[i], Integer.MAX_VALUE);
	    }
	    
	    for(int i = 0; i < 10; ++i) {
	        dp[0][i] = i;
	    }
	    
	    for(int i = 1; i <= n; ++i) {
	        for(int j = 0; j < 10; ++j) {
	            int left = (target[i] - nums[i] - j + 20) % 10;
    	        int right = 10 - left;
    	        dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + right);
    	        dp[i][(j + left) % 10] = Math.min(dp[i][(j + left) % 10], dp[i-1][j] + left);   
	        }
	    }
        
        int answer = dp[n][0];
        for(int i = 1; i < 10; ++i) { 
            answer = Math.min(answer, dp[n][i]);
        }
        
        System.out.println(answer);
	}
}