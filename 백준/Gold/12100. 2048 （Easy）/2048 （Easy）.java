import java.io.*;
import java.util.*;

/**
 * 11:57 START! 12:33 STOP!
 * 16:17 RE!
 */
public class Main
{
    /**
     * 완전탐색 DFS O(4^5*N^2) ~= O(1024N^2) ~= O(N^2) ~= 400
     */
    static int N;
    static int answer = 0;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(bf.readLine());
	    int[][] board = new int[N][N];
	    
	    StringTokenizer st;
	    for(int i = 0; i < N; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j = 0; j < N; ++j) {
	            board[i][j] = Integer.parseInt(st.nextToken());
	        }
	    }
	    
	    dfs(0, board);
	    
	    System.out.println(answer);
	}
	
	public static void dfs(int level, int[][] board) {
	    if(level == 5) {
	        int max = 0;
	        for(int i = 0; i < N; ++i) {
	            for(int j = 0; j < N; ++j) {
	                max = Math.max(max, board[i][j]);
	            }
	        }
	        
	        answer = Math.max(answer, max);
	        return;
	    }
	    
	    // 상
	    int[][] next = up(board);
	   // if(level == 0) {
    // 	    System.out.println("Step " + level + " UP");
    // 	    for(int i = 0; i < N; ++i) {
    // 	        for(int j = 0; j < N; ++j) {
    // 	            System.out.print(next[i][j] + " ");
    // 	        }
    // 	        System.out.println();
    // 	    }
	   // }
	    dfs(level + 1, next);
	    
	    // 하
	    next = down(board);
	   // if(level == 0) {
	   //     System.out.println("Step " + level + " DOWN");
    // 	    for(int i = 0; i < N; ++i) {
    // 	        for(int j = 0; j < N; ++j) {
    // 	            System.out.print(next[i][j] + " ");
    // 	        }
    // 	        System.out.println();
    // 	    }   
	   // }
	    dfs(level + 1, next);
	    
	    // 좌
	    next = left(board);
	   // if(level == 0) {
    // 	    System.out.println("Step " + level + " LEFT");
    // 	    for(int i = 0; i < N; ++i) {
    // 	        for(int j = 0; j < N; ++j) {
    // 	            System.out.print(next[i][j] + " ");
    // 	        }
    // 	        System.out.println();
    // 	    }
	   // }
	    dfs(level + 1, next);
	    
	    // 우
	    next = right(board);
	   // if(level == 0) {
	   //     System.out.println("Step " + level + " RIGHT");
    // 	    for(int i = 0; i < N; ++i) {
    // 	        for(int j = 0; j < N; ++j) {
    // 	            System.out.print(next[i][j] + " ");
    // 	        }
    // 	        System.out.println();
    // 	    }
	   // }
	    dfs(level + 1, next);
	}
	
	public static int[][] up(int[][] board) {
	    int[][] result = new int[N][N];
	    boolean[][] merged = new boolean[N][N];
	    for(int i = 0; i < N; ++i) { // i는 col
	        int idx = 0;
	        for(int j = 0; j < N; ++j) { // j는 row
	            if(board[j][i] != 0) {
	                if(idx != 0 && !merged[idx - 1][i] && board[j][i] == result[idx - 1][i]) {
	                    result[idx - 1][i] += board[j][i];
	                    merged[idx - 1][i] = true;
	                } else {
	                    result[idx++][i] = board[j][i];
	                }
	            }
	        }
	    }
	    
	    return result;
	}
	
	public static int[][] down(int[][] board) {
	    int[][] result = new int[N][N];
	    boolean[][] merged = new boolean[N][N];
	    for(int i = 0; i < N; ++i) { // i는 col
	        int idx = N - 1;
	        for(int j = N - 1; j >= 0; --j) { // j는 row
	            if(board[j][i] != 0) {
	                if(idx != N - 1 && !merged[idx + 1][i] && board[j][i] == result[idx + 1][i]) {
	                    result[idx + 1][i] += board[j][i];
	                    merged[idx + 1][i] = true;
	                } else {
	                    result[idx--][i] = board[j][i];
	                }
	            }
	        }
	    }
	    
	    return result;
	}
	
	public static int[][] left(int[][] board) {
	    int[][] result = new int[N][N];
	    boolean[][] merged = new boolean[N][N];
	    for(int i = 0; i < N; ++i) { // i는 row
	        int idx = 0;
	        for(int j = 0; j < N; ++j) { // j는 col
	            if(board[i][j] != 0) {
	                if(idx != 0 && !merged[i][idx - 1] && board[i][j] == result[i][idx - 1]) {
	                    result[i][idx - 1] += board[i][j];
	                    merged[i][idx - 1] = true;
	                } else {
	                    result[i][idx++] = board[i][j];
	                }
	            }
	        }
	    }
	    
	    return result;
	}
	
	public static int[][] right(int[][] board) {
	    int[][] result = new int[N][N];
	    boolean[][] merged = new boolean[N][N];
	    for(int i = 0; i < N; ++i) { // i는 row
	        int idx = N - 1;
	        for(int j = N - 1; j >= 0; --j) { // j는 col
	            if(board[i][j] != 0) {
	                if(idx != N - 1 && !merged[i][idx + 1] && board[i][j] == result[i][idx + 1]) {
	                    result[i][idx + 1] += board[i][j];
	                    merged[i][idx + 1] = true;
	                } else {
	                    result[i][idx--] = board[i][j];
	                }
	            }
	        }
	    }
	    
	    return result;
	}
}