import java.io.*;

public class Main
{
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        long[][][] dp = new long[n+1][11][1<<10]; // dp[i][j][k]: j로 끝나는 i자리 숫자 (이진수 k에 마킹된 수를 모두 사용)

		for(int i = 1; i < 10; i++) {
			dp[1][i][1<<i] = 1; // i로 끝나는 1자리 숫자 (i포함)
		}

        for(int i = 2; i < n + 1; ++i) { // 2자리 숫자 ~ n자리 숫자
            for(int j = 0; j < 10; ++j) { // 0으로 끝남 ~ 9로 끝남
                for(int k = 0; k < 1024; ++k) { // 포함되는 숫자 bit mask
                    int bit = k | (1 << j); // j로 끝나야 하니까, j는 무조건 포함!
                    if(j==0) {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]) % MOD;
                    }
                    else if(j==9) {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k]) % MOD;
                        continue;
                    }
                    else {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k] + dp[i-1][j-1][k]) % MOD;
                    }
                }
            }
        }

        long result = 0;
        for(int i = 0; i < 10; ++i) {
            result = (result + dp[n][i][1023]) % MOD; // i로 끝나는 n자리 숫자(0~9사용)
        }
        System.out.println(result);
    }
}