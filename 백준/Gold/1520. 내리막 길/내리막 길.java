import java.io.*;
import java.util.*;

/**
 *  19:10 시작!
 */
public class Main
{
    /**
     * 모든 경로의 수? dfs! O(MN) ~= 250,000 but 시간 초과가 남..
     * 
     */
    static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] map, dp;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    M = Integer.parseInt(st.nextToken()); // 세로 길이
	    N = Integer.parseInt(st.nextToken()); // 가로 길이
	    
	    map = new int[M][N];
	    for(int i = 0; i < M; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j = 0; j < N; ++j) {
	            map[i][j] = Integer.parseInt(st.nextToken());
	        }
	    }
	    
	    // (0, 0)에서 (M-1, N-1)까지 갈 수 있는 경우의 수 (항상 내려가도록)
	    dp = new int[M][N];
	    for(int i = 0; i < M; ++i) Arrays.fill(dp[i], -1);
	    System.out.println(dfs(0, 0));
	}
	
	public static int dfs(int x, int y) {
	    if(dp[x][y] != -1) return dp[x][y];
	    if(x == M -1 && y == N - 1) return 1;
	    
	    int total = 0;
	    for(int d = 0; d < 4; ++d) {
	        int nx = x + dx[d];
	        int ny = y + dy[d];
	        
	        if(nx >= 0 && nx < M && ny >= 0 && ny < N && map[nx][ny] < map[x][y]) {
	            total += dfs(nx, ny);
	        }
	    }
	    
	    return dp[x][y] = total;
	}
}