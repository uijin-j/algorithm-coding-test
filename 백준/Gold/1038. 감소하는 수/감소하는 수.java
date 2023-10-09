import java.util.*;

public class Main {
  static int N;
  static List<Long> decreasing = new ArrayList<>();

  public static void find(long num, int length) {
    if(length > 10) return;

    decreasing.add(num);
    for(int i = 0; i < num % 10; ++i) {
      find((num * 10) + i, length + 1);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();

    // 감소하는 수의 최댓값 : 9876543210
    if(N < 10) System.out.println(N);
    else if(N > 1022) System.out.println(-1);
    else {
      for(long i = 0; i < 10; ++i) {
        find(i, 1);
      }
      Collections.sort(decreasing);
      System.out.println(decreasing.get(N));
    }
  }
}