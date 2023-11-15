import java.util.*;

public class Main {
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
    int[][] map = new int[n][m];

    sc.nextLine();
    for(int i = 0; i < n; ++i) {
      String row = sc.nextLine();
      int j = 0;
      for(char c : row.toCharArray()) {
        map[i][j++] = c - '0';
      }
    }

    int answer = bfs(n, m, map);
    System.out.println(answer);
  }

  static int bfs(int n, int m, int[][] map) {
    Queue<Point> q = new LinkedList<>();
    
    map[0][0] = 0;
    q.offer(new Point(0, 0));

    int count = 1;
    while (!q.isEmpty()) {
      int size = q.size();

      for(int i = 0; i < size; ++i) {
        Point now = q.poll();

        for(int j = 0; j < 4; ++j) {
          int nx = now.x + dx[j];
          int ny = now.y + dy[j];

          if(nx == n-1 && ny == m-1) return count + 1;

          if(nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 1) {
            map[nx][ny] = 0;
            q.offer(new Point(nx, ny));
          }
        }
      }

      count++;
    }

    return -1;
  }
}
