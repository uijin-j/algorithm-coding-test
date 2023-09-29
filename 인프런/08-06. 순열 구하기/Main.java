import java.util.*;
  
public class Main {
  static int n;
  static int m;
  static int[] nums;
  static boolean[] check;
  static int[] select;

  public static void dfs(int l) {
    if(l == m) {
      for(int num: select) System.out.print(num + " ");
      System.out.println();
      return;
    }

    for(int i = 0; i < n; ++i) {
      if(!check[i]) {
        select[l] = nums[i];
        check[i] = true;
        dfs(l+1);
        check[i] = false;
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    nums = new int[n];
    check = new boolean[n];
    select = new int[m];
    for(int i = 0; i < n; ++i) nums[i] = sc.nextInt();

    dfs(0);
  }
}