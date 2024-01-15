import java.io.*;
import java.util.*;

public class Main
{
    static int INF = 1500;
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n+1];
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; ++i) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dists = new int[n+1][n+1];
        for(int i = 1; i <= n; ++i) {
            Arrays.fill(dists[i], INF);
            dists[i][i] = 0;
        }

        for(int i = 0; i < r; ++i) {
            st = new StringTokenizer(bf.readLine());
            int area1 = Integer.parseInt(st.nextToken());
            int area2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            dists[area1][area2] = dist;
            dists[area2][area1] = dist;
        }
        
        // 플로이드-와샬
        for(int k = 1; k <= n; ++k) {
            for(int i = 1; i <= n; ++i) {
                for(int j = 1; j <= n; ++j) {
                    dists[i][j] = Math.min(dists[i][j], dists[i][k] + dists[k][j]);
                }
            }
        }

        int max = 0;
        for(int i = 1; i <= n; ++i) { // i 지역에 떨어졌을 때
            int sum = 0;
            for(int j = 1; j <= n; ++j) {
                if(dists[i][j] <= m) {
                    sum += items[j];
                }
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);
	}
}
