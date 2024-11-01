import java.io.*;
import java.util.*;

/**
 * 14:32 시작!
 */
public class Main
{
    /*
     * 1 2 10 : 1번에서 +10
     * 1 3 20 : 1-2번에서 +20 
     * 2 3 10 : 2번에서 +10
     * 1 4 30 : 1-3번에서 +10
     * 2 4 20 
     * 3 4 20 : 3번에서 +20
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    
	    int n = Integer.parseInt(st.nextToken());
	    int c = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(bf.readLine());
	    
	    int[][] info = new int[m][3];
	    for(int i = 0; i < m; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        info[i][0] = Integer.parseInt(st.nextToken());
	        info[i][1] = Integer.parseInt(st.nextToken());
	        info[i][2] = Integer.parseInt(st.nextToken());
	    }
	    
	    Arrays.sort(info, (a, b) -> a[1] - b[1] == 0 ? a[0] - b[0] : a[1] - b[1]);
	    
	    int[] remain = new int[n+1];
	    Arrays.fill(remain, c);
	    
	    int count = 0;
	    for(int i = 0; i < m; ++i) {
	        int from = info[i][0];
	        int to = info[i][1];
	        int cnt = info[i][2];

	        for(int j = from; j < to; ++j) {
	            cnt = Math.min(cnt, remain[j]);
	        }
	        
	        if(cnt == 0) continue;
	        
	        for(int j = from; j < to; ++j) {
	            remain[j] -= cnt;
	        }
	        
	        count += cnt;
	    }
	    
	    System.out.println(count);
	}
}