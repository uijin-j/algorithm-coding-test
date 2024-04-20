import java.io.*;
import java.util.*;

public class Main
{
	static int[] stones;
	static long[] dp;
	static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
		stones = new int[n];
		dp = new long[n]; // dp[i]는 i에서 n-1까지 가는데 필요한 최소 힘
		StringTokenizer st = new StringTokenizer(bf.readLine());

		for(int i = 0; i < n; ++i) {
			stones[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(jump(0));
    }

	// now에서 n-1까지 가는데 필요한 최소 힘
	public static long jump(int now) {
		if(now == n - 1) return 0;
		if(dp[now] != 0) return dp[now];

		dp[now] = Long.MAX_VALUE;

		for(int i = now + 1; i < n; ++i) {
			dp[now] = Math.min(dp[now], Math.max(jump(i), getPower(now, i)));
		}

		return dp[now];
	}

	public static long getPower(int from, int to) {
		return (to - from) * (long) (1 + Math.abs(stones[from] - stones[to]));
	}
}
