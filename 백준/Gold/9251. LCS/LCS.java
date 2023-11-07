import java.util.*;

public class Main {
  static String s1, s2;
  static int len1, len2;
  static int[][] dp;// dp[i][j]는 s1의 0 ~ i의 부분 문자와 s2의 0 ~ j까지의 부분 문자 사이의 LCS

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    s1 = sc.nextLine();
    s2 = sc.nextLine();
    len1 = s1.length();
    len2 = s2.length();
    dp = new int[len1 + 1][len2 + 1];

    dfs(1);

    System.out.println(dp[len1][len2]); 
  }

  static void dfs(int len) { // 열 채우기
    if(len == len2 + 1) {
      return;
    }

    for(int i = 1; i <= len1; ++i) {
      // dp[i][len]을 채워야 함
      if(s1.charAt(i - 1) == s2.charAt(len - 1)) {
        dp[i][len] = dp[i - 1][len - 1] + 1;
      } else {
        dp[i][len] = Math.max(dp[i - 1][len], dp[i][len - 1]);
      }
    }

    dfs(len + 1);
  }
}
