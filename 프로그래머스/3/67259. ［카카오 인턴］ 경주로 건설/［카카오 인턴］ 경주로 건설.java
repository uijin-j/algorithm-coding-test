import java.util.*;

class Solution {
    // 최소비용으로 목적지에 가는 방법 => bfs?
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    public class Node {
        int x, y;
        int cost;
        int direction;
        
        public Node(int x, int y, int cost, int direction) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.direction = direction;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        int[][] dist = new int[n][n];
        for(int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, 1));
        q.offer(new Node(0, 0, 0, 2));
        int answer = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node node = q.poll();

            if(node.x == n-1 && node.y == n-1) {
                answer = Math.min(answer, node.cost);
                continue;
            }
            
            for(int i = 0; i < 4; ++i) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0) {
                    int cost = node.direction == i ? 100 : 600;
                    if(dist[nx][ny] == Integer.MAX_VALUE || cost < dist[nx][ny] + 500) {
                        dist[node.x][node.y] = node.cost;
                        q.offer(new Node(nx, ny, cost, i));
                    }
                }
            }
        }
        
        return answer;
    }
    
    public boolean hasCorner(int d1, int d2) {
        if(d1 == -1 || d2 == -1) return false;
        return dx[d1] * dx[d2] + dy[d1] * dy[d2] == 0;
    }
}