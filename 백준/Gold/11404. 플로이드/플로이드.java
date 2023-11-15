import java.util.*;

public class Main {
  static final int MAX_VALUE = 100_000_001;
  static int[][] cost;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); // 도시의 갯수
    int m = sc.nextInt(); // 버스의 갯수
    cost = new int[n+1][n+1]; // cost[i][j]는 i -> j도시로 가는 최소 비용

    for(int i = 1; i <= n; ++i) { // cost[][] 초기화
      for(int j = 1; j <= n; ++j) {
        cost[i][j] = MAX_VALUE;
        if(i == j) cost[i][j] = 0;
      }
    }

    for(int i = 0; i < m; ++i) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int fare = sc.nextInt();

      cost[from][to] = Math.min(cost[from][to], fare);
    }

    // 플로이드-워샬
    for(int k = 1; k <= n; ++k) {
      for(int i = 1; i <= n; ++i) {
        for(int j = 1; j <= n; ++j) {
          cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
        }
      }
    }

    for(int i = 1; i <= n; ++i) {
      for(int j = 1; j <= n; ++j) {
        System.out.print((cost[i][j] == MAX_VALUE)? "0 " : (cost[i][j] + " "));
      }
      System.out.println();
    }
  }
}
