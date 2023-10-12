import java.util.*;

public class Main {
  static int M, N, K, answer, count;
  static boolean[][] board;
  static List<Integer> counts = new ArrayList<>();
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  public static void count(int x, int y, int c) {
    ++count;

    for(int i = 0; i < 4; ++i) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if(nx >= 0 && nx < N && ny >= 0 && ny < M && !board[nx][ny]) {
        board[nx][ny] = true;
        count(nx, ny, c + 1);
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    M = sc.nextInt();
    N = sc.nextInt();
    K = sc.nextInt();
    board = new boolean[N][M];

    for(int i = 0; i < K; ++i) {
      int lx = sc.nextInt();
      int ly = sc.nextInt();
      int rx = sc.nextInt();
      int ry = sc.nextInt();

      for(int j = lx; j < rx; ++j) {
        for(int k = ly; k < ry; ++k) {
          board[j][k] = true;
        }
      }
    }

    for(int i = 0; i < N; ++i) {
      for(int j = 0; j < M; ++j) {
        if(!board[i][j]) {
          answer++;
          board[i][j] = true;
          count = 0;
          count(i, j, 1);
          counts.add(count);
        }
      }
    }

    System.out.println(answer);
    counts.stream().sorted().forEach(i -> System.out.print(i + " "));
  }
}