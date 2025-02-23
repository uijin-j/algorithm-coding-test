import java.io.*;
import java.util.*;

public class Main
{
    static int n, m;
    static int[][] board;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            String string = bf.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = string.charAt(j) - '0';
                min = Math.min(min, board[i][j]);
                max = Math.max(max, board[i][j]);
            }
        }
        
        int result = 0;
        for (int k = min; k < max; k++) {
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (board[i][j] == k) {
                        result += bfs(i, j, k);
                    }
                }
            }
        }
        
        System.out.println(result);
	}
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	public static int bfs(int sx, int sy, int depth) {
	    Queue<int[]> q = new LinkedList<>();
	    q.offer(new int[]{ sx, sy });
	    board[sx][sy] = depth + 1;
	    
	    int count = 1;
	    boolean possible = true;
	    while(!q.isEmpty()) {
	        int[] p = q.poll();
	        int x = p[0];
	        int y = p[1];
	        
	        for (int d = 0; d < 4; d++) {
                int ny = x + dy[d];
                int nx = y + dx[d];
                
                if (ny < 0 || ny >= n || nx < 0 || nx >= m || board[ny][nx] < depth) {
                    possible = false;
                    continue;
                }
                
                if (board[ny][nx] != depth) {
                    continue;
                }
                
                q.add(new int[]{ny, nx});
                board[ny][nx] = depth + 1;
                count++;
            }
	    }
	    
	    if(possible) return count;
	    return 0;
	}
}