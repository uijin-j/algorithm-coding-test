import java.util.*;

public class Main {
  static int N, K, answer;
  static String[] words;
  static boolean[] check = new boolean[26];

  public static void dfs(int l, int s) {
    if(l == K) {
      int count = 0;
      for(int i = 0; i < N; ++i) {
        boolean flag = true;
        for(char c : words[i].toCharArray()) {
          if(!check[c - 'a']) {
            flag = false;
            break;
          }
        }
        if(flag) count++;
      }
      answer = Math.max(answer, count);
      return;
    }

    for(int i = s; i < 26; ++i) {
      if(check[i]) continue;
      check[i] = true;
      dfs(l + 1, i + 1);
      check[i] = false;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();
    words = new String[N];

    // a n t i c 는 무조건 알아야 함
    if(K < 5) {
      System.out.println(0);
      return;
    }

    check['a' - 'a'] = true;
    check['n' - 'a'] = true;
    check['t' - 'a'] = true;
    check['i' - 'a'] = true;
    check['c' - 'a'] = true;

    sc.nextLine();
    for(int i = 0; i < N; ++i) {
      words[i] = sc.nextLine();
    }

    dfs(5, 0);
    System.out.println(answer);
  }
}