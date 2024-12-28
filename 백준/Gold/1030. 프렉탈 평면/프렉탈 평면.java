import java.io.*;
import java.util.*;

// 14:17 
public class Main
{
    static int s, n, k;
    static int[][] after1;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    s = Integer.parseInt(st.nextToken()); // 시간 (최대 10초)
	    n = Integer.parseInt(st.nextToken()); // 확장되는 크기 (최대 8)
	    k = Integer.parseInt(st.nextToken()); // 검정 영역 크기
	    int r1 = Integer.parseInt(st.nextToken()); 
	    int r2 = Integer.parseInt(st.nextToken());
	    int c1 = Integer.parseInt(st.nextToken());
	    int c2 = Integer.parseInt(st.nextToken());
	    
	    after1 = new int[n][n];
	    int x = (n - k) / 2;
	    int y = (n - k) / 2;
	    for(int i = 0; i < k; ++i) {
	        for(int j = 0; j < k; ++j) {
	            after1[x + i][y + j] = 1;
	        }
	    }
	
	    // sol1. 실제로 시뮬레이션? O(8^9 * 8^9) ~= O(2^54) ~= 불가능
	    // sol2. 규칙을 찾자! 
	    // - 각 네모는 1초전 사각형이 n * n개 있음 => 가운데는 all 검정
	    for(int i = r1; i <= r2; ++i) {
	        for(int j = c1; j <= c2; ++j) {
	            System.out.print(findColor(s, i, j));
	        }
	        
	        System.out.println();
	    }
	}
	
	public static int findColor(int s, int x, int y) {
	    if(s == 0) {
	        return 0;
	    }
	    
	    if(s == 1) {
	        return after1[x][y];
	    }

	    int child = (int) Math.pow(n, s - 1);
	    int parentX = x / child;
	    int parentY = y / child;
	    if(after1[parentX][parentY] == 1) {
	        return 1;
	    }

	    return findColor(s - 1, x - parentX * child, y - parentY * child);
	}
	
}