import java.io.*;
import java.util.*;

public class Main
{ 
    public static class Matrix {
        int r, c;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        Matrix[] matrices = new Matrix[n];
        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrices[i] = new Matrix(r, c);
        }

        // DP
        int[][] dp = new int[n][n]; // dp[i][j]: i번째 행렬에서 j번째 행렬까지의 곱했을 때, 최소 연산 횟수
        for(int i = 0; i < n; ++i) Arrays.fill(dp[i], Integer.MAX_VALUE);
        for(int i = 0; i < n; ++i) dp[i][i] = 0;

        for(int size = 2; size <= n; ++size) {
            for(int start = 0; start <= n - size; ++start) {
                int end = start + size - 1;
				for(int k = start; k < end; ++k) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][k] + dp[k+1][end] + matrices[start].r * matrices[k].c * matrices[end].c);
                }
            }
        }
        
        System.out.println(dp[0][n-1]);
    }
}
