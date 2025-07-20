import java.io.*;
import java.util.*;

// 00:26 시작!
public class Main
{
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    int[][] map = new int[n][m];
	    for(int i = 0; i < n; ++i) {
	        String row  = bf.readLine();
	        for(int j = 0; j < m; ++j) {
	            char ch = row.charAt(j);
	            map[i][j] = ch - '0';
	        }
	    }
	    
	    Queue<Node> q = new LinkedList<>();
	    q.offer(new Node(0, 0, 1, 0));
	    boolean[][][] visited = new boolean[n][m][2];
	    while(!q.isEmpty()) {
	        Node cur = q.poll();
	        
	        if(cur.x == n - 1 && cur.y == m - 1) {
                System.out.println(cur.dist);
                return;
            }
	            
	        for(int d = 0; d < 4; ++d) {
	            int nx = cur.x + dx[d];
	            int ny = cur.y + dy[d];
	            
	            if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
	                if(map[nx][ny] == 0 && !visited[nx][ny][cur.cnt]) {
	                    visited[nx][ny][cur.cnt] = true;
                        q.add(new Node(nx, ny, cur.dist + 1, cur.cnt));
                        continue;
	                }
	                
	                if(cur.cnt == 0 && !visited[nx][ny][0]) {
	                    visited[nx][ny][1] = true;
	                    q.offer(new Node(nx, ny, cur.dist + 1, cur.cnt + 1));
	                }
	            }
	        }
	    }
	    
	    System.out.println(-1);
	}
	
	public static class Node {
	    int x, y, dist;
	    int cnt;
	    
	    public Node(int x, int y, int dist, int cnt) {
	        this.x = x;
	        this.y = y;
	        this.dist = dist;
	        this.cnt = cnt;
	    }
	}
}