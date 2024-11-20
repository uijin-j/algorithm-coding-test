import java.io.*;
import java.util.*;

// 13:49 시작! 풀이 참고 😭
public class Main
{
    /**
     * 10^k, 25 * 100^k (k >= 0)
     * 1 10 100 1000 10000 100000 ...
     * 25 2500 250000 25000000 ...
     * 
     * 동전의 갯수는 무한하다.
     * 배낭 문제?
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(bf.readLine());
	    
	    int[] coins = { 1, 10, 25 };
	    int[] dp = new int[100]; // dp[i]: 1, 10, 25 값어치의 동전으로 i를 지불하는 최적해 
	    Arrays.fill(dp, Integer.MAX_VALUE - 1);
	    dp[0] = 0;
	    for(int i = 0; i < 3; ++i) {
	        int coin = coins[i];
	        for(int j = coin; j < 100; ++j) {
	            dp[j] = Math.min(dp[j], 1 + dp[j - coin]);
	        }
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    while(t > 0) {
	        long price = Long.parseLong(bf.readLine());

            int count = 0;
	        while(price > 0) {
	            count += dp[(int) (price % 100)];
	            price /= 100;
	        }
	        
	        
	        sb.append(count).append("\n");
            
	        t--;
	    }
	    
	    System.out.print(sb);
	}
}