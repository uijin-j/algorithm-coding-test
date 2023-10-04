import java.util.*;

public class Main {
    static int n, m, answer;
    static int[] plans;

    public static int countWithdraw(int limit) {
      int count = 1;
      int money = limit;
      for(int today: plans) {
        if(money - today < 0) {
          count++;
          money = limit;
        }
        money -= today;
      }

      return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        m = sc.nextInt();
        plans = new int[n];

        for(int i = 0; i < n; ++i) plans[i] = sc.nextInt();

        int lt = Arrays.stream(plans).max().getAsInt();
        int rt = Arrays.stream(plans).sum();

        while(lt <= rt) {
          int mid = (lt+rt)/2;
          if(countWithdraw(mid) <= m) {
            answer = mid;
            rt = mid - 1;
          } else lt = mid + 1;
        }

        System.out.println(answer);
    }
}