import java.io.*;
import java.util.*;

// 19:43 시작! 20:57 스탑!
// 23:03 재시작!
public class Main {
    /**
     * 더러운 방을 모두 깨끗한 칸으로 만드는데 필요한 최소 이동 횟수
     * 항상 최단거리만 가도 될까? (가장 가까운 칸부터 치우기)
     * - 더러운 칸을 방문하는 순서 구하기 (10!) 12_000_000 * 실제로 이동 해보기 400 = 4_800_000_000
     */
    static int w, h;
    static int[][] room, dist;
    static boolean[][] visit;
    static int[] fromRobbot;
    static boolean[] check;
    static List<Point> durty;
    static int numOfDurty;
    static Point robot;
    static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    while(true) {
	        st = new StringTokenizer(bf.readLine());
	        w = Integer.parseInt(st.nextToken());
	        h = Integer.parseInt(st.nextToken());
	        
	        if(w == 0 && h == 0) break;
	       
	        min = Integer.MAX_VALUE;
	        room = new int[h][w];
	        durty = new ArrayList<>();
	        robot = new Point(-1, -1);
	        for(int i = 0; i < h; ++i) {
	            int j = 0;
	            for(char ch : bf.readLine().toCharArray()) {
	                if(ch == '.') {
	                    room[i][j++] = -1;
	                    continue;
	                }
	                
	                if(ch == 'x') {
	                    room[i][j++] = -2;
	                    continue;
	                }
	                
	                if(ch == '*') {
	                    durty.add(new Point(i, j));
	                    room[i][j++] = durty.size() - 1;
	                    continue;
	                }
	                
	                if(ch == 'o') {
	                    robot = new Point(i, j);
	                    room[i][j++] = -1;
	                    continue;
	                }
	            }
	        }
	        
	        numOfDurty = durty.size();
	        
	        if(numOfDurty == 0) {
	            sb.append(0).append("\n");
	            continue;
	        }
	        
	        dist = new int[numOfDurty][numOfDurty];
	        for(int i = 0; i < numOfDurty; ++i) {
	            Arrays.fill(dist[i], Integer.MAX_VALUE);
	            visit = new boolean[h][w];
	            visit[durty.get(i).x][durty.get(i).y] = true;
	            findDist(dist[i], durty.get(i).x, durty.get(i).y);
	        }
	        
	        fromRobbot = new int[numOfDurty];
	        Arrays.fill(fromRobbot, Integer.MAX_VALUE);
	        visit = new boolean[h][w];
	        visit[robot.x][robot.y] = true;
	        findDist(fromRobbot, robot.x, robot.y);
	        
	        boolean reachable = true;
	        for(int i = 0; i < numOfDurty; ++i) {
	            if(fromRobbot[i] == Integer.MAX_VALUE) {
	                reachable = false;
	            }
	        }
	        
	        if(!reachable) {
	            sb.append(-1).append("\n");
	            continue;
	        }
	        
	        check = new boolean[numOfDurty];
	        makeOrder(0, -1, 0);
	        
	        if(min == Integer.MAX_VALUE) sb.append(-1);
	        else sb.append(min);
	        
	        sb.append("\n");
	    }
	    
	    System.out.println(sb);
	}
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	public static void findDist(int[] dist, int sx, int sy) {
	    Queue<Point> q = new LinkedList<>();
	    visit[sx][sy] = true;
	    q.offer(new Point(sx, sy));
	    
	    int level = 1;
	    while(!q.isEmpty()) {
	        int size = q.size();
	        
	        for(int i = 0; i < size; ++i) {
	            Point p = q.poll();
	            
	            for(int d = 0; d < 4; ++d) {
	                int nx = p.x + dx[d];
	                int ny = p.y + dy[d];
	                
	                if(nx >= 0 && nx < h && ny >= 0 && ny < w && room[nx][ny] != -2 && !visit[nx][ny]) {
	                    visit[nx][ny] = true;
	                    
	                    if(room[nx][ny] >= 0) {
        	                dist[room[nx][ny]] = level;
        	            }
	            
	                    q.offer(new Point(nx, ny));
	                }
	            }
	            
	        }
	        
	        level++;
	    }
	}
	
	public static void makeOrder(int level, int before, int sum) {
	    if(level == durty.size()) {
	        min = Math.min(min, sum);
	        return;
	    }
	    
	    for(int i = 0; i < durty.size(); ++i) {
	        if(check[i]) continue;
            check[i] = true;
	        makeOrder(level + 1, i,  (before == -1) ? fromRobbot[i] : sum + dist[before][i]);
            check[i] = false;
	    }
	}
	
	public static class Point {
	    int x, y;
	    
	    public Point(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}
}