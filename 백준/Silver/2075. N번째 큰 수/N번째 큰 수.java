import java.util.*;

public class Main {
  static class Number {
    int r, c, value;

    public Number(int r, int c, int value) {
      this.r = r;
      this.c = c;
      this.value = value;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[][] table = new int[n][n];

    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < n; ++j) {
        table[i][j] = sc.nextInt();
      }
    }

    // n번째 큰 수
    PriorityQueue<Number> q = new PriorityQueue<>((a, b) -> b.value - a.value);
    for(int i = 0; i < n; ++i) {
      q.offer(new Number(n-1, i, table[n-1][i]));
    }

    for(int i = 0; i < n-1; ++i) {
      Number now = q.poll();
      q.offer(new Number(now.r - 1, now.c, table[now.r-1][now.c]));
    }

    System.out.println(q.poll().value);
  }
}
