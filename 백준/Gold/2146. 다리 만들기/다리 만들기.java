import java.io.*;
import java.util.*;

// 13:12 시작! 
public class Main
{
    static int n, min;
    static boolean[][] map;
    static int[][] visit, dist;
    static Queue<int[]> q;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    map = new boolean[n][n];
	    visit = new int[n][n];
	    min = Integer.MAX_VALUE;
	    
	    StringTokenizer st;
	    for(int i = 0; i < n; ++i) {
	       st = new StringTokenizer(bf.readLine());
	       for(int j = 0; j < n; ++j) {
	           map[i][j] = Integer.parseInt(st.nextToken()) == 1;
	       }
	    }
	    
	    int island = 1;
	    for(int i = 0; i < n; ++i) {
	       for(int j = 0; j < n; ++j) {
                if(map[i][j] && visit[i][j] == 0) { // 새로운 섬 발견!
                    q = new LinkedList<>();
                    dist = new int[n][n];
                    dfs(i, j, island);
                    findMinDist(island);
                }
	       }
	    }
	    
	    System.out.println(min);
	}
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	public static void dfs(int x, int y, int island) {
	    visit[x][y] = island;
	    
	    boolean bound = false;
	    for(int i = 0; i < 4; ++i) {
	        int nx = x + dx[i];
	        int ny = y + dy[i];
	        
	        if(nx >= 0 && nx < n && ny >= 0 && ny < n && visit[nx][ny] == 0) {
	            if(map[nx][ny]) {
	                dfs(nx, ny, island);
	                continue;
	            }
	            
	            bound = true;
	        }
	    }
	    
	    if(bound) {
	        q.offer(new int[]{x, y});
	        dist[x][y] = 1;
	    }
	}
	
	public static void findMinDist(int island) {
	    while(!q.isEmpty()) {
	        int[] point = q.poll();
	        int x = point[0];
	        int y = point[1];
	            
            for(int j = 0; j < 4; ++j) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < n && dist[nx][ny] == 0) {
    	            if(map[nx][ny] && visit[nx][ny] != island) {
    	                min = Math.min(min, dist[x][y] - 1);
    	                return;
    	            }
    	            
    	            if(!map[nx][ny]) {
    	                dist[nx][ny] = dist[x][y] + 1;
    	                q.offer(new int[]{nx, ny});
    	            }
    	        }
            }
	    }
	}
}