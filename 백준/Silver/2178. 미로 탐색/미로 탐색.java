import java.util.*;

public class Main {
  static int[][] board;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    board = new int[n+1][m+1]; // 미로판

    sc.nextLine();
    for(int i = 1; i <= n; ++i) { //입력
      String line = sc.nextLine();
      int j = 1;
      for(char c : line.toCharArray()) {
        board[i][j++] = c - '0';
      }
    }

    System.out.println(bfs(n, m, board));
  }

  // (1, 1)에서 (n, m)으로 가는 최단거리를 리턴
  static int bfs(int n, int m, int[][] board) {
    Queue<Point> q = new LinkedList<>();
    
    board[1][1] = 0;
    q.offer(new Point(1, 1));

    int count = 1; // 지나온 칸 수
    while (!q.isEmpty()) {
      int size = q.size();

      for(int i = 0; i < size; ++i) { // 한 depth를 탐색
        Point now = q.poll();

        for(int j = 0; j < 4; ++j) {
          int nx = now.x + dx[j];
          int ny = now.y + dy[j];

          if(nx == n && ny == m) return count + 1; // 다음칸에서 도착이므로 '지금까지 지나온 칸 + 다음칸' 리턴
          if(nx >= 1 && nx <= n && ny >= 1 && ny <= m && board[nx][ny] == 1) {
            board[nx][ny] = 0; // 방문 check
            q.offer(new Point(nx, ny));
          }
        }
      }

      count++;
    }

    return -1; // 경로가 없는 경우
  }
}
