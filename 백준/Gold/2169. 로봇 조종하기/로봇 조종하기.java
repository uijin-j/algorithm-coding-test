import java.io.*;
import java.util.*;

// 로봇 조종하기 (13:17 ~ ?) (❗️정답 참고)
public class Main
{
    /**
     * 맵의 크기는 최대 1_000 * 1_000 = 1_000_000
     * (1,1) -> (n, m) 으로 가는 최대 가치 (최단 거리X)
     * 왼, 오, 아래로는 이동O but 위로는 이동X
     * 
     * DFS 또는 BFS를 해야 함
     * -> 항상 가치가 큰 쪽으로 가면 좋을까? NO!
     * -> 모든 경우를 다 확인할 수 있을까?  O(3^nm)
     * -> DP를 사용해보자!
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    
	    int[][] map = new int[n][m];
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j = 0; j < m; ++j) {
	            map[i][j] = Integer.parseInt(st.nextToken());
	        }
	    }
	    
	    int[][] dp = new int[n][m]; // dp[i][j]: (0,0)에서 (i, j)까지 갔을 때, 얻을 수 있는 최대 가치
	    for(int i = 0; i < n; ++i) {
	        Arrays.fill(dp[i], Integer.MIN_VALUE);
	    }
	    
	    dp[0][0] = map[0][0];
	    
	    for(int j = 1; j < m; ++j) { // 첫 번째 줄은 예외! (항상 왼쪽에서 자신으로 옴!)
	        dp[0][j] = dp[0][j-1] + map[0][j];
	    }
	    
	    for(int i = 1; i < n; ++i) {
	        int[] toRight = new int[m];
	        int[] toLeft = new int[m];
	        
	        toRight[0] = dp[i-1][0] + map[i][0];
	        for(int j = 1; j < m; ++j) {
	            toRight[j] = Math.max(toRight[j-1], dp[i-1][j]) + map[i][j];
	        }
	        
	        toLeft[m-1] = dp[i-1][m-1] + map[i][m-1];
	        for(int j = m-2; j >= 0; --j) {
	            toLeft[j] = Math.max(toLeft[j+1], dp[i-1][j]) + map[i][j];
	        }
	        
	        for(int j = 0; j < m; ++j) {
	            dp[i][j] = Math.max(toRight[j], toLeft[j]);
	        }
	    }
	    
	    System.out.println(dp[n-1][m-1]);
	}
}