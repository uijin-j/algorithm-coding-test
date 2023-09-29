import java.util.*;
  
public class Main {
  static int n;
  static int m;
  static int[] memory;

  public static void dfs(int l) {
    if(l == m) {
      for(int num: memory) System.out.print(num + " ");
      System.out.println();
      return;
    }

    for(int i = 1; i <= n; ++i) {
      memory[l] = i;
      dfs(l+1);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    memory = new int[m];

    dfs(0);
  }
}