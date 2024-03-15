import java.io.*;
import java.util.*;

public class Main
{ 
    static boolean[][] board = new boolean[10][10];
    static int[] remain = new int[6];
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(remain, 5);
        StringTokenizer st;
        for(int i = 0; i < 10; ++i) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < 10; ++j) {
                int point = Integer.parseInt(st.nextToken());
                if(point == 1) board[i][j] = true;
            }
        }

        dfs(0, 0, 0);

        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    public static void dfs(int x, int y, int count) {
        if (x >= 9 && y > 9) {
            min = Math.min(min, count);
            return;
        }

        if (min <= count) return;

        if (y > 9) {
            dfs(x + 1, 0, count);
            return;
        }
 
        if (board[x][y]) {
            for (int i = 5; i >= 1; i--) {
                if (remain[i] > 0 && check(x, y, i)) {
                    fill(x, y, i, false);
                    remain[i]--;
                    dfs(x, y + 1, count + 1);
                    fill(x, y, i, true);
                    remain[i]++;
                }
            }
        } else {
            dfs(x, y + 1, count);
        }

    }

    public static boolean check(int x, int y, int size) {
        for(int i = x; i < x + size; ++i) {
            for(int j = y; j < y + size; ++j) {
                if(i >= 10 || j >= 10 || !board[i][j]) return false;
            }
        }

        return true;
    }

    public static void fill(int x, int y, int size, boolean state) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                board[i][j] = state;
            }
        }
    }
}
