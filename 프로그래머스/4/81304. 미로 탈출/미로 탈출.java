import java.util.*;

class Solution {
    // start -> end 최단거리
    // but 트랩이 있다는 것이 다름 (트랩을 방문하면 방향이 바뀜)
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int m = traps.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < m; ++i) {
            map.put(traps[i], i);
        }
        
        // u -> v 방향성을 저장해야 하는데, 트랩에 연결된 간선은 방향을 변경 가능해야 함!
        int[][] graph = new int[n+1][n+1]; // graph[i][j]: i -> j 거리 (0: 연결 X)
        for(int i = 0; i <= n; ++i) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        } 
        
        for(int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];
            
            graph[u][v] = Math.min(graph[u][v], d);
        }

        boolean[][] visit = new boolean[n+1][1 << m];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{ start, 0, 0 });
        
        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int cur = node[0];
            int state = node[1];
            int total = node[2];
            
            if(cur == end) return total;
            if(visit[cur][state]) continue;
            visit[cur][state] = true;
            
            boolean curTrapped = false;
            if(map.containsKey(cur)) {
                int trap = map.get(cur);
                state ^= (1 << trap); // cur의 트랩 상태 변경
                curTrapped = (state & (1 << trap)) > 0;
            }

            for(int next = 1; next <= n; ++next) {
                if(graph[cur][next] == Integer.MAX_VALUE && graph[next][cur] == Integer.MAX_VALUE) continue; // 연결 X
                
                boolean nextTrapped = false;
                if(map.containsKey(next) && ((state & (1 << map.get(next))) > 0)) { 
                    nextTrapped = true;
                }
                
                if(curTrapped == nextTrapped) { // 서로 같으면 방향이 그대로
                    if(graph[cur][next] == Integer.MAX_VALUE) continue;
                    pq.offer(new int[]{ next, state, total + graph[cur][next] });
                } else { // 서로 다르면 방향이 반대
                    if(graph[next][cur] == Integer.MAX_VALUE) continue;
                    pq.offer(new int[]{ next, state, total + graph[next][cur] });
                }
            }
        }
        
        return -1;
    }
}