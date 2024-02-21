import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n+1][k+1]; // dp[i][j]: 0에서 
        dp[0][0] = 1;

        for(int i = 0; i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                for(int l = 0; l <= i; l++) {
                    dp[i][j] = (dp[i][j] + dp[i-l][j-1]) % 1_000_000_000;
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
