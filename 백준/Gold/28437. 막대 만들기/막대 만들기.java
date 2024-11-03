import java.io.*;
import java.util.*;

/**
 * 21:55 시작!
 */
public class Main
{
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

	    int[] dp = new int[100_001]; // dp[i]는 i를 만드는 방법 수
	    int n = Integer.parseInt(bf.readLine());
	    st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) {
	        int length = Integer.parseInt(st.nextToken());
	        dp[length]++;
	    }

        for(int i = 1; i <= 50000; i++) {
            for(int j = 2; i * j <= 100_000; j++) {
                dp[i * j] += dp[i];
            }
        }
	    
	    int m = Integer.parseInt(bf.readLine());
	    st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < m; ++i) {
	        int target = Integer.parseInt(st.nextToken());
	        System.out.print(dp[target] + " ");
	    }
	    
	    
	}
}