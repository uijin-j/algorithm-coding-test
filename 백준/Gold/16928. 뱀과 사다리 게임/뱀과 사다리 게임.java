import java.util.*;

public class Main {
  static int n, m;
  static int[] board = new int[101];
  static int[] dp = new int[101];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();
    
    for(int i = 0; i < n + m; ++i) {
      int start = sc.nextInt();
      int end = sc.nextInt();

      board[start] = end;
    }

    bfs();

    System.out.println(dp[100]);
  }

  static void bfs() {
    Queue<Integer> q = new LinkedList<>();

    q.offer(1);

    while (!q.isEmpty()) {
      int now = q.poll();

      for(int i = 1; i <= 6; ++i) {
        int next = now + i;
        if(next > 100) return;
        if(next == 100) {
          dp[next] = dp[now] + 1;
          return;
        };

        if(board[next] != 0) { // 뱀 또는 사다리에 연결됨
          next = board[now + i];
        }

        if(dp[next] == 0) { // 아직 최소거리 확정 X
          dp[next] = dp[now] + 1;
          q.offer(next);
        }
      }
    }
  }
}