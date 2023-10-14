import java.util.*;

public class Main {
  static int L, C;
  static boolean[] alpha = new boolean[26];

  public static void combi(int l, int s, String password, int v, int c) {
    if(l == L) {
      if(v < 1 || c < 2) return; // v는 모음 갯수, c는 자음 갯수
      System.out.println(password);
      return;
    }

    for(int i = s; i < 26; ++i) {
      if(alpha[i]) {
        char ch = (char) (i + 'a');
        if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
          combi(l + 1, i + 1, password + ch, v + 1, c);
        } else {
          combi(l + 1, i + 1, password + ch, v, c + 1);
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    L = sc.nextInt();
    C = sc.nextInt();
    
    sc.nextLine();
    String[] input = sc.nextLine().split(" ");
    for(int i = 0; i < C; ++i) {
      int c = input[i].charAt(0) - 'a';
      alpha[c] = true;
    }

    combi(0, 0, "", 0, 0);
  }
}