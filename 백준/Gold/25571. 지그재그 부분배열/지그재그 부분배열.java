import java.io.*;
import java.util.*;

// 1. 지그재그 부분 배열
public class Main {
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(bf.readLine());
	    
	    StringBuilder sb = new StringBuilder();
	    while(t > 0) {
	        int n = Integer.parseInt(bf.readLine());
	        int[] nums = new int[n];
	        StringTokenizer st = new StringTokenizer(bf.readLine());
	        for(int i = 0; i < n; ++i) {
	            nums[i] = Integer.parseInt(st.nextToken());
	        }
	        
	        if(n == 1) {
	            sb.append(0).append("\n");
	            t--;
                continue;
	        }
	        
	        // 지그재그 부분배열의 갯수
	        // Step 1. i부터 시작하는 가장 긴 지그재그 수열을 찾아서 count
	        // Step 2. Step 1에서 찾은 가장 긴 지그재그 수열의 마지막 문자열을 i로 업뎃
	        long count = 0;
	        int start = 0;
	        boolean needUp = nums[start+1] - nums[start] > 0;
	        for(int end = 1; end < n; ++end) {
	            int diff = nums[end] - nums[end-1];
	            if(diff == 0) {
	                start = end;
	                continue;
	            }
	            
	            if((diff > 0 && !needUp) || (diff < 0 && needUp)) {
	                // start 땡기기
	                start = end - 1;
	                count++;
	                continue;
	            }
	            
	            // start 부터 end 까지 추가
	            count += end - start;
	            needUp = !needUp;
	        }
	        
	        sb.append(count).append("\n");
	   
	        t--;
	    }
	    
	    System.out.println(sb);
	}
}