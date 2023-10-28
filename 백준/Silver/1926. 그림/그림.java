import java.util.*;

public class Main {
  static int n, m, num;
  static int[][] paper;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    paper = new int[n][m];

    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        paper[i][j] = sc.nextInt();
      }
    }

    int count = 0;
    int max = 0;
    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        if(paper[i][j] == 1) {
          count++;

          paper[i][j] = 0;
          num = 1;
          dfs(i, j);
          max = Math.max(max, num);
        }
      }
    }

    System.out.println(count);
    System.out.println(max);
  }

  static void dfs(int x, int y) {
    for(int i = 0; i < 4; ++i) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if(nx >= 0 && nx < n && ny >= 0 && ny < m && paper[nx][ny] == 1) {
        paper[nx][ny] = 0;
        num++;
        dfs(nx, ny);
      }
    }
  }
}