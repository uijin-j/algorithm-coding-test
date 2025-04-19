import java.io.*;
import java.util.*;

public class Main
{
    /**
     * 15:41 시작!
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine()); // 최대 2_000
        int[] v = new int[n+1];
        for(int i = 1; i <= n; ++i) {
            v[i] = Integer.parseInt(bf.readLine()); // 최대 1_000
        }

        // 단순 완탐? O(2^2000) ~= 시간초과
        // 그리디? => 항상 양쪽 끝 중에서 더 작은 녀석을 선택? but 같다면? => 같을 때는 그 안쪽이 더 작은 애를?
        // dp?
        int[] sum = new int[n+1];
        for(int i = 1; i <= n; ++i) {
            sum[i] = sum[i-1] + v[i];
        }
        
        int[][] dp = new int[n+1][n+1]; // dp[i][j]: i~j 범위의 벼가 있을 때 얻을 수 있는최대 가치?
        for(int i = 1; i <= n; ++i) dp[i][i] = v[i];
        for(int gap = 1; gap < n; ++gap) {
            for(int i = 1; i <= n - gap; ++i) {
                int j = i + gap;
                dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]) + (sum[j] - sum[i-1]);
            }
        }
        
        System.out.println(dp[1][n]);
	}
}