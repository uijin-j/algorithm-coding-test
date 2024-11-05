import java.io.*;
import java.util.*;

/**
 * 11:03
 */
public class Main
{
    /**
     * 1 3 66 7 9
     * 3 6 7 8 10 12 14 15 18 20  
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   
	    int n = Integer.parseInt(bf.readLine());
	    int k = Integer.parseInt(bf.readLine());
	    int[] sensors = new int[n];
	    
	    StringTokenizer st = new StringTokenizer(bf.readLine()); 
	    for(int i = 0; i < n; ++i) {
	        sensors[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    Arrays.sort(sensors);
	    
	    int[] gaps = new int[n-1];
	    for(int i = 1; i < n; ++i) {
	        gaps[i-1] = sensors[i] - sensors[i-1];
	    }
	    
	    Arrays.sort(gaps);
	    
	     int total = sensors[n-1] - sensors[0];
	    for(int i = n - 2; i >= 0; --i) {
	        if(k == 1) break;
	        total -= gaps[i];
	        k--;
	    }
	    
	    System.out.println(total);
	}
}