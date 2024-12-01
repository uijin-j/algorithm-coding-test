import java.io.*;
import java.util.*;

// 21:37 시작!
public class Main
{
    /**
     * 
     */
    static int n, m;
    static char[][] board;
    static int[][] visit;
    static boolean cycle;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    board = new char[n][m];
	    
	    for(int i = 0; i < n; ++i) {
	        board[i] = bf.readLine().toCharArray();
	    }

	    for(int i = 0; i < n; ++i) {
	        for(int j = 0; j < m; ++j) {
	            visit = new int[n][m];
	            visit[i][j] = 1;
	            dfs(i, j, i, j);
	            
	            if(cycle) {
	                System.out.println("Yes");
	                return;
	            }
	        }
	    }
	    
	    System.out.println("No");
	}
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	public static void dfs(int x, int y, int sx, int sy) {
	    for(int d = 0; d < 4; ++d) {
	        int nx = x + dx[d];
	        int ny = y + dy[d];
	        
	        if(nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == board[x][y]) {
	            if(nx == sx && ny == sy && visit[x][y] - visit[sx][sy] >= 3) {
	                cycle = true;
	                return;
	            }
	            
	            if(visit[nx][ny] > 0) continue;
	            
	            visit[nx][ny] = visit[x][y] + 1;
	            dfs(nx, ny, sx, sy);
	        }
	    }
	}
}