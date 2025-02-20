import java.io.*;
import java.util.*;

// 22:15 시작!
public class Main
{
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    int k = Integer.parseInt(st.nextToken());
	    int[] positions = new int[k];
	    
	    st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < k; ++i) {
	        positions[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    // 이분탐색? 1 ~ n
	    // 완탐? O(kCm)
	    int left = 1;
	    int right = n;
	    String answer = "";
	    while(left <= right) {
	        int mid = left + (right - left) / 2;
	        
	        //  mid 이상의 간격으로 갈 수 있는지 확인
	        int count = 1;
	        int before = positions[0];
	        StringBuilder sb = new StringBuilder("1");
	        for(int i = 1; i < k; ++i) {
	            if(positions[i] - before >= mid) {
	                count++;
	                before = positions[i];
	                
	                if(count <= m) sb.append("1");
	                else sb.append("0");
	                
	                continue;
	            }
	            
	            sb.append("0");
	        }
	        
	        if(count >= m) {
	            left = mid + 1;
	            answer = sb.toString();
	        } else {
	            right = mid - 1;
	        }
	    }
	    
	    System.out.println(answer);
	}
}