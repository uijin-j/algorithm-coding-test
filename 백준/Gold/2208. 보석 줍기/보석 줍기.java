import java.io.*;
import java.util.*;

// 17:18 시작
public class Main
{
    /**
     * 최대 가치로 보석을 훔치기
     * N에의 보석 중 연속된 M개 이상의 보석을 훔칠 수 있음
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken()); // 최대 100_000
	    int m = Integer.parseInt(st.nextToken());
	    
	    int[] sum = new int[n+1];
	    int[] nums = new int[n+1];
	    for(int i = 1; i <= n; ++i) {
	        nums[i] = Integer.parseInt(bf.readLine());
	        sum[i] = sum[i-1] + nums[i];
	    }
	    
	    int max = 0;
	    int bestCost = 0;
	    for(int to = m; to <= n; ++to) {
	        bestCost = Math.min(bestCost, sum[to - m]);
	        max = Math.max(max, sum[to] - bestCost);
	    }
	    
	    System.out.println(max);
	}
}