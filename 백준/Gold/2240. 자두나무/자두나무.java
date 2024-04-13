import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int t = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int[] trees = new int[t+1];
		for(int i = 1; i <= t; ++i) {
		    trees[i] = Integer.parseInt(bf.readLine());
		}
		// dp[i][j][k]: 현재 i초, 지금까지 j만큼 움직였고, 현재 자두의 위치가 k일 때, 최대 자두 갯수
		int[][][] dp = new int[t+1][w+1][3];
	    dp[1][0][1] = trees[1] == 1 ? 1 : 0; // 1초 / 0번 움직임 / 현재 위치 1
	    dp[1][1][2] = trees[1] == 2 ? 1 : 0; // 1초 / 1번 움직임 / 현재 위치 2
		
		for(int i = 2; i <= t; ++i) {
		    dp[i][0][1] = dp[i-1][0][1] + (trees[i] == 1 ? 1 : 0);
			dp[i][0][2] = dp[i-1][0][2] + (trees[i] == 2 ? 1 : 0);
	
		    for(int j = 1; j <= w; ++j) {
		        dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2]) + ((trees[i] == 1) ? 1 : 0);
		        dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i-1][j-1][1]) + ((trees[i] == 2) ? 1 : 0);
		    }
		}
		
		int answer = 0;
		for(int i = 0; i <= w; ++i) {
		    answer = Math.max(answer, Math.max(dp[t][i][1], dp[t][i][2]));
		}
		
		System.out.println(answer);
	}
}
