import java.io.*;
import java.util.*;

public class Main
{ 
    // 블로그 참고: https://sskl660.tistory.com/89
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] boxes = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
			boxes[i] = Integer.parseInt(st.nextToken());
		}

        int[] dp = new int[n]; // dp[i]: i번째 수까지 있다고 했을 때, i위치에서의 LIS
        Arrays.fill(dp, 1);
        int max = 1;
        for(int i = 1; i < n; ++i) {
            for(int j = 0; j < i; ++j) {
                if(boxes[j] < boxes[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
