import java.util.*;

public class Main {
  static int[] dp = new int[100001];
  static int[] cases = {1, -1, 2};
  static int min = Integer.MAX_VALUE;
  static int count = 0;

  public static void main(String[] args) {
    int n, k;
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    k = sc.nextInt();

    if(k <= n) {
      System.out.println(n - k);
      System.out.println(1);
      return;
    }

    bfs(n, k);

    System.out.println(min);
    System.out.println(count);
  }

  static void bfs(int start, int goal) {
    Queue<Integer> q = new LinkedList<>();

    dp[start] = 1;
    q.offer(start);

    while(!q.isEmpty()) {
      int now = q.poll();

      if(dp[now] > min) return;

      for(int i = 0; i < 3; ++i) {
        int next = now + cases[i];
        if(i == 2) next = now * cases[i];

        if(next == goal) {
            min = dp[now];
            count++;
        }

        if(next >= 0 && next <= 100000 && (dp[next] == 0 || dp[next] == dp[now] + 1)) {
          dp[next] = dp[now] + 1;
          q.offer(next);
        }
      }
    }
  }
}