import java.io.*;
import java.util.*;

// 12:15 시작!
public class Main
{
    // sol1. 최소 비용으로 N개의 도시를 모두 방문하고 돌아오기 => 완전 탐색(DFS) ~= O(16!) 시간초과!
    // sol2. 만약 0번 도시에서 시작했을 때, 
    // 0번 도시와 연결된 도시(k)을 하나씩 방문했을 때 얻을 수 있는 최소 거리(d(k)) 중 
    // w[0][k] + d(k)가 최소인 경우를 선택하면 됨!
    static int n, finalStatus;
    static int[][] w, dp;
    static final int INF = 16_000_001;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    w = new int[n][n];
	
	    finalStatus = (1 << n) - 1; // 모든 도시가 방문된 상태
	    dp = new int[n][finalStatus]; // dp[i][j]: j상태의 도시를 모두 방문하고, i 도시를 마지막으로 방문하는 최단거리
	    
	    for(int i = 0; i < n; ++i) {
	        Arrays.fill(dp[i], -1);
	    }
	    
	    StringTokenizer st;
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j = 0; j < n; ++j) {
	            w[i][j] = Integer.parseInt(st.nextToken());
	        }
	    }
	    
	    System.out.println(tsp(0, (1 << 0)));
	}
	
	public static int tsp(int city, int status) {
	    if(status == finalStatus) {
            if(w[city][0] == 0) return INF;
	        return w[city][0];
	    }
	    
	    if(dp[city][status] != -1) return dp[city][status];
	    dp[city][status] = INF;
	    
	    for(int i = 0; i < n; ++i) {
	        if(w[city][i] == 0 || (status & (1 << i)) != 0) continue;
	        int nextStatus = status | (1 << i);
	        dp[city][status] = Math.min(dp[city][status], tsp(i, nextStatus) + w[city][i]);
	    }
	    
	    return dp[city][status];
	}
}