import java.util.*;

public class Main {
  static int n;
  static int[][] forest;
  static int[][] dp;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  static int dfs(int x, int y) {
    if(dp[x][y] != 0) return dp[x][y];

    dp[x][y] = 1;
    for(int i = 0; i < 4; ++i) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if(nx >= 0 && nx < n && ny >= 0 && ny < n && forest[nx][ny] > forest[x][y]) {
        dp[x][y] = Math.max(dp[x][y], dfs(nx,ny) + 1);
      }
    }

    return dp[x][y];
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    n = sc.nextInt();

    forest = new int[n][n];
    dp = new int[n][n];

    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < n; ++j) {
        forest[i][j] = sc.nextInt();
      }
    }

    int answer = 0;
    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < n; ++j) {
        answer  = Math.max(dfs(i, j), answer);
      }
    }

    System.out.println(answer);
  }
}