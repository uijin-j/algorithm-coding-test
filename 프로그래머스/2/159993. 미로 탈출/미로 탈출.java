import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    
    class Point {
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Point() {
        }
    }
    
    public int solution(String[] maps) {
        Point start = new Point(), end = new Point(), lever = new Point();
        for(int i = 0; i < maps.length; ++i) {
            for(char c: maps[i].toCharArray()) {
                if(c == 'S') start = new Point(i, maps[i].indexOf(c));
                else if(c == 'E') end = new Point(i, maps[i].indexOf(c));
                else if(c == 'L') lever = new Point(i, maps[i].indexOf(c));
            }
        }
        
        int d1 = bfs(start, lever, maps);
        if(d1 == -1) return -1;
        
        int d2 = bfs(lever, end, maps);
        if(d2 == -1) return -1;
        
        return d1 + d2;
    }
    
   private int bfs(Point start, Point end, String[] maps) {
       Queue<Point> q = new LinkedList<>();
       int n = maps.length;
       int m = maps[0].length();
       
       int[][] check = new int[n][m];
       
       check[start.x][start.y] = 1;
       q.add(start);
       
       while(!q.isEmpty()) {
           Point now = q.poll();
           
           for(int i = 0; i < 4; ++i) {
               int nx = now.x + dx[i];
               int ny = now.y + dy[i];
               
               if(nx >= 0 && nx < n && ny >= 0 && ny < m && check[nx][ny] == 0 && maps[nx].charAt(ny) != 'X') {
                   check[nx][ny] = check[now.x][now.y] + 1;
                   q.offer(new Point(nx, ny));
               }
           }
       }
       
       return check[end.x][end.y] - 1;
   }
}