import java.util.*;

class Solution {
    /** 
     * (0, 0)에서 (m-1, n-1)까지 가는 최단경로
     * but 제약이 존재 (경로 상의 t의 합이 s를 넘지 않아야 함)
     */
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    public int[] solution(int m, int n, int s, int[][] time_map) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, 0));
        int minDist = Integer.MAX_VALUE;
        int minTime = Integer.MAX_VALUE;
        long[][][] visit = new long[m][n][2];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                Arrays.fill(visit[i][j], Long.MAX_VALUE);
            }
        }
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            if(visit[node.x][node.y][0] <= node.dist && visit[node.x][node.y][1] <= node.time) {
                continue;
            }
            visit[node.x][node.y][0] = Math.min(visit[node.x][node.y][0], node.dist);
            visit[node.x][node.y][1] = Math.min(visit[node.x][node.y][1], node.time);
            
            
            if(node.x == m-1 && node.y == n-1) {
                if(minDist > node.dist) {
                    minDist = node.dist;
                    minTime = (int) node.time;
                }
                
                if(minDist == node.dist && minTime > node.time) minTime = (int) node.time;
            }
            
            for(int d = 0; d < 4; ++d) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && time_map[nx][ny] != -1) {
                    long nextTime = node.time + time_map[nx][ny];
                    if(nextTime > s) continue;
                    q.offer(new Node(nx, ny, node.dist + 1, nextTime));
                }
            }
        }
        
        return new int[]{minDist, minTime};
    }
    
    public class Node {
        int x, y, dist;
        long time;
        
        public Node(int x, int y, int dist, long time) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.time = time;
        }
    }
}