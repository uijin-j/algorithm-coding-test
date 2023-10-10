import java.util.*;

public class Main {
  static int N, answer = Integer.MAX_VALUE;
  static int[][] synergy;
  static boolean[] check;

  public static void dfs(int l, int s) {
    if(l * 2 == N) {
      int sum1 = 0, sum2 = 0;
      for(int i = 0; i < N; ++i) {
        for(int j = 0; j < N; ++j) {
          if(check[i] == check[j]) {
            if(check[i]) sum1 += synergy[i][j];
            if(!check[i]) sum2 += synergy[i][j];
          }
        }
      }

      answer = Math.min(answer, Math.abs(sum1 - sum2));
      return;
    }

    for(int i = s; i < N; ++i) {
      check[i] = true;
      dfs(l + 1, i + 1);
      check[i] = false; 
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    synergy = new int[N][N];
    check = new boolean[N];

    for(int i = 0; i < N; ++i) {
      for(int j = 0; j < N; ++j) {
        synergy[i][j] = sc.nextInt();
      }
    }

    dfs(0, 0);

    System.out.println(answer);
  }
}