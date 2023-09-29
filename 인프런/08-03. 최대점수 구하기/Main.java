import java.util.*;
  
public class Main {
  static int n;
  static int m;
  static int[] scores;
  static int[] times;
  static int answer;

  public static void dfs(int l, int sumOfTime, int sumOfScore) {
    if(sumOfTime > m) return;
    if(l == n) {
      answer = Math.max(answer, sumOfScore);
      return;
    }

    dfs(l+1, sumOfTime+times[l], sumOfScore+scores[l]);
    dfs(l+1, sumOfTime, sumOfScore);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    scores = new int[n];
    times = new int[n];

    for(int i = 0; i < n; ++i) {
      scores[i] = sc.nextInt();
      times[i] = sc.nextInt();
    }

    dfs(0, 0, 0);

    System.out.println(answer);
  }
}