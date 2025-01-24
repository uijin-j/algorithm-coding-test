import java.io.*;
import java.util.*;

// 00:13 시작
public class Main {
    /**
     * 두 영역 사이의 거리(d)를 구해야 함! 정답은 (d + 1) / 2
     */
    static List<Point> bound = new ArrayList<>();
    static int[] parent;
    static int r, c;
    static int[][] lake;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    r = Integer.parseInt(st.nextToken()); // 최대 1_500
	    c = Integer.parseInt(st.nextToken()); // 최대 1_500
	    lake = new int[r][c];
	    int[][] point = new int[2][2]; // 백조 위치
	    int idx = 0;
	    for(int i = 0; i < r; ++i) { // O(2_250_000)
	        int j = 0;
	        for(char ch : bf.readLine().toCharArray()) {
	            if(ch == 'X') lake[i][j] = -1;
	            if(ch == 'L') {
	                point[idx++] = new int[]{ i, j };
	            }
	            
	            j++;
	        }
	    }
	    
	    int num = 1;
	    for(int i = 0; i < r; ++i) { 
	        for(int j = 0; j < c; ++j) {
	            if(lake[i][j] == 0) {
	                lake[i][j] = num;
	                fill(i, j, num++);
	            }
	        }
	    }
	    
	    parent = new int[num + 1];
	    for(int i = 0; i <= num; ++i) {
	        parent[i] = i;
	    }
	    
	    int firstX = point[0][0];
	    int firstY = point[0][1];
	    int secondX = point[1][0];
	    int secondY = point[1][1];
	    int count = 0;
	    while(find(lake[firstX][firstY]) != find(lake[secondX][secondY])) {
	        // 1초 후 - 모든 지역의 bound를 넓히기
	        List<Point> newBound = new ArrayList<>();
            for(Point p : bound) {
                spread(p, newBound);   
            }
            
            bound = newBound;
	        count++;
	    }
	    
	    System.out.println(count);
	}
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	public static void fill(int x, int y, int num) {
	    boolean nextToX = false;
	    for(int d = 0; d < 4; ++d) {
	        int nx = x + dx[d];
	        int ny = y + dy[d];
	        
	        if(nx >= 0 && nx < r && ny >= 0 && ny < c) {
	            if(lake[nx][ny] == -1) {
	                nextToX = true;
	                continue;
	            }
	            
	            if(lake[nx][ny] == 0) {
	                lake[nx][ny] = num;
	                fill(nx, ny, num);   
	            }
	        }
	    }
	    
	    if(nextToX) bound.add(new Point(x, y));
	}
	
	public static void spread(Point p, List<Point> list) {
	    for(int d = 0; d < 4; ++d) {
            int nx = p.x + dx[d];
            int ny = p.y + dy[d];

            if(nx >= 0 && nx < r && ny >= 0 && ny < c) {
                if(lake[nx][ny] == -1) {
                    lake[nx][ny] = lake[p.x][p.y];
                    
                    for(int dd = 0; dd < 4; ++dd) {
                        int nnx = nx + dx[dd];
                        int nny = ny + dy[dd];
                        
                        boolean nextToX = false;
                        if(nnx >= 0 && nnx < r && nny >= 0 && nny < c) {
                            if(lake[nnx][nny] == -1) nextToX = true;
                            if(lake[nnx][nny] > 0) {
                                union(lake[nx][ny], lake[nnx][nny]);
                            }
                        }
                        
                        if(nextToX) list.add(new Point(nx, ny));
                    }
                    
                    continue;
                }
                
                if(lake[nx][ny] > 0) {
                    union(lake[p.x][p.y], lake[nx][ny]);
                }
            }
	    }
	}
	
	public static int find(int child) {
	    if(parent[child] == child) return child;
	    return parent[child] = find(parent[child]);
	}
	
	public static void union(int child1, int child2) {
	    int parent1 = find(child1);
	    int parent2 = find(child2);
	    
	    if(parent1 == parent2) return;
	    
	    if(child1 < child2) parent[parent2] = parent1;
	    else parent[parent1] = parent2;
	}
	
	public static class Point {
	    int x, y;
	    
	    public Point(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}
}