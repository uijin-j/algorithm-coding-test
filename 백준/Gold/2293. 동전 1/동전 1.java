import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] dp = new int[k+1]; // dp[i]: i 값어치를 만드는 경우의 수
        dp[0] = 1; // dp[0]: 0을 만드는 경우의 수는 아무 동전을 사용하지 않는 것
        for(int i = 0; i < n; ++i) {
            int value = Integer.parseInt(bf.readLine());
            for(int j = value; j <= k; ++j) {
                dp[j] += dp[j - value];
            }
        }

        // Q. dp[j] += dp[j - value]를 하면 해당 동전을 딱 1번만 사용하는 거 아닌가?
        // ex. 1 2 5 동전이 있고 10을 만드는 경우에 현재 i가 1이고(1, 2 동전만 있을 때) j가 7이라면
        // dp[7] += dp[7 - 2]를 하면 2 동전을 딱 하나 사용하는 것만 고려하는 것 아닌가? 
        // += 의 의미는 2가 없었을 때 dp[7](즉, 1 동전만 있을 때 dp[7])에 2 동전이 있는 경우를 더하는 것이다. (즉, 2를 무조건 사용하는 경우를 더해야 하긴 함!)
        // 내가 생각하기에 dp[7] += dp[7 - (2 * 1)] + dp[7 - (2 * 2)] + dp[7 - (2 * 3)] 인데? (2를 1번 사용 + 2를 2번 사용 + ... + 2 * n < 7일때까지 반복)
        // A. 만약 dp[7] += dp[7 - 2]를 한다면, 이미 먼저 dp[5] += dp[5 - 2]를 했을 것이기 때문에 dp[5] 안에는 2번 동전을 사용하는 경우의 수가 포함되어 있음!!
        
        System.out.println(dp[k]);
    }
}
