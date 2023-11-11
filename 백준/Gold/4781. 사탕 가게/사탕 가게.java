import java.util.*;

public class Main {
  public static class Candy {
    int kcal;
    int price;

    public Candy(int kacl, int price) {
      this.kcal = kacl;
      this.price = price;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n, m;
    while(true) {
      n = sc.nextInt();
      m = (int) Math.round(sc.nextFloat() * 100f);

      if(n == 0 && m == 0) break;

      List<Candy> candies = new ArrayList<>();
      for(int i = 0; i < n; ++i) {
        int price = sc.nextInt();
        int kcal = (int) Math.round(sc.nextFloat() * 100f);

        candies.add(new Candy(price, kcal));
      }

      int[] dp = new int[m + 1]; // dp[i]는 i/100원이 있을 때 구매할 수 있는 최대 칼로리
      for(Candy candy: candies) {
        for(int i = candy.price; i <= m; ++i) {
          dp[i] = Math.max(dp[i], dp[i - candy.price] + candy.kcal);
        }
      }

      System.out.println(dp[m]);
    }
  }
}
