import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    static int n, min = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        map = new int[n][n];

        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; ++j) {
                map[i][j] = (Integer.parseInt(st.nextToken()) == 1) ? -1 : 0;
            }
        }

        // 섬 별로 tagging -> dfs -> n^2
        // 모든 점에서 bfs.. n^2

        int num = 1;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                if(map[i][j] == -1) {
                    map[i][j] = num++;
                    tagging(i, j, map[i][j]);
                }
            }
        }

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                if(map[i][j] != 0) {
                    findMinDistanse(i, j, map[i][j]);
                }
            }
        }

        System.out.println(min);
    }

    public static void tagging(int x, int y, int num) {
        for(int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == -1) {
                map[nx][ny] = num;
                tagging(nx, ny, num);
            }
        }
    }

    static class Node {
        int x, y, dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void findMinDistanse(int x, int y, int num) {
        int[][] dist = new int[n][n];
        Queue<Node> q = new LinkedList<>();
        dist[x][y] = -1;
        q.offer(new Node(x, y, 0));

        while(!q.isEmpty()) {
            Node node = q.poll();

            for(int i = 0; i < 4; ++i) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < n && dist[nx][ny] == 0) {
                    if(map[nx][ny] == 0) {
                        dist[nx][ny] = node.dist + 1;
                        q.offer(new Node(nx, ny, dist[nx][ny]));
                    } else if(map[nx][ny] != num) {
                        min = Math.min(min, node.dist);
                        return;
                    }
                }
            }
        }
    }
}
