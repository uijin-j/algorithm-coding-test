import java.util.*;
import java.io.*;

/**
 * 15:24 START 
 */
public class Main
{
    /**
     *  N개의 앱이 있음 (N은 최대 100)
     *  각 앱은 m1 m2 .. mn 메모리를 차지하고 있음 (해당 앱을 제거했을 때 이득)
     *  각 앱은 c1 c2 .. cn 의 재시작 시간이 걸림 (해당 앱을 제거했을 때 손실)
     * 
     *  적절하게 앱을 선택해서 최소 손실로 M 이상의 메모리를 확보하자. 
     * 
     *  풀이. 이득과 손실이 있기 때문에 배낭 문제?
     *   - but 같은 앱을 여러 개 선택 X (토스랑 다른 점)
     *   - 딱 M이 아니라 M 이상을 맞추면 됨
     */
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		int[] memory = new int[n];
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < n; ++i) memory[i] = Integer.valueOf(st.nextToken());
		
		int[] cost = new int[n];
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < n; ++i) cost[i] = Integer.valueOf(st.nextToken());
		
		int[] dp = new int[m+1]; // dp[i]는 i 메모리를 확보하기 위한 최소 비용
		Arrays.fill(dp, 10001);
		dp[0] = 0;
		
		for(int app = 0; app < n; ++app) {
		    int appMemory = Math.min(memory[app], m);
		    int appCost = cost[app];
		    for(int mem = m + appMemory; mem > appMemory; --mem) {
		        int idx = Math.min(mem, m);
		        dp[idx] = Math.min(dp[idx], appCost + dp[mem - appMemory]);
		    }
		    
		    dp[appMemory] = Math.min(dp[appMemory], appCost);
		}
		
		System.out.println(dp[m]);
	}
}