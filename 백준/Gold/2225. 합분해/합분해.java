import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n+1][k+1]; // dp[i][j]: 0에서 n까지의 숫자 중에서 j개를 골라 i를 만드는 경우의 수
        
        for(int i = 0; i <= k; ++i) dp[0][i] = 1; // i개를 골라 0을 만드는 경우는 모두 1
        for(int i = 0; i <= n; ++i) dp[i][1] = 1; // 1개를 골라 i를 만드는 경우는 모두 1

        for(int i = 1; i <= n; i++) { // 1을 만드는 경우 -> n을 만드는 경우
            for(int j = 2; j <= k; j++) { // 2개 선택 -> k개 선택 
                for(int l = 0; l <= i; l++) { // l을 1개 선택
                    dp[i][j] = (dp[i][j] + dp[i-l][j-1]) % 1_000_000_000;
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
