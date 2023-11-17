import java.util.*;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    // 시간 제한 1초
    // 모든 경우를 다 해보는 경우 (dfs) -> O(n^2)
    // n의 최댓값이 100_000이므로 dfs로 풀 수 없음..

    // 문제를 분할해서 생각해보자!
    // i열에서 첫번째 스티커를 선택 했다면, i-1에서 두번째 스티커를 선택하거나, i-2에서 두번째 스티커를 선택할 수 있다!

    while(t > 0) {
      int n = sc.nextInt();
      int[][] stickers = new int[2][n+1];

      for(int i = 0; i < 2; ++i) {
        for(int j = 1; j <= n; ++j) {
          stickers[i][j] = sc.nextInt();
        }
      }

      int[][] dp = new int[2][n+1]; // dp[0][i]는 i열의 스티커 중 첫 줄의 스티커를 선택했을 때, 최댓값 / dp[1][i]는 i열의 스티커 중 두번째 줄의 스티커를 선택했을 때, 최댓값
      dp[0][1] = stickers[0][1];
      dp[1][1] = stickers[1][1];
      
      for(int i = 2; i <= n; ++i) {
        dp[0][i] = stickers[0][i] + Math.max(dp[1][i-1], dp[1][i-2]);
        dp[1][i] = stickers[1][i] + Math.max(dp[0][i-1], dp[0][i-2]);
      }

      System.out.println(Math.max(dp[0][n], dp[1][n]));
      t--;
    }
  }
}
