import java.io.*;
import java.util.*;

// 14:16 시작!
public class Main
{
    /**
     * 식탁은 직사각형 모양
     * 식탁에 앉을 수 있는 사람의 수는 식탁의 둘레
     * 조건) 식탁은 항상 아파트의 변에 평행하게
     * 즉, 둘레가 가장 큰 직사각형을 구하기!
     * 0 1 0 0
     * 0 2 1 0
     * 2 1 0 1
     * 2 1 0 0
     * 
     * 0 1 0 0
     * 0 2 1 0
     * 1 3 0 1
     * 2 4 0 0
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int r = Integer.parseInt(st.nextToken());
	    int c = Integer.parseInt(st.nextToken());
	    boolean[][] apt = new boolean[r][c];
	    int[][] sum = new int[r][c];
	    
	    for(int i = 0; i < r; ++i) {
	        String line = bf.readLine();
	        for(int j = c - 1; j >= 0; --j) {
	            char ch = line.charAt(j);
	            apt[i][j] = (ch == '.');
	            if(ch == 'X') {
	                sum[i][j] = 0;
	            } else {
	                if(j == c - 1) sum[i][j] = 1;
	                else sum[i][j] = sum[i][j + 1] + 1;
	            }
	        }
	    }
	    
	    int max = 0;
	    for(int i = 0; i < r; ++i) {
	        for(int j = 0; j < c; ++j) {
	            if(!apt[i][j]) continue;
	            int width = sum[i][j];
	            int height = 1;
	            max = Math.max(max, (height + width) * 2);
	            for(int k = i + 1; k < r; ++k) {
	                if(!apt[k][j]) break;
	                width = Math.min(width, sum[k][j]);
	                height += 1;
	                max = Math.max(max, (height + width) * 2);
	            }
	        }
	    }
	    
	    max = Math.max(max - 1, 0);
	    System.out.println(max);
	}
}