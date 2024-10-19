import java.io.*;
import java.util.*;

/**
 *  16:00 START!
 */
public class Main
{
    /**
     * 시뮬레이션 문제?
     * 각 플레이어의 차례마다 확장할 수 있으면 확장 O(nm)
     * 더 이상 확장할 수 없다면 멈추기
     */
    static int N, M, P;
    static int[][] board;
    static boolean[][] visit;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    P = Integer.parseInt(st.nextToken());
	    
	    int[] size = new int[P];
	    st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < P; ++i) size[i] = Integer.parseInt(st.nextToken());
	    
	    int[] score = new int[P];
	    board = new int[N][M];
	    visit = new boolean[N][M];
	    for(int i = 0; i < N; ++i) {
	        String line = bf.readLine();
	        
	        int j = 0;
	        for(char ch : line.toCharArray()) {
	            if(ch == '.') {
	                board[i][j] = -1;
	            } else if(ch == '#') {
	                visit[i][j] = true;
	            } else if(ch != '.') {
	                int player = ch - '0';
	                board[i][j] = player - 1;
	                score[player - 1]++;
	            }
	            
	            j++;
	        }
	    }
	    
	    int turn = 0; // 0번 플레이어부터 시작
	    boolean[] stop = new boolean[P]; // 더이상 움직일 수 없는 플레이어 check
	    int stopCount = 0;
	    while(stopCount < P) {
	        if(!stop[turn]) {
	            int count = extend(turn, size[turn]); // 확장된 크기를 반환
	            score[turn] += count;
	            if(count == 0) {
	                stop[turn] = true;
	                stopCount++;
	            }
	        }
	        
	       // System.out.println(turn + " turn!");
	       // for(int i = 0; i < N; ++i) {
    	   //     for(int j = 0; j < M; ++j) {
    	   //         System.out.print(board[i][j]);
    	   //     }
    	   //     System.out.println();
    	   // }
	        
	        turn = (turn + 1) % P;
	    }
	    
	    for(int s : score) System.out.print(s + " ");
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static int extend(int player, int size) {
	    int count = 0;
	    
	    // dfs
	    Queue<int[]> q = new LinkedList<>();
	    for(int i = 0; i < N; ++i) {
	        for(int j = 0; j < M; ++j) {
	            if(board[i][j] == player && !visit[i][j]) {
	               visit[i][j] = true;
	               q.offer(new int[]{i, j, 0});
	            }
	        }
	    }
	   
	    boolean[][] check = new boolean[N][M];
	    while(!q.isEmpty()) {
	        int[] node = q.poll();
	        int x = node[0];
	        int y = node[1];
	        int level = node[2];
	        
	        for(int d = 0; d < 4; ++d) {
	            int nx = x + dx[d];
	            int ny = y + dy[d];
	            
	            if(nx >= 0 && nx < N && ny >= 0 && ny < M 
	                && board[nx][ny] == -1 && !visit[nx][ny] && !check[nx][ny]) {
	                check[nx][ny] = true;
	                board[nx][ny] = board[x][y];
	                count++;
	                if(level + 1 == size) continue; // 더 이상 확장 불가능하므로 큐에 넣기 않음!
	                q.offer(new int[]{nx, ny, level + 1});
	            }
	        }
	    }
	    
	    return count;
	}
}