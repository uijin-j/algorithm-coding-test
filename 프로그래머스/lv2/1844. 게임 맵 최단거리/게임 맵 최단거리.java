import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int n, m;
    int[][] dis;
    
    class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    
    public void bfs(int x, int y, int[][] maps) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        maps[x][y] = 0;
        dis[x][y] = 1;
        
        while(!q.isEmpty()) {
            Point now = q.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            for(int i = 0; i < 4; ++i) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
            
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1) {
                    maps[nx][ny] = 0;
                    dis[nx][ny] = dis[nowX][nowY] + 1;
                    q.offer(new Point(nx, ny));
                }
            }  
        }
    }
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        dis = new int[n][m];
        
        bfs(0, 0, maps);
        
        if(dis[n-1][m-1] == 0) return -1;
        return dis[n-1][m-1];
    }
}