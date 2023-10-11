import java.util.*;

public class Main {
  static int N;
  static List<Integer> answers = new ArrayList<>();

  public static void amazingPrime(int n, int candi) { // n자리 소수를 모두 구하자!
    if(n == N) {
      answers.add(candi);
      return;
    }
    for(int i = 1; i < 10; i += 2) {
      int temp = candi * 10 + i;
      if(isPrime(temp)) amazingPrime(n+1, temp);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();

    if(N == 1) {
      System.out.println(2);
      System.out.println(3);
      System.out.println(5);
      System.out.println(7);
      return;
    }
    
    amazingPrime(1, 2);
    amazingPrime(1, 3);
    amazingPrime(1, 5);
    amazingPrime(1, 7);

    answers.stream().forEach(i -> System.out.println(i));
  }

  public static boolean isPrime(int n) {
    for(int i = 2; i < n; ++i) {
      if(n % i == 0) return false;
    }
    return true;
  }
}