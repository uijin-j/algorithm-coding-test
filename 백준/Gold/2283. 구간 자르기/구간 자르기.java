import java.io.*;
import java.util.*;

// 20:00 시작!
public class Main
{
    /**
     * 카카오 문제 중.. 유튜브 광고 위치 정하는 문제랑 비슷한 것 같음!
     * 0 8
     * 0 10
     * 3 10
     * 3 15
     * 4 25
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int k = Integer.parseInt(st.nextToken());
	    int[] ranges = new int[1_000_002];
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        int to = Integer.parseInt(st.nextToken());
	        
	        ranges[from + 1] += 1;
	        ranges[to + 1] -= 1;
	    }
	    
	    int[] sum = new int[1_000_001];
	    for(int i = 1; i < 1_000_001; ++i) {
	        ranges[i] += ranges[i-1];
	        sum[i] += sum[i-1] + ranges[i];
	    }
	    
	    int a = 0, b = 1;
	    while(b < 1_000_001) {
	        int total = sum[b] - sum[a];
	        if(total == k) {
	            System.out.println(a + " " + b);
	            return;
	        } 
	        
	        if(total < k) b++;
	        else a++;
	    }
	    
	    System.out.println("0 0");
	}
}