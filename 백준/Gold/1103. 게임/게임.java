import java.io.*;
import java.util.*;

// 14:31 시작
public class Main
{
    static int n, m;
    static int[][] board, dp;
    static boolean[][] visit;
    static int max;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    board = new int[n][m];
	    for(int i = 0; i < n; ++i) {
	        char[] line = bf.readLine().toCharArray();
	        for(int j = 0; j < m; ++j) {
	            board[i][j] = (line[j] == 'H') ? -1 : line[j] - '0';
	        }
	    }
	    
	    dp = new int[n][m];
	    visit = new boolean[n][m];
	    visit[0][0] = true;
	    dfs(0, 0, 1);
	    
	    System.out.println(max);
	}
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	public static void dfs(int x, int y, int move) {
	    if(max == -1) return;
	   
	    max = Math.max(max, move);
	    dp[x][y] = move;
	    
	    for(int d = 0; d < 4; ++d) {
	        int nx = x + board[x][y] * dx[d];
	        int ny = y + board[x][y] * dy[d];
	        
	        if(nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] != -1) {
	            if(visit[nx][ny]) {
	                max = -1;
	                return;
	            }
	            
	            if(dp[nx][ny] > move) continue;
	            
	            visit[nx][ny] = true;
	            dfs(nx, ny, move + 1);
	            visit[nx][ny] = false;
	        }
	    }
	}
}