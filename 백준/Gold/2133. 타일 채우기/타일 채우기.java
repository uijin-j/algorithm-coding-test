import java.util.*;

public class Main {
  static int n, answer;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    if(n % 2 == 1) {
      System.out.println(0);
      return;
    }
    
    int[] dp = new int[Math.max(n / 2 + 1, 3)]; // dp[i]는 n = i * 2일 때, 경우의 수
    dp[1] = 3;
    dp[2] = 11;
    int tmp = 0;
    for (int i = 3; i <= n / 2; i++) {
        dp[i] = dp[i-1] * 3 + 2 + (tmp += dp[i-2] * 2);
    }
    
    System.out.println(dp[n/2]);
  }
}