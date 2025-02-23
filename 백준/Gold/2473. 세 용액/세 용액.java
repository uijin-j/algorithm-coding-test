import java.io.*;
import java.util.*;

// 14:35 시작!
public class Main
{
    /**
     * 1. 완탐? O(nC3) = 5000C3 시간초과 
     * 2. 투 포인터?
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    long[] values = new long[n];
	    
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) {
	        values[i] = Long.parseLong(st.nextToken());
	    }
	    
	    Arrays.sort(values);
	    
	    // 1. 무조건 사용할 용액을 하나 선택하기
	    long closest = Long.MAX_VALUE;
    	long[] answer = new long[3];
	    for(int i = 0; i < n; ++i) {
	        long fix = values[i];
	        
	        int left = 0;
	        int right = n - 1;
    	    while(left < right) {
    	        if(left == i) {
    	            left++;
    	            continue;
    	        }
    	        
    	        if(right == i) {
    	            right--;
    	            continue;
    	        }
    	        
    	        long sum = values[left] + values[right];
    	        if(closest > Math.abs(sum + fix)) {
    	            closest = Math.abs(sum + fix);
    	            answer = new long[]{ fix, values[left], values[right] };
    	        }
    	        
    	        if(sum > fix * -1) {
    	            right--;
    	        } else if(sum < fix * -1) {
    	            left++;
    	        } else {
    	            right--;
    	            left++;
    	        }
    	    }
	    }
	    
	    Arrays.sort(answer);
	    
	    for(int i = 0; i < 3; ++i) {
	        System.out.print(answer[i] + " ");
	    }
	}
}