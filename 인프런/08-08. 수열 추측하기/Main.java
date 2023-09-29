import java.util.*;
  
public class Main {
  static int n;
  static int f;
  static boolean[] check;
  static int[] select;
  static int[] pascal;
  static int[][] dy;
  static boolean flag = false;

  public static int nCr(int n, int r) {
    if(dy[n][r] > 0) return dy[n][r];
    if(n == r || r == 0) return 1;
    return dy[n][r] = nCr(n-1, r) + nCr(n-1, r-1);
  }

  public static void dfs(int l, int sum) {
    if(flag) return;
    if(sum > f) return;
    if(l == n) {
      if(sum == f) {
        for(int num: select) System.out.print(num + " ");
        System.out.println();
        flag = true;
      }
      return;
    }

    for(int i = 1; i <= n; ++i) {
      if(!check[i]) {
        select[l] = i;
        check[i] = true;
        dfs(l+1, sum + i * pascal[l]);
        check[i] = false;
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    f = sc.nextInt();
    check = new boolean[n+1];
    select = new int[n];
    pascal = new int[n];
    dy = new int[n+1][n+1];
    
    for(int i = 0; i < n; ++i) pascal[i] = nCr(n-1, i);

    dfs(0, 0);
  }
}