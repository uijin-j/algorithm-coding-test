import java.util.*;
  
public class Main {
  static int n;
  static int m;
  static int[] coins;
  static int answer = Integer.MAX_VALUE;

  public static void dfs(int l, int money) {
    if(money > m) return;
    if(l >= answer) return;
    if(money == m) {
      answer = Math.min(answer, l);
      return;
    }

    for(int i = n - 1; i >= 0; --i) {
      dfs(l+1, money+coins[i]);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    coins = new int[n];
    for(int i = 0; i < n; ++i) coins[i] = sc.nextInt();
    m = sc.nextInt();

    Arrays.sort(coins);

    dfs(0, 0);
    System.out.println(answer);
  }
}