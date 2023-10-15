import java.util.*;

public class Main {
  static int N;
  static int[][] map;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  public static int dfs(int x, int y) {
    int answer = 0;
    for(int i = 0; i < 4; ++i) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if(nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 1) {
        map[nx][ny] = 0;
        answer++;
        answer += dfs(nx, ny);
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    N = Integer.valueOf(sc.nextLine());
    
    map = new int[N][N];

    for(int i = 0; i < N; ++i) {
        String s = sc.nextLine();
        int j = 0;
        for(char c: s.toCharArray()) {
            map[i][j++] = c - '0';   
        }
    }

    int count = 0;
    List<Integer> house = new ArrayList<>();
    for(int i = 0; i < N; ++i) {
      for(int j = 0; j < N; ++j) {
        if(map[i][j] == 1) {
          count++;
          map[i][j] = 0;
          house.add(dfs(i, j) + 1);
        }
      }
    }
    
    System.out.println(count);
    house.stream().sorted().forEach(System.out::println);
  }
}