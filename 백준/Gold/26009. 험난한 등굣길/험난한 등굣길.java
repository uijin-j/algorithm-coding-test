import java.io.*;
import java.util.*;

// 17:10 시작!
public class Main
{
    /**
     * 모든 k에 대해 정체 되는 칸을 구하고, bfs?
     */
    static boolean[][] map;
    static int[][] check; 
    static int n, m;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    map = new boolean[n+1][m+1];
	    check = new int[n+1][m+1];
	    for(int i = 0; i <= n; ++i) {
	        Arrays.fill(check[i], -1);
	    }
	    
	    int k = Integer.parseInt(bf.readLine());
	    int[][] regions = new int[k][3];
	    for(int i = 0; i < k; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int x = Integer.parseInt(st.nextToken());
	        int y = Integer.parseInt(st.nextToken());
	        int dist = Integer.parseInt(st.nextToken());
	        
	        regions[i] = new int[]{x, y, dist};
	    }
	    
	    Arrays.sort(regions, (a, b) -> b[2] - a[2]);
	    
	     for(int i = 0; i < k; ++i) {
	        mark(regions[i][0], regions[i][1], regions[i][2]);   
	     }
	    
	    Queue<int[]> q = new LinkedList<>();
	    q.offer(new int[]{1, 1});
	    boolean possible = false;
	    int level = 0;
	    while(!q.isEmpty()) {
	        int size = q.size();
	        for(int i = 0; i < size; ++i) {
	            int[] p = q.poll();
	            
	            for(int d = 0; d < 4; ++d) {
	                int nx = p[0] + dx[d];
	                int ny = p[1] + dy[d];
	                if(nx >= 1 && nx <= n && ny >= 1 && ny <= m && !map[nx][ny]) {
	                    if(nx == n && ny == m) {
	                        System.out.println("YES");
	                        System.out.println(level + 1);
	                        return;
	                    }
	                    
	                    q.offer(new int[]{nx, ny});
	                    map[nx][ny] = true;
	                }
	            }
	        }
	        
	        level++;
	    }
	    
	    System.out.println("NO");
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void mark(int x, int y, int dist) {
	    if(check[x][y] > dist) return; // 이미 마킹 했으면
	    
	    Queue<int[]> q = new LinkedList<>();
	    q.offer(new int[]{x, y});
	    map[x][y] = true;
	    check[x][y] = dist;
	    
	    int level = 0;
	    while(!q.isEmpty()) {
	        if(level == dist) break;
	        
	        int size = q.size();
	        for(int i = 0; i < size; ++i) {
	            int[] p = q.poll();
	            
	            for(int d = 0; d < 4; ++d) {
	                int nx = p[0] + dx[d];
	                int ny = p[1] + dy[d];
	                if(nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
	                    if(check[nx][ny] >= dist - (level + 1)) continue;
	                    check[nx][ny] = dist - (level + 1);
	                    map[nx][ny] = true;
	                    q.offer(new int[]{nx, ny});
	                }
	            }
	        }
	        
	        ++level;
	    }
	}
}