import java.io.*;
import java.util.*;

// 움직이는 미로 탈출
public class Main {
    static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, 0, 1, 1, 1, 0, -1, -1, -1 };
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    boolean[][] board = new boolean[8][8];
	    for(int i = 0; i < 8; ++i) {
	        String line = bf.readLine();
	        for(int j = 0; j < 8; ++j) {
	            if(line.charAt(j) == '.') board[i][j] = true;
	        }
	    }
	    
	    // 8초 버티기
	    Queue<int[]> q = new LinkedList<>();
	    q.offer(new int[]{ 7, 0 });
	    int active = 7;
	    while(!q.isEmpty()) {
	        int size = q.size();
	        
	        for(int i = 0; i < size; ++i) {
	            int[] p = q.poll();
	            int x = p[0];
	            int y = p[1];

	            for(int d = 0; d < 9; ++d) {
	                int nx = x + dx[d];
	                int ny = y + dy[d];
	                
	                if(nx >= 0 && nx <= active && ny >= 0 && ny < 8 && board[nx][ny]) {
	                    if(nx == 0 && ny <= 7) {
	                        System.out.println("1");
	                        return;
	                    }
	                    
	                    if(--nx < 0 || !board[nx][ny]) continue;
	                    q.offer(new int[]{ nx, ny });
	                }
	            }
	        }

	        active--;
	    }
	    
	    System.out.println("0");
	}
}