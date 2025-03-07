import java.io.*;
import java.util.*;

public class Main {
	
	static final int MOD = 10007;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[49][49];
		for(int i = 0; i < 49; i++) {
            dp[i][0] = 1;
        }
        
		for(int i = 1; i < 49; i++) {
			for(int j = 1; j < i + 1; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % MOD;
			}
		}
        
		int result = 0;
		for(int i = 1; i <= N/4; i++) {
			if(i%2 != 0) result = (result + dp[13][i] * dp[52-4*i][N-4*i]) % MOD;
			else result = (result - (dp[13][i]*dp[52-4*i][N-4*i]) % MOD + MOD) % MOD;
		}
		System.out.println(result);
	}
}