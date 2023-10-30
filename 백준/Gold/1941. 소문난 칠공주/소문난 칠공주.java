import java.util.*;

public class Main {
  static int answer = 0;
  static char[][] tables = new char[5][5];
  static boolean[][] selected = new boolean[5][5];
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  public static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public Point getNext() {
      if(y == 4) {
        return new Point(x + 1, 0);
      }
      return new Point(x, y + 1);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    for(int i = 0; i < 5; ++i) {
      String row = sc.nextLine();

      int j = 0;
      for(char c: row.toCharArray()) {
        tables[i][j++] = c;
      }
    }

    selectPrincess(0, 0, new Point(0, 0));

    System.out.println(answer);
  }

  // 7공주를 선택하는 메서드
  static void selectPrincess(int count, int countS, Point now) {
    if(count == 7) {
      if(countS >= 4 && isConnected()) {
        answer++;
      }
      return;
    }

    if(now.x == 5) return;

    selected[now.x][now.y] = true;
    selectPrincess(count + 1, (tables[now.x][now.y] == 'S') ? countS + 1 : countS, now.getNext());

    selected[now.x][now.y] = false;
    selectPrincess(count, countS, now.getNext());
  }

  static boolean isConnected() {
    boolean[][] selectedCopy = copy(selected);

    for(int i = 0; i < 5; ++i) {
      for(int j = 0; j < 5; ++j) {
        if(selectedCopy[i][j]) {
          dfs(selectedCopy, new Point(i, j));
          if(isAllUnSelected(selectedCopy)) return true;
          return false;
        }
      }
    }

    return false;
  }

  static void dfs(boolean[][] arr, Point now) {
    for(int i = 0; i< 4; ++i) {
      int nx = now.x + dx[i];
      int ny = now.y + dy[i];

      if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && arr[nx][ny]) {
        arr[nx][ny] = false;
        dfs(arr, new Point(nx, ny));
      }
    }
  }

  static boolean isAllUnSelected(boolean[][] arr) {
    for(int i = 0; i < 5; ++i) {
      for(int j = 0; j < 5; ++j) {
        if(arr[i][j]) return false;
      }
    }

    return true;
  }

  static boolean[][] copy(boolean[][] arr) {
    boolean[][] copy = new boolean[5][5];

    for(int i = 0; i < 5; ++i) {
      for(int j = 0; j < 5; ++j) {
        copy[i][j] = arr[i][j];
      }
    }

    return copy;
  }
}