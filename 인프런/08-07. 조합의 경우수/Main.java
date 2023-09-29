import java.util.*;
  
public class Main {
  static int n;
  static int r;
  static int[][] dy;

  public static int dfs(int n, int r) {
    if(dy[n][r] > 0) return dy[n][r];
    if(n == r || r == 0) return 1;
    if(r * 2 > n) r = n - r;
    return dy[n][r] = dfs(n-1, r-1) + dfs(n-1, r);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    r = sc.nextInt();
    dy = new int[n+1][n+1];

    System.out.println(dfs(n ,r));
  }
}