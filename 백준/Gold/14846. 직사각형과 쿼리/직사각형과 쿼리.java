import java.util.*;

public class Main {
  static int n;
  static int[][] matrix;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    matrix = new int[n+1][n+1];

    for(int i = 1; i <= n ; ++i) {
      for(int j = 1; j <= n; ++j) {
        matrix[i][j] = sc.nextInt();
      }
    }

    int[][][] dp = new int[n+1][n+1][11]; // dp[i][j][k]는 (1, 1)에서 (i, j)사이에 있는 K의 갯수
    for(int k = 1; k <= 10; ++k) {
      for(int i = 1; i <= n; ++i) {
        for(int j = 1; j <= n; ++j) {
          dp[i][j][k] = dp[i-1][j][k] + dp[i][j-1][k] - dp[i-1][j-1][k];
          if(k == matrix[i][j]) dp[i][j][k] += 1;
        }
      }
    }

    int q = sc.nextInt();
    while(q > 0) {
      int x1 = sc.nextInt();
      int y1 = sc.nextInt();
      int x2 = sc.nextInt();
      int y2 = sc.nextInt();

      int count = 0;
      for(int i = 1; i <= 10; ++i) {
        if(dp[x2][y2][i] - dp[x2][y1-1][i] - dp[x1-1][y2][i] + dp[x1-1][y1-1][i] > 0) {
          count++;
        }
      }
      System.out.println(count);

      q--;
    }
  }
}
