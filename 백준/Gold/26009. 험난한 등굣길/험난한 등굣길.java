import java.io.*;
import java.util.*;

public class Main {
    /**
     * 최단거리를 구하는 문제이기 때문에 BFS or DFS
     */
    static int n, m, k;
    static int[][] map;
    static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    map = new int[n+1][m+1];
	    
	    k = Integer.parseInt(bf.readLine());
	    int[][] jam = new int[k][3];
	    for(int i = 0; i < k; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int r = Integer.parseInt(st.nextToken());
	        int c = Integer.parseInt(st.nextToken());
	        int d = Integer.parseInt(st.nextToken());
	        
	        fill(r, c, d);
	    }
	    
	    Queue<int[]> q = new LinkedList<>();
	    map[1][1] = 1;
	    q.offer(new int[]{ 1, 1 });
	    while(!q.isEmpty()) {
	        int[] node = q.poll();
	        int x = node[0];
	        int y = node[1];

	        for(int d = 0; d < 4; ++d) {
	            int nx = x + dx[d];
	            int ny = y + dy[d];
	            
	            if(nx > 0 && nx <= n && ny > 0 && ny <= m && map[nx][ny] == 0) {
	                if(nx == n && ny == m) {
	                    System.out.println("YES");
	                    System.out.println(map[x][y]);
	                    return;
	                }
	                
	                map[nx][ny] = map[x][y] + 1;
	                q.offer(new int[]{ nx, ny });
	            }
	        }
	    }
	    
	    System.out.println("NO");
	}
	
	public static void fill(int x, int y, int d) {
	    // x - d, y
	    // x, y + d
	    // x + d, y
	    // x, y - d
	    if(d == 0) {
	        map[x][y] = 1;
	        return;
	    }
	    
	    int nx = x - d;
	    int ny = y;
	    while(nx < x) {
	        if(nx > 0 && nx <= n && ny > 0 && ny <= m) {
	            map[nx][ny] = -1;
	        }
	        
	        nx++;
	        ny++;
	    }
	    
	    while(nx < x + d) {
	        if(nx > 0 && nx <= n && ny > 0 && ny <= m) {
	            map[nx][ny] = -1;
	        }
	        
	        nx++;
	        ny--;
	    }
	    
	    while(nx > x) {
	        if(nx > 0 && nx <= n && ny > 0 && ny <= m) {
	            map[nx][ny] = -1;
	        }
	        
	        nx--;
	        ny--;
	    }
	    
	    while(nx > x - d) {
	        if(nx > 0 && nx <= n && ny > 0 && ny <= m) {
	            map[nx][ny] = -1;
	        }
	        
	        nx--;
	        ny++;
	    }
	}
}