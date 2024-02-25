import java.io.*;
import java.util.*;

public class Main
{
    static int answer = Integer.MIN_VALUE;
    static boolean[][] map;
    static int row, col;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new boolean[row][col];
        for(int i = 0; i < row; ++i) {
            String rowString = bf.readLine();
            for(int j = 0; j < col; ++j) {
                char c = rowString.charAt(j);
                if(c == 'L') map[i][j] = true;
                else map[i][j] = false;
            }
        }

        for(int i = 0; i < row; ++i)
            for(int j = 0; j < col; ++j)
                if(map[i][j]) bfs(new Point(i, j, 0));

        System.out.println(answer);
    }

    public static void bfs(Point start) {
        boolean[][] check = new boolean[row][col];
        Deque<Point> q = new ArrayDeque();
        q.offer(start);
        check[start.x][start.y] = true;

        int max = -1;
        while(!q.isEmpty()) {
            Point p = q.poll();

            if(p.dist > max) max = p.dist;
            
            for(int i = 0; i < 4; ++i) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && nx < row && ny >= 0 && ny < col && map[nx][ny] && !check[nx][ny]) {
                    q.offer(new Point(nx, ny, p.dist + 1));
                    check[nx][ny] = true;
                }
            }
        }

        answer = Math.max(answer, max);
    }
}
