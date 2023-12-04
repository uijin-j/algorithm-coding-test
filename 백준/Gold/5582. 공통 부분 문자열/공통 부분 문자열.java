import java.io.*;

public class Main {
    static String s1, s2;
    static int len1, len2, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int len1 = s1.length();
        int len2 = s2.length();

        int[][] dp = new int[len1 + 1][len2 + 1]; // dp[i][j]는 s1.substring(0, i)과 s2.substring(0, j) 사이의 가장 긴 공통 부분 문자열!

        // 0.1초 = 10_000_000 = O(n^2)까지 가능

        int answer = 0;
        for(int i = 1; i <= len1; ++i) {
            for(int j = 1; j <= len2; ++j) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    answer = Math.max(answer, dp[i][j]);
                    continue;
                }
            }
        }
        
        System.out.println(answer);
    }
}