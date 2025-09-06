import java.util.*;

/**
 * 22:24 시작!
 * (x, y)에서 (r, c)로 k 거리로 가기 (중복 방문 OK)
 * 경로가 사전 순으로 가장 빨라야 함 (d > l > r > u)
 * 탈출이 불가능한 경우 impossible
 * 
 * 완탐을 해야할 것 같음 --> 중복 방문이 가능하지만 거리가 k를 넘어서면 탐색이 종료되기 때문에 시간 초과가 안 날수도?
 */
class Solution {
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, -1, 1, 0 };
    String[] direction = { "d", "l", "r", "u" };
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        boolean[][][] check = new boolean[n+1][m+1][k+1];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0, ""));
        check[x][y][0] = true;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            for(int d = 0; d < 4; ++d) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                int nd = cur.dist + 1;
                
                if(nx > 0 && nx <= n && ny > 0 && ny <= m && !check[nx][ny][nd]) {
                    if(nd == k && nx == r && ny == c) {
                        return cur.path + direction[d];
                    }
                    
                    // 남은 거리로 절대 정답이 불가능하면 넘어가기
                    int dist = Math.abs(r - nx) + Math.abs(c - ny);
                    if(dist > k - nd) continue;
                    
                    check[nx][ny][nd] = true;
                    q.offer(new Node(nx, ny, nd, cur.path + direction[d]));
                }
            }
        }
        
        return "impossible";
    }
    
    public class Node {
        int x, y, dist;
        String path;
        
        public Node(int x, int y, int dist, String path) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.path = path;
        }
    }
}