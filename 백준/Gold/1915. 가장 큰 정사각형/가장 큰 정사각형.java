import java.io.*;
import java.util.*;

public class Main
{
    // 누적합?
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] arr = new boolean[n+1][m+1];
        for(int i = 1; i <= n; ++i) {
            String row = bf.readLine();
            for(int j = 1; j <= m; ++j) {
                char c = row.charAt(j-1);
                if(c == '0') arr[i][j] = false;
                else arr[i][j] = true;
            }
        }

        int[][][] info = new int[n+1][m+1][3]; // 가로, 세로, 사각형
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                if(!arr[i][j]) continue;
                info[i][j][0] = info[i][j-1][0] + 1;
                info[i][j][1] = info[i-1][j][1] + 1;
                
                if(info[i][j-1][0] == 0 || info[i-1][j][0] == 0 || info[i-1][j-1][2] == 0) info[i][j][2] = 1;
                info[i][j][2] = Math.min(Math.min(info[i][j][0], info[i][j][1]), info[i-1][j-1][2] + 1);

                max = Math.max(max, info[i][j][2]);
            }
        }

        System.out.println(max * max);
    }
}
