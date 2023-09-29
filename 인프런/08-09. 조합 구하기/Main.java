import java.util.*;
  
public class Main {
  static int n;
  static int m;
  static int[] select;

  public static void dfs(int l, int s) {
    if(l == m) {
      for(int num: select) System.out.print(num + " ");
      System.out.println();
      return;
    }

    for(int i = s; i <= n; ++i) {
      select[l] = i;
      dfs(l+1, i+1);
    }
    
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    select = new int[m];

    dfs(0, 1);
  }
}