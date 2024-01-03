import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int r = Integer.parseInt(st.nextToken());
	    int c = Integer.parseInt(st.nextToken());
	    
	    char[][] chars = new char[c][r];
	    
	    for(int i = 0; i < r; ++i) {
	        String s = bf.readLine();
	        for(int j = 0; j < c; ++j) {
	            chars[j][i] = s.charAt(j);
	        }
	    }
	    
	    int count = 0;
	    for(int i = 1; i < r; ++i) {
	        Set<String> set = new HashSet<>();
	        
	        for(int j = 0; j < c; ++j) {
	            String s = String.valueOf(chars[j]).substring(i);
	            
	            if(set.contains(s)) {
	                System.out.println(count);
	                return;
	            }
	            
	            set.add(s);
	        }
	        
	        count++;
	    }
	    
	    
	    System.out.println(count);
	}
}
