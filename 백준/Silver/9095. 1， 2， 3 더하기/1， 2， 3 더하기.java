import java.io.*;

public class Main {
    static int[] dp;
    static int k;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        dp = new int[12]; // dp[i]: i일 때, 나올 수 있는 경우의 수
        dp[1] = 1;
        dp[2] = dp[1] + 1;
        dp[3] = dp[2] + dp[1] + 1;

        while(t > 0) {
            int n = Integer.parseInt(bf.readLine());

            System.out.println(getNumberOfCases(n));

            --t;
        }
    }

    public static int getNumberOfCases(int i) {
        if(i == 1) return dp[1];
        if(i == 2) return dp[2];
        if(i == 3) return dp[3];

        return dp[i] = getNumberOfCases(i - 1) + getNumberOfCases(i - 2) + getNumberOfCases(i - 3);
    }
}
