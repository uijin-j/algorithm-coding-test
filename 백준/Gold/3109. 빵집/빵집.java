import java.io.*;
import java.util.*;

// 1번. 빵집
public class Main
{
    /**
     * 첫번째 열에서 마지막 열로 도달해야 함 (최단거리 X) 
     */
    static int r, c;
    static boolean[][] board;
    static int[][] dp; // dp[i][j]: (i, j)에서 빵집까지 도달할 수 있는가? (1: true / 2: false)
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    r = Integer.parseInt(st.nextToken());
	    c = Integer.parseInt(st.nextToken());
	    dp = new int[r][c];
	    board = new boolean[r][c];
	    for(int i = 0; i < r; ++i) {
	        char[] row = bf.readLine().toCharArray();
	        for(int j = 0; j < c; ++j) {
	            board[i][j] = (row[j] == '.') ? true : false;
	        }
	    }
	    
	    int count = 0;
	    for(int start = 0; start < r; ++start) { // 10_000 * 500 = 5_000_000
	        if(canReach(start, 0)) count++;
	    }
	    
	    System.out.println(count);
	}
	
	static int[] dx = { -1, 0, 1 };
    public static boolean canReach(int x, int y) {
        if(dp[x][y] != 0) return (dp[x][y] == 1) ? true : false;
        if(y == c - 1) {
            dp[x][y] = 1;
            return true;
        }
        
        for(int d = 0; d < 3; ++d) {
            int nx = x + dx[d];
            int ny = y + 1;
            
            if(nx >= 0 && nx < r && board[nx][ny]) {
                boolean result = canReach(nx, ny);
                
                if(result) {
                    board[nx][ny] = false;
                    dp[x][y] = 1;
                    return true;
                }
            }
        }
        
        dp[x][y] = -1;
        return false;
    }
}