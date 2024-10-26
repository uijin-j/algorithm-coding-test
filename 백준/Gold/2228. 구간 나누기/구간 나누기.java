import java.io.*;
import java.util.*;

/**
 *  19:01 start!
 */
public class Main
{
    /**
     * n0 n1 ... nn (1개 ~ 100개의 수)
     * M개의 구간으로 나누기
     * 
     * 투포인터? 구간이 여러 개라 적용이 쉽지 않음..
     * (힌트를 보고.. DP!라는 걸 알게됨😢)
     * 
     * Q. DP를 어떻게 활용할 수 있을까? (문제를 작게 분할 가능!)
     *  - 1. K부터 nn까지를 마지막 구간으로 보고,
     *  - 2. 0 ~ K-2 구간에서 M-1 구간의 최대 합과 더하기 (K를 조정하면서 최댓값으로)
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int M = Integer.parseInt(st.nextToken());
	   
	    int[] arr = new int[N+1];
	    int[] sum = new int[N+1]; // 누적합 K ~ i까지의 합은 sum[i] - sum[k-1]
	    for(int i = 1; i <= N; ++i) {
	        arr[i] = Integer.parseInt(bf.readLine());
	        sum[i] = sum[i-1] + arr[i];
	    }
	    
	    int[][] dp = new int[N+1][M+1]; // dp[i][j]: 1 ~ i에서 j개의 구간을 설정했을 때, 최대합
	    for(int i = 0; i <= N; ++i) {
	        Arrays.fill(dp[i], -32768 * 101);
	        dp[i][0] = 0;
	    }
	    
	    dp[1][1] = arr[1];
	    
	    for(int i = 2; i <= N; ++i) {
	        for(int j = 1; j <= M; ++j) {
	            dp[i][j] = dp[i-1][j];
	            
	            if(j == 1) dp[i][j] = Math.max(dp[i][j], sum[i]);

	            for(int k = 1; k <= i; ++k) {
	                if(k >= 2) {
	                    dp[i][j] = Math.max(dp[i][j], dp[k-2][j-1] + (sum[i] - sum[k-1]));
	                }
	             }
	             
	             //System.out.println("dp["+ i+ "][" + j + "] = " + dp[i][j]);
	         }
	     }
	     
	    System.out.println(dp[N][M]);
	}
}