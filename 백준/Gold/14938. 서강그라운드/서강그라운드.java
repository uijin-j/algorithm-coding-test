import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100;
    static int n, m, r;
    static int[] items;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n+1];
        dist = new int[n+1][n+1];
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= n; ++j) {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; ++i) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < r; ++i) {
            st = new StringTokenizer(bf.readLine());
            int area1 = Integer.parseInt(st.nextToken());
            int area2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            dist[area1][area2] = Math.min(dist[area1][area2], distance);
            dist[area2][area1] = Math.min(dist[area2][area1], distance);
        }

        // 플로이드-와샬
        for(int k = 1; k <= n; ++k) {
            for(int i = 1; i <= n; ++i) {
                for(int j = 1; j <= n; ++j) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int answer = 0;
        for(int i = 1; i <= n; ++i) { // i 도시에 떨어졌을 때
            int sum = 0;
            for(int j = 1; j <= n; ++j) {
                if(dist[i][j] <= m) {
                    sum += items[j];
                }
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
