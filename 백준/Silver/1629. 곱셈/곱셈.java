import java.util.*;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();
    int b = sc.nextInt();
    int c = sc.nextInt();

    
    System.out.println(fuc(a, b, c));
  }

  static long fuc(int a, int b, int c) {
    if(b == 1) return a % c;

    long n = fuc(a, b/2, c);
    if(b % 2 == 0) {
      return (n * n) % c;
    }
    
    return (((n * n) % c) * (a % c)) % c;
  }
}
