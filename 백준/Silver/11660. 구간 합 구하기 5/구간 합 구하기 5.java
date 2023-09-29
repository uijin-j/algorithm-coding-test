import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] table;
    public static int[][] dp;
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		table = new int[N+1][N+1];
		dp = new int[N+1][N+1]; // dp[a][b]는 (1, 1)에서 (a, b)까지의 구간합
		
		for(int i = 1; i <= N; ++i)
		    for(int j = 1; j <= N; ++j)
		        table[i][j] = sc.nextInt();
		
		for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= N; ++j) {
                if(j-1 < 1 && i-1 < 1) dp[i][j] = table[i][j];
                else if(j-1 < 1) dp[i][j] = dp[i-1][j] + table[i][j];
                else if(i-1 < 1) dp[i][j] = dp[i][j-1] + table[i][j];
                else dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + table[i][j];
            }
        }
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; ++i) {
		    int x1 = sc.nextInt();
		    int y1 = sc.nextInt();
		    int x2 = sc.nextInt();
		    int y2 = sc.nextInt();
		    
		    int ans = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
		    sb.append(ans + "\n");
		}
		
		System.out.print(sb);
	}
}