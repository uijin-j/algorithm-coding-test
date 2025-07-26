import java.io.*;
import java.util.*;

// 22:38 시작!
public class Main {
    /**
     *  N, M은 최대 100, 격자의 크기는 최대 10,000
     * 1. 공기를 dfs하면서 만나는 치즈의 cnt를 증가 (dfs를 하면 자연스럽게 내부 공간은 탐색X)
     * 2. cnt를 계산해서 2이상은 공기로 변경 (변경된 칸을 저장)
     * 3. 변경된 칸을 추가적으로 dfs (치즈가 모두 없어질 때까지 1-3 반복)
     *   - 이때 해당 칸의 4변 중 내부 공간이 있으면 해당 내부 공간을 탐색해서 공기 칸에 추가
     */
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    int[][] board = new int[n][m];
	    int cheese = 0;
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j = 0; j < m; ++j) {
	            board[i][j] = Integer.parseInt(st.nextToken());
	            if(board[i][j] == 1) cheese += 1;
	        }
	    }
	    
	    boolean[][] check = new boolean[n][m]; // 확인한 공기인지 확인
	    int[][] cnt = new int[n][m]; // 접촉한 공기 count
	    Queue<Node> q = new LinkedList<>();
	    check[0][0] = true;
	    q.offer(new Node(0, 0));
	    int time = 0;
	    while(cheese > 0) {
	        time += 1;
	        while(!q.isEmpty()) {
    	        Node cur = q.poll();
    	        
    	        for(int d = 0; d < 4; ++d) {
    	            int nx = cur.x + dx[d];
    	            int ny = cur.y + dy[d];
    	            
    	            if(nx >= 0 && nx < n && ny >= 0 && ny < m && !check[nx][ny]) {
    	                if(board[nx][ny] == 1) {
    	                    cnt[nx][ny] += 1;
    	                } else {
    	                    check[nx][ny] = true;
    	                    q.offer(new Node(nx, ny));
    	                }
    	            }
    	        }
    	    }
    	    
    	    for(int i = 0; i < n; ++i) {
	            for(int j = 0; j < m; ++j) {
	                if(board[i][j] == 1 && cnt[i][j] >= 2) {
	                    board[i][j] = 0;
	                    cheese -= 1;
	                    check[i][j] = true;
	                    q.offer(new Node(i, j));
	                }
	            }
	        }
	    }
	    
	    System.out.println(time);
	}
	
	public static class Node {
	    int x, y;
	    
	    public Node(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }    
	}
	
}