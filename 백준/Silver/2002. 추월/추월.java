import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    Map<String, Integer> in = new HashMap<>();
	    
	    for(int i = 1; i <= n; ++i) {
	        in.put(bf.readLine(), i);
	    }
	    
	    boolean[] check = new boolean[n+1];
	    int cur = 1, count = 0;
	    for(int i = 1; i <= n; ++i) {
	        while(check[cur]) {
	            cur++;
	        }
	        
	        int num = in.get(bf.readLine());
	        check[num] = true;
	        
	        if(num != cur) count++;
	    }
	    
	    System.out.println(count);
	}
}
