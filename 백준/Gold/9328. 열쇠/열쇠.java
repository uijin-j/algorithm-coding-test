import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// 20:05 시작!
public class Main
{
    static int h, w, count;
    static char[][] map;
    static boolean[][] visit;
    static Set<Character> keys;
    static Map<Character, List<Integer>> doors;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(bf.readLine());
	    
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    while(t > 0) {
	        st = new StringTokenizer(bf.readLine());
	        h = Integer.parseInt(st.nextToken());
	        w = Integer.parseInt(st.nextToken());
	        count = 0;
	        visit = new boolean[h][w];
	        doors = new ConcurrentHashMap<>();
	        
	        map = new char[h][w];
	        for(int i = 0; i < h; ++i) {
	            map[i] = bf.readLine().toCharArray();
	        }
	        
	        keys = new HashSet<>();
	        for(char key : bf.readLine().toCharArray()) {
	            if(key == '0') break;
	            keys.add(key);
	        }
	        
	        // 테두리 탐색
	        int[] xs = new int[]{ 0, h - 1 };
	        for(int y = 0; y < w; ++y) {
	            for(int i = 0; i < 2; ++i) {
	                int x = xs[i];
	                
	                if(visit[x][y]) continue;
	                if(map[x][y] == '*') continue;
	                
	                visit[x][y] = true;
	                
	                if(map[x][y] >= 'A' && map[x][y] <= 'Z'
	                    && !keys.contains(Character.toLowerCase(map[x][y]))) {
	                    doors.putIfAbsent(map[x][y], new ArrayList<>());
	                    doors.get(map[x][y]).add(getNum(x, y));
	                    continue;
	                }
	                
	                if(map[x][y] == '$') {
	                    count++;
	                }
	                
	                if(map[x][y] >= 'a' && map[x][y] <= 'z') {
	                    keys.add(map[x][y]);
	                }
	                
	                search(x, y);
	            }
	        }
	        
	        int[] ys = new int[]{ 0, w - 1 };
	        for(int x = 1; x < h - 1; ++x) {
	            for(int i = 0; i < 2; ++i) {
	                int y = ys[i];
	                
	                if(visit[x][y]) continue;
	                if(map[x][y] == '*') continue;
	                
	                visit[x][y] = true;
	                
	                if(map[x][y] >= 'A' && map[x][y] <= 'Z'
	                    && !keys.contains(Character.toLowerCase(map[x][y]))) {
	                    doors.putIfAbsent(map[x][y], new ArrayList<>());
	                    doors.get(map[x][y]).add(getNum(x, y));
	                    continue;
	                }
	                
	                if(map[x][y] == '$') {
	                    count++;
	                }
	                
	                if(map[x][y] >= 'a' && map[x][y] <= 'z') {
	                    keys.add(map[x][y]);
	                }
	                
	                search(x, y);
	            }
	        }
	        
	        Set<Character> already = new HashSet<>();
	        List<Character> list;
	        while(keys.size() > already.size()) {
	            list = new ArrayList<>();
	            for(char key : keys) {
	                if(already.contains(key)) continue;
	                list.add(key);
	                already.add(key);
	            }
	            
	            for(char key: list) {
	                for(int door : doors.getOrDefault(Character.toUpperCase(key), new ArrayList<>())) {
	                    int[] point = getPoint(door);
	                    search(point[0], point[1]);
	                }
	            }
	        }
	        
	        sb.append(count).append("\n");
	        
	        --t;
	    }
	    
	    System.out.println(sb);
	}
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	public static void search(int x, int y) {
        for(int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= 0 && nx < h && ny >= 0 && ny < w && !visit[nx][ny]) {
                
                if(map[nx][ny] == '*') continue;
                
                visit[nx][ny] = true;
                
                if(map[nx][ny] >= 'A' && map[nx][ny] <= 'Z'
                    && !keys.contains(Character.toLowerCase(map[nx][ny]))) {
                    doors.putIfAbsent(map[nx][ny], new ArrayList<>());
                    doors.get(map[nx][ny]).add(getNum(nx, ny));
                    continue;
                }
                
                if(map[nx][ny] == '$') {
                    count++;
                }
                
                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
                    keys.add(map[nx][ny]);
                }

                search(nx, ny);
            }
        }
	}

	public static int getNum(int x, int y) {
	    return x * w + y;
	}
	
	public static int[] getPoint(int num) {
	    int x = num / w;
	    int y = num - (x * w);
	    return new int[]{ x, y };
	}
}