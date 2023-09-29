import java.util.*;
  
public class Main {
  static int c;
  static int n;
  static int[] dog;
  static int answer = 0;

  public static void dfs(int l, int sum) {
    if(sum > c) return;
    if(l == n) {
        answer = Math.max(answer, sum);
        return;
    }

    dfs(l+1, sum+dog[l]);
    dfs(l+1, sum);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    c = sc.nextInt();
    n = sc.nextInt();
    dog = new int[n];

    for(int i = 0; i < n; ++i) dog[i] = sc.nextInt();

    dfs(0, 0);
    
    System.out.println(answer);
  }
}