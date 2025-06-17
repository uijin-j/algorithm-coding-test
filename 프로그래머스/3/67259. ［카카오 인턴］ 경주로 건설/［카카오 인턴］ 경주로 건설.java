import java.util.*;

// 00:52 시작!
class Solution {
    /**
     * 최소 비용으로 (0,0) → (n-1,n-1)까지 가기? BFS
     */
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    int[][][] min;
    public int solution(int[][] board) {
        int n = board.length;
        min = new int[n][n][4];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                Arrays.fill(min[i][j], Integer.MAX_VALUE);   
            }
        }
        for(int i = 0; i < 4; ++i) min[0][0][i] = 0;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, -1));
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int d = cur.direction;
            int cost = cur.cost;

            for(int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0) {
                    int nextCost = cur.cost + ((cur.direction == i || cur.direction == -1) ? 100 : 600);
                    if(nextCost < min[nx][ny][i]) {
                        q.offer(new Node(nx, ny, nextCost,i));
                         min[nx][ny][i] = nextCost;
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < 4; ++i) {
            answer = Math.min(answer, min[n-1][n-1][i]);
        }
        
        return answer;
    }
    
    public class Node {
        int x, y, cost, direction;
        
        public Node(int x, int y, int cost, int direction) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.direction = direction;
        }
    }
}