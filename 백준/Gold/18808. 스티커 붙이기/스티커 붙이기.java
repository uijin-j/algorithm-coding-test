/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.io.*;
import java.util.*;

// 12:22 시작!
public class Main
{
    /**
     * 시뮬레이션?
     * 모든 스티커를 하나씩 탐색 O(K) = 100
     * 각 스티커 별로 총 4가지 경우를 탐색 (상하좌우) O(4)
     * 회전된 스티커를 모든 칸을 돌면서 붙일 수 있는지 확인 O(NM*RC) = 160_000
     * Q. 어떻게 90도씩 회전을 할까?
     */
    static int[][] monitor;
    static int n, m, k;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    k = Integer.parseInt(st.nextToken());
	    monitor = new int[n][m];
	    
	    List<Sticker> stickers = new ArrayList<>();
	    for(int i = 0; i < k; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int r = Integer.parseInt(st.nextToken());
	        int c = Integer.parseInt(st.nextToken());
	        int[][] sticker = new int[r][c];
	        int count = 0;
	        for(int j = 0; j < r; ++j) {
	            st = new StringTokenizer(bf.readLine());
	            for(int l = 0; l < c; ++l) {
	                sticker[j][l] = Integer.parseInt(st.nextToken());
	                if(sticker[j][l] == 1) count++; 
	            }
	        }
	        
	        stickers.add(new Sticker(r, c, count, sticker));
	    }
	    
	    int total = 0;
	    int idx = 0;
	    for(Sticker sticker : stickers) {
	        for(int i = 0; i < 4; ++i) {
	            if(i != 0) sticker.turn();
	            boolean done = false;
	            for(int x = 0; x < n - sticker.r + 1; ++x) {
	                for(int y = 0; y < m - sticker.c + 1; ++y) {
	                    if(check(x, y, sticker)) {
	                        put(x, y, sticker);
	                        total += sticker.count;
	                        done = true;
	                        break;
	                    }
	                }
	                if(done) break;
	            }
	            if(done) break;
	        }
	    }
	    
	    System.out.println(total);
	}
	
	public static class Sticker {
        int r, c, count;
        int[][] shape;
        
        public Sticker(int r, int c, int count, int[][] shape) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.shape = shape;
        }
        
        public void turn() {
            int[][] result = new int[c][r];
            for(int i = 0; i < r; ++i) {
                for(int j = 0; j < c; ++j) {
                    result[j][r - i - 1] = shape[i][j];
                }
            }
            
            this.r = result.length;
            this.c = result[0].length;
            this.shape = result;
        }
    }
    
    public static boolean check(int sx, int sy, Sticker sticker) {
        for(int i = 0; i < sticker.r; ++i) {
            for(int j = 0; j < sticker.c; ++j) {
                int x = sx + i;
                int y = sy + j;
                
                if(sticker.shape[i][j] == 0) continue;
                if(monitor[x][y] == 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static void put(int sx, int sy, Sticker sticker) {
        for(int i = 0; i < sticker.r; ++i) {
            for(int j = 0; j < sticker.c; ++j) {
                int x = sx + i;
                int y = sy + j;

                if(sticker.shape[i][j] == 1) monitor[x][y] = 1;
            }
        }
    }
}