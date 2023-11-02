import java.util.*;

public class Main {
  static int[] dp = new int[100001];
  static int[] parent = new int[100001];
  static int[] cases = {1, -1, 2};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int k = sc.nextInt();

    bfs(n, k);

    System.out.println(dp[k] - 1);
    Stack<Integer> stack = new Stack<>();
    int index = k;
    while (index != n) {
        stack.push(index);
        index = parent[index];
    }

    stack.push(n);

    while (!stack.isEmpty()) {
        System.out.print(stack.pop() + " ");
    }
  }

  static void bfs(int start, int goal) {
    Queue<Integer> q = new LinkedList<>();

    dp[start] = 1;
    q.offer(start);

    while(!q.isEmpty()) {
      int now = q.poll();

      if(now == goal) return;

      for(int i = 0; i < 3; ++i) {
        int next = now + cases[i];
        if(i == 2) next = now * cases[i];

        if(next >= 0 && next <= 100000 && dp[next] == 0) {
          dp[next] = dp[now] + 1;
          parent[next] = now;
          q.offer(next);
        }
      }
    }
  }
}
