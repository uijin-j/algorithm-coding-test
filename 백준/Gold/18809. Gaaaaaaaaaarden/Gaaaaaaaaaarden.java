import java.io.*;
import java.util.*;

// 17:25 시작!
public class Main
{
    static int n, m, g, r, max;
    static int[][] garden;
    static List<int[]> points;
    static int[][] green, red;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    g = Integer.parseInt(st.nextToken());
	    r = Integer.parseInt(st.nextToken());
	    
	    garden = new int[n][m];
	    points = new ArrayList<>();
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j = 0; j < m; ++j) {
	            garden[i][j] = Integer.parseInt(st.nextToken());
	            
	            if(garden[i][j] == 2) {
	                points.add(new int[]{ i, j });
	            }
	        }
	    }

	     dfs(0, new ArrayList<>(), new ArrayList<>());
	     
	     System.out.println(max);
	}
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	public static void dfs(int level, List<int[]> greens, List<int[]> reds) {
	    if(level == points.size()) {
	        if(greens.size() < g || reds.size() < r) return;

	        int[][] green = new int[n][m];
	        int[][] red = new int[n][m];
	        Queue<int[]> q = new LinkedList<>();
	        for(int[] p : greens) {
	            green[p[0]][p[1]] = 1;
	            q.offer(new int[]{ p[0], p[1], 1 });
	        }
	        
	        for(int[] p : reds) {
	            red[p[0]][p[1]] = 1;
	            q.offer(new int[]{ p[0], p[1], 0 });
	        }
	        
	        int flower = 0;
	        while(!q.isEmpty()) {
	            int[] node = q.poll();
	            int x = node[0];
	            int y = node[1];
	            
	            if(green[x][y] == -1 && red[x][y] == -1) continue;
	            
	            boolean isGreen = (node[2] == 1);
	            int time = 0;
	            if(isGreen) time = green[x][y];
	            else time = red[x][y];
	            
	            for(int d = 0; d < 4; ++d) {
	                int nx = x + dx[d];
	                int ny = y + dy[d];
	                
	                if(nx >= 0 && nx < n && ny >= 0 && ny < m && garden[nx][ny] != 0) {
	                    if((isGreen && red[nx][ny] == time+1) || (!isGreen && green[nx][ny] == time+1)) {
	                        flower++;
	                        green[nx][ny] = -1;
	                        red[nx][ny] = -1;
	                        continue;
	                    }
	                    
	                    if(green[nx][ny] == 0 && red[nx][ny] == 0) {
	                        if(isGreen) green[nx][ny] = time + 1;
	                        else red[nx][ny] = time + 1;
	                        
	                        q.offer(new int[]{ nx, ny, isGreen ? 1 : 0 });
	                    }
	                }
	            }
	        }
	        
	        max = Math.max(max, flower);
	        return;
	    }
	    
        // green
        if(greens.size() < g) {
            greens.add(points.get(level));
            dfs(level+1, greens, reds);
            greens.remove(greens.size() - 1);
        }
        
        // red 
        if(reds.size() < r) {
            reds.add(points.get(level));
            dfs(level+1, greens, reds);
            reds.remove(reds.size() - 1);
        }
        
        // nothing
        dfs(level+1, greens, reds);
	}
}