import java.util.*;

public class Main {
    static int N;
    static int answer = Integer.MAX_VALUE;
    static int[][] cost;
    static int[][] dp; // dp[1][0] : 1번 노드가 0번 색을 칠했을 때 최솟값

    public static void paintHouse(int h) {
      if(h == N) return;

      dp[h][0] = Math.min(dp[h-1][1], dp[h-1][2]) + cost[h][0];
      dp[h][1] = Math.min(dp[h-1][0], dp[h-1][2]) + cost[h][1];
      dp[h][2] = Math.min(dp[h-1][0], dp[h-1][1]) + cost[h][2];
  
      paintHouse(h + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        N = sc.nextInt();
        cost = new int[N][3];
        dp = new int[N][3];

        for(int i = 0; i < N; ++i) {
          cost[i][0] = sc.nextInt();
          cost[i][1] = sc.nextInt();
          cost[i][2] = sc.nextInt();
        }
        
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];

        paintHouse(1);
    
        System.out.println(Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]));
    }
}