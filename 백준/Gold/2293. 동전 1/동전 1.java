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

        // Q. dp[j] += dp[j - value]를 하면 해당 동전을 딱 1번만 사용하는 거 아닌가? (ex. )
        // ex. 

        System.out.println(dp[k]);
    }
}
