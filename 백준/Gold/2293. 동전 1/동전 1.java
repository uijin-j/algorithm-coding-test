import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dp = new int[k+1]; // dp[i]: i 값어치를 만드는 경우의 수
        dp[0] = 1;
        for(int i = 0; i < n; ++i) {
            int value = Integer.parseInt(bf.readLine());
            for(int j = value; j <= k; ++j) {
                dp[j] += dp[j - value];
            }
        }

        System.out.println(dp[k]);
    }
}