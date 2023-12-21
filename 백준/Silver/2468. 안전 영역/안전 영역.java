import java.io.*;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws Exception {
        // dfs -> 100 * (100 * 100) * (100 * 100)

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        int[][] area = new int[n][n];

        int min = 100, max = 0;
        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; ++j) {
                area[i][j] = Integer.parseInt(st.nextToken());

                min = Math.min(min, area[i][j]);
                max = Math.max(max, area[i][j]);
            }
        }

        int maxCount = 0;
        for(int i = min - 1; i <= max; ++i) {
            int count = 0;
            // i만큼 잠길 때
            boolean[][] checked = check(area, i);
            for(int j = 0; j < n; ++j) {
                for(int k = 0; k < n; ++k) {
                    if(checked[j][k]) {
                        count++;
                        checked[j][k] = false;
                        dfs(j, k, checked);
                    }
                }
            }

            maxCount = Math.max(maxCount, count);
        }

        System.out.println(maxCount);
    }

    public static boolean[][] check(int[][] original, int height) {
        boolean[][] copy = new boolean[n][n];

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                copy[i][j] = (original[i][j] > height) ? true : false;
            }
        }

        return copy;

    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void dfs(int x, int y, boolean[][] checked) {
        for(int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n && checked[nx][ny]) {
                checked[nx][ny] = false;
                dfs(nx, ny, checked);
            }
        }
    }
}
