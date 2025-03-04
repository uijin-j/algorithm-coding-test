import java.io.*;
import java.util.*;

public class Main
{
    /**
     * 게임 보드에서 사이클을 찾기
     * 1. 모든 칸을 기준으로 DFS를 수행, 만약 DFS 과정에서 이미 방문한 점을 또 간다면?
     *    사이클이 존재하는 것! but 바로 뒤로 돌아갈수도 있으니 어떤 점으로부터 왔는지 넘겨주기
     */
    static int n, m;
    static char[][] board;
    static boolean[][] visit;
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
	    
	    visit = new boolean[n][m];
	    for(int i = 0; i < n; ++i) {
	        for(int j = 0; j < m; ++j) {
	            if(visit[i][j]) continue;

	            visit[i][j] = true;
	            dfs(i, j, -1, -1);
	            
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
	public static void dfs(int x, int y, int preX, int preY) {
	    for(int d = 0; d < 4; ++d) {
	        int nx = x + dx[d];
	        int ny = y + dy[d];
	        
	        if(nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == board[x][y]) {
	            if(visit[nx][ny]) {
	                if(nx != preX || ny != preY) cycle = true;
	                continue;
	            }
	            
	            visit[nx][ny] = true;
	            dfs(nx, ny, x, y);
	        }
	    }
	}
}