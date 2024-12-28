import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    int half = m / 2;
	    
	    long[][] map = new long[n][n];
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j = 0; j < n; ++j) {
	            map[i][j] = -Long.parseLong(st.nextToken());
	        }
	    }
	    
	    long[][] answer = new long[n][n];
	    long[][] sum = new long[n-m+2][n-m+2];
	    for(int i = 1; i <= n-m+1; ++i) {
	        for(int j = 1; j <= n-m+1; ++j) {
	            answer[i+half-1][j+half-1] = map[i-1][j-1] - sum[i-1][j] - sum[i][j-1] + sum[i-1][j-1];
	            if(i >= m + 1) answer[i+half-1][j+half-1] += sum[i-m][j];
	            if(j >= m + 1) answer[i+half-1][j+half-1] += sum[i][j-m];
	            if(i >= m + 1 && j >= m + 1) answer[i+half-1][j+half-1] -= sum[i-m][j-m];
	            
	            sum[i][j] = answer[i+half-1][j+half-1] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
	        }
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < n; ++i) {
    	    for(int j = 0; j < n; ++j) {
    	        sb.append(answer[i][j]).append(" ");
    	    }
    	    sb.append("\n");
    	}
    	
    	System.out.print(sb);
	}
	
}