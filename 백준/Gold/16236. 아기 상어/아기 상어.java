import java.io.*;
import java.util.*;

/**
 * 23:20 시작! 
 */
public class Main
{
    /**
     * 20 X 20 = 400 칸이기 때문에 O(n^2)도 가능 
     */
    static int[] dx = { -1, 0, 0, 1 };
    static int[] dy = { 0, -1, 1, 0 };
    static int n, t;
    static int[][] map;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    map = new int[n][n];
	    int[] pos = new int[2];
	    StringTokenizer st;
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j = 0; j < n; ++j) {
	            map[i][j] = Integer.parseInt(st.nextToken());
	            if(map[i][j] == 9) {
	                map[i][j] = 0;
	                pos = new int[]{ i, j };
	            }
	        }
	    }
	   
	    int size = 2;
	    int cnt = 0;
	    while(true) {
	        int[] next = bfs(pos, size);
	        if(next[0] == -1) break;
	        map[next[0]][next[1]] = 0;
	        pos = next;
	        cnt++;
	        if(size == cnt) {
	            size++;
	            cnt = 0;
	        }
	    }
	    
	    System.out.println(t);
	}
	
	public static int[] bfs(int[] pos, int size) {
	    boolean[][] visit = new boolean[n][n];
	    Queue<int[]> q = new LinkedList<>();
	    visit[pos[0]][pos[1]] = true;
	    q.offer(new int[]{pos[0], pos[1]});
	    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0]-b[0] == 0 ? a[1]-b[1] : a[0]-b[0]);
	    int time = 0;
	    while(!q.isEmpty()) {
	        int len = q.size();
	        for(int i = 0; i < len; ++i) {
	            int[] cur = q.poll();
	            for(int d = 0; d < 4; ++d) {
	                int nx = cur[0] + dx[d];
	                int ny = cur[1] + dy[d];
	                if(nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] <= size && !visit[nx][ny]) {
	                    if(map[nx][ny] > 0 && map[nx][ny] < size) {
	                        pq.offer(new int[]{nx, ny});
	                    }
	                    
	                    visit[nx][ny] = true;
	                    q.offer(new int[]{nx, ny});
	                }
	            }
	        }
	        
	        if(!pq.isEmpty()) {
	            t += time+1;
	            return pq.poll();
	        }
	        
	        time++;
	    }
	    
	    return new int[]{-1, -1};
	}
}