import java.util.*;

public class Main {
  static int n, m, answer;
  static int[][] map;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  public static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    map = new int[n][m];

    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        map[i][j] = sc.nextInt();
      }
    }

    buildWall(0);

    System.out.println(answer);
  }

  static void buildWall(int count) {
    if(count == 3) { // 벽 세우기 완료
      // 1. 바이러스 퍼트리기
      int[][] arr = copy(map);
      spreadVirus(arr);

      // 2. 안전영역 구하기
      answer = Math.max(answer, countSaftyArea(arr));
      return;
    }

    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        if(map[i][j] == 0) {
          map[i][j] = 1;
          buildWall(count + 1);
          map[i][j] = 0;
        }
      }
    }
  }

  public static int[][] copy(int[][] arr) {
    int[][] copy = new int[arr.length][];

    for (int i = 0; i < arr.length; i++) {
        copy[i] = Arrays.copyOf(arr[i], arr[i].length);
    }

    return copy;
  }

  public static void spreadVirus(int[][] arr) {
    Queue<Point> q = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
          if (arr[i][j] == 2) {
              q.add(new Point(i, j));
          }
      }
    }

    while(!q.isEmpty()) {
      Point now = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = now.x + dx[i];
        int ny = now.y + dy[i];
      
        if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == 0) {
          arr[nx][ny] = 2;
          q.add(new Point(nx, ny));
        }
      }
    }
  }

  public static int countSaftyArea(int[][] arr) {
    int result = 0;
    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        if(arr[i][j] == 0) result++;
      }
    }

    return result;
  }
}