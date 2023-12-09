import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 스티커 문제랑 비슷 dp 문제인듯!
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] max = new int[n][3]; // dp[i][j]는 i+1 번째 줄에서 j칸을 선택했을 때, 지금까지의 최댓값
        int[][] min = new int[n][3]; // dp[i][j]는 i+1 번째 줄에서 j칸을 선택했을 때, 지금까지의 최솟값
        int[][] nums = new int[n][3];

        StringTokenizer st;
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < 3; ++j) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max[0][0] = nums[0][0];
        max[0][1] = nums[0][1];
        max[0][2] = nums[0][2];

        min[0][0] = nums[0][0];
        min[0][1] = nums[0][1];
        min[0][2] = nums[0][2];

        for(int i = 1; i < n; ++i) {
            max[i][0] = Math.max(max[i-1][0], max[i-1][1]) + nums[i][0];
            max[i][1] = Math.max(Math.max(max[i-1][0], max[i-1][1]), max[i-1][2]) + nums[i][1];
            max[i][2] = Math.max(max[i-1][1], max[i-1][2]) + nums[i][2];

            min[i][0] = Math.min(min[i-1][0], min[i-1][1]) + nums[i][0];
            min[i][1] = Math.min(Math.min(min[i-1][0], min[i-1][1]), min[i-1][2]) + nums[i][1];
            min[i][2] = Math.min(min[i-1][1], min[i-1][2]) + nums[i][2];
        }

        int maxResult = Math.max(Math.max(max[n-1][0], max[n-1][1]), max[n-1][2]);
        int minResult = Math.min(Math.min(min[n-1][0], min[n-1][1]), min[n-1][2]);
        System.out.print(maxResult + " " + minResult);
    }

}