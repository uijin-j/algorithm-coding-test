import java.util.*;

class Solution {
    /**
     * (0,0) → (m-1, n-1)로 가는 최단거리 ==> 완탐? O(nm)
     * 그런데.. 지나온 길의 t의 합이 s를 넘지 않아야 함 ==> 기존에 왔던 sum(t')가 현재 sum(t)보다 크면, 재방문 가능!
     */
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    public int[] solution(int m, int n, int s, int[][] time_map) {
        long[][] visit = new long[m][n];
        for(int i = 0; i < m; ++i) Arrays.fill(visit[i], Long.MAX_VALUE);
        
        Queue<Node> q = new LinkedList<>();
        visit[0][0] = 0;
        q.offer(new Node(0, 0, 0, 0));
        
        int len = Integer.MAX_VALUE;
        int time = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            if(cur.x == m-1 && cur.y == n-1) {
                if(len > cur.cnt) {
                    len = cur.cnt;
                    time = (int) cur.time;
                }
                
                if(len == cur.cnt && time > cur.time) {
                    time = (int) cur.time;
                }
            }
            
            for(int d = 0; d < 4; ++d) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && time_map[nx][ny] != -1) {
                    long nextTime = cur.time + (long) time_map[nx][ny];
                    if(nextTime <= s && nextTime < visit[nx][ny]) {
                        visit[nx][ny] = nextTime;
                        q.offer(new Node(nx, ny, cur.cnt + 1, nextTime));
                    }
                }
            }
        }
        
        return new int[]{len, time};
    }
    
    public class Node {
        int x, y, cnt;
        long time;
        
        public Node(int x, int y, int cnt, long time) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.time = time;
        }
    }
}