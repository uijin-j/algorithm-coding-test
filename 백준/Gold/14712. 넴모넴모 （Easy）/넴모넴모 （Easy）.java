import java.io.*;
import java.util.*;

public class Main
{
    static int n, m, count;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1][m+1];
        
        dfs(1, 1);

        System.out.println(count);
	}

    public static void dfs(int x, int y) {
        if(x > n) {
            count++;
            return;
        }

        int[] next = getNext(x, y);
        if(!visited[x-1][y] || !visited[x-1][y-1] || !visited[x][y-1]) {
            visited[x][y] = true;
            dfs(next[0], next[1]);
            visited[x][y] = false;
        }

        dfs(next[0], next[1]);
    }

    public static int[] getNext(int x, int y) {
        if(y == m) {
            return new int[]{x + 1, 1};
        }

        return new int[]{x, y + 1};
    }

}
