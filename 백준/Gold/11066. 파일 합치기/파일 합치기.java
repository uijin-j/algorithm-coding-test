import java.io.*;
import java.util.*;

// 2. 파일 합치기
public class Main {
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(bf.readLine());
	    
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    while(t > 0) {
	        int k = Integer.parseInt(bf.readLine()); // 3 <= k <= 500
	        int[] size = new int[k+1]; // size[i] <= 10_000
	        
	        st = new StringTokenizer(bf.readLine());
	        for(int i = 1; i <= k; ++i) {
	            size[i] = Integer.parseInt(st.nextToken());
	        }
	        
	        // 어떤 순서로 합칠지 모든 경우를 구하면 시간초과
	        
	        int[] sum = new int[k+1];
	        for(int i = 1; i <= k; ++i) {
	            sum[i] = sum[i-1] + size[i];
	        }
	        
	        int[][] dp = new int[k+1][k+1]; // dp[i][j]: i~j챕터까지 합치는 최소 비용
	        for(int gap = 1; gap < k; ++gap) {
	            for(int s = 1; s + gap <= k; ++s) {
	                int e = s + gap;
	                
	                dp[s][e] = Integer.MAX_VALUE;
	                for(int m = s; m < e; ++m) {
	                    dp[s][e] = Math.min(dp[s][e], dp[s][m] + dp[m+1][e] + (sum[e] - sum[s-1]));
	                }
	            }
	        }
	        
	        sb.append(dp[1][k]).append("\n");
	        
	        t--;
	    }
	    
	    System.out.println(sb);
	}
}