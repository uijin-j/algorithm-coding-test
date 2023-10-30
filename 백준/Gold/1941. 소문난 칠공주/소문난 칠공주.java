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
      String line = sc.nextLine();
      for(int j = 0; j < 5; ++j) {
        tables[i][j] = line.charAt(j);
      }
    }

    selectPrincess(0, 0, new Point(0, 0));

    System.out.println(answer);
  }

  // 7공주를 선택하는 메서드
  static void selectPrincess(int count, int countS, Point now) {
    if(count == 7) {
      if(countS >= 4 && isConnected()) answer++;
      return;
    }

    if(now.x == 5) return;

    selected[now.x][now.y] = true;
    selectPrincess(count + 1, (tables[now.x][now.y] == 'S') ? countS + 1 : countS, now.getNext());

    selected[now.x][now.y] = false;
    selectPrincess(count, countS, now.getNext());
  }

  // 7공주 자리가 연결되어 있는지 확인하는 메서드
  static boolean isConnected() {
    boolean[][] selectedCopy = copy(selected);

    for(int i = 0; i < 5; ++i) {
      for(int j = 0; j < 5; ++j) {
        if(selectedCopy[i][j]) { // 7공주 중 한명을 찾으면
          dfs(selectedCopy, new Point(i, j));
          if(isAllUnSelected(selectedCopy)) return true;
          return false;
        }
      }
    }

    return false;
  }

  // Point로 들어온 자리의 공주랑 연결된 다른 공주 자리를 false로 변경
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