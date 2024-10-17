import java.io.*;
import java.util.*;

/**
 *  20:00 시작!
 */
public class Main
{
    static char[][] snapshot;
    static Deque<Character>[] map;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        map = new Deque[6];
        for(int i = 0; i < 6; ++i) map[i] = new ArrayDeque<>();
        
	    snapshot = new char[6][12]; // map[i][j]는 i열의 j행 아래가 0행!
	    for(int i = 0; i < 12; ++i) {
	        String line = bf.readLine();
	        char[] chars = line.toCharArray();
	        for(int j = 0; j < 6; ++j) {
	            snapshot[j][i] = chars[j];
	            map[j].offer(chars[j]);
	        }
	    }
	    
	    int count = 0;
	    while(hasBomb()) { // 터질 뿌요들이 있다면?
	        bomb();
	        count++;
	    }
	    
	    System.out.println(count);
	}
	
	static boolean[][] toRemove;
	static boolean[][] visit;
	public static boolean hasBomb() {
	   // System.out.println("Print Snapshot");
	   // for(int i = 11; i >= 0; --i) {
	   //     for(int j = 0; j < 6; ++j) {
	   //         System.out.print(snapshot[j][i] + " ");
	   //     }
	   //     System.out.println();
	   // }
	    
	    boolean hasBomb = false;
	    toRemove = new boolean[6][12]; // 제거해야 하는 뿌요
	    visit = new boolean[6][12];
	    
	    for(int i = 0; i < 6; ++i) {
	        for(int j = 0; j < 12; ++j) {
	            if(snapshot[i][j] == '.' || visit[i][j]) continue;
	            
	            boolean[][] connect = new boolean[6][12];
	            int count = check(i, j, connect); // 얘랑 연결된 애들이 4개 이상인지 확인!
	            if(count >= 4) {
	                hasBomb = true;
	                mark(connect, toRemove);
	            }
	        }
	    }
	    
	    return hasBomb;
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static int check(int col, int row, boolean[][] connect) {
	    connect[col][row] = true;
	    visit[col][row] = true;
	    
	    int total = 1;
	    for(int d = 0; d < 4; ++d) {
	        int nCol = col + dx[d];
	        int nRow = row + dy[d];
	        
	        if(nCol >= 0 && nCol < 6 && nRow >= 0 && nRow < 12 
	            && !connect[nCol][nRow] && snapshot[nCol][nRow] == snapshot[col][row]) {
	            total += check(nCol, nRow, connect);
	        }
	    }
	    
	    return total;
	}
	
	public static void mark(boolean[][] connect, boolean[][] toRemove) {
	    for(int i = 0; i < 6; ++i) {
	        for(int j = 0; j < 12; ++j) {
	            if(connect[i][j]) {
	                toRemove[i][j] = true;
	            }
	        }
	    }
	}
	
	public static void bomb() {
	    for(int i = 0; i < 6; ++i) {
	        Deque<Character> deque = new ArrayDeque<>();
	        for(int j = 0; j < 12; ++j) {
	            Character puyo = map[i].poll();
	            if(!toRemove[i][j]) {
	                deque.offer(puyo);
	            }
	        }
	        
	        while(deque.size() < 12) {
	            deque.offerFirst('.');
	        }
	        
	        for(int j = 0; j < 12; ++j) {
	            Character puyo = deque.poll();
	            snapshot[i][j] = puyo;
	            deque.offer(puyo);
	        }
	        
	        map[i] = deque;
	    }
	}
}