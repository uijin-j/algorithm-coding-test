import java.util.*;
import java.io.*;

public class Main
{
    // 11:39 START! 11:47 STOP!
    // 13:14 RESTART! 
    /**
     *  완탐? O(4^n) 이고 n의 최댓값이 8이기 때문에 OK
     */
    static int n, m, numOfCctv;
    static int[][] room;
    static int[] selected;
    static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		room = new int[n][m];
	    numOfCctv = 0;
		for(int i = 0; i < n; ++i) {
		    st = new StringTokenizer(bf.readLine());
		    for(int j = 0; j < m; ++j) {
		        room[i][j] = Integer.parseInt(st.nextToken());
		        
		        if(room[i][j] >= 1 && room[i][j] <= 5) {
		            numOfCctv++;
		        }
		    }
		}
		
		selected = new int[numOfCctv]; // 방향 정하기
		dfs(0);
		
		System.out.println(answer);
	}
	
	public static void dfs(int level) {
	    if(level == numOfCctv) {
	        int[][] copy = new int[n][m];
	        for(int i = 0; i < n; ++i) {
	            for(int j = 0; j < m; ++j) {
	               copy[i][j] = room[i][j];
	            }
	        }
	        
	        int idx = 0;
	        for(int i = 0; i < n; ++i) {
	            for(int j = 0; j < m; ++j) {
	               if(copy[i][j] >= 1 && copy[i][j] <= 5) {
	                   check(i, j, selected[idx], copy);
	                   idx++;
	               } 
	            }
	        }
	        
	        int count = 0;
	        for(int i = 0; i < n; ++i) {
	            for(int j = 0; j < m; ++j) {
	               if(copy[i][j] == 0) {
	                   count++;
	               } 
	            }
	        }
	        
	        answer = Math.min(answer, count);
	        return;
	    }
	    
	    for(int i = 0; i < 4; ++i) {
	        selected[level] = i;
	        dfs(level + 1);
	    }
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void check(int x, int y, int d, int[][] room) {
	    int type = room[x][y];
	    if(type == 1) {
	        fill(x, y, d, room);
	        return;
	    }
	    
	    if(type == 2) {
	        fill(x, y, d, room);
	        fill(x, y, (d + 2) % 4, room);
	        return;
	    }
	    
	    if(type == 3) {
	        fill(x, y, d, room);
	        fill(x, y, (d + 1) % 4, room);
	    }
	    
	    if(type == 4) {
	        fill(x, y, d, room);
	        fill(x, y, (d + 1) % 4, room);
	        fill(x, y, (d + 2) % 4, room);
	    }
	    
	    if(type == 5) {
	        fill(x, y, d, room);
	        fill(x, y, (d + 1) % 4, room);
            fill(x, y, (d + 2) % 4, room);
            fill(x, y, (d + 3) % 4, room);
	    }
	}
	
	public static void fill(int x, int y, int d, int[][] room) {
	    int nx = x + dx[d];
        int ny = y + dy[d];
        
        while(nx >= 0 && nx < n && ny >= 0 && ny < m && room[nx][ny] != 6) {
            if(room[nx][ny] == 0) {
                room[nx][ny] = -1;
            }

            x = nx;
            y = ny;
            nx = x + dx[d];
            ny = y + dy[d];
        }
	}
	
}
