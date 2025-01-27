import java.util.*;

class Solution {
    // start -> end 최단거리
    // but 트랩이 있다는 것이 다름 (트랩을 방문하면 방향이 바뀜) => 즉, 같은 노드를 2번까지 밞을 수 있음!
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        Set<Integer> set = new HashSet<>();
        for(int trap : traps) set.add(trap);
        
        // u -> v 방향성을 저장해야 하는데, 트랩에 연결된 간선은 방향을 변경 가능해야 함!
        int[][] graph = new int[n+1][n+1]; // graph[i][j]: i -> j 거리 (0: 연결 X)
        for(int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];
            
            graph[u][v] = d;
            
            if(set.contains(v)) {
                graph[v][u] = -d;
            }
        }

        int m = traps.length;
        boolean[][] visit = new boolean[n+1][1 << (m + 3)];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        visit[start][0] = true;
        pq.offer(new int[]{ start, 0, 0 });
        
        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int cur = node[0];
            int state = node[1];
            int total = node[2];
            
            boolean curTrapped = false;
            if(set.contains(cur) && ((state & (1 << cur)) > 0)) {
                curTrapped = true;
            }
            
            for(int next = 1; next <= n; ++next) {
                if(graph[cur][next] == 0) continue; // 연결 X
                
                boolean nextTrapped = false;
                if(set.contains(next) && ((state & (1 << next)) > 0)) { 
                    nextTrapped = true;
                }
                
                if(curTrapped ^ nextTrapped) { // 서로 다르면 방향이 반대
                    if(graph[cur][next] > 0) continue;
                } else { // 서로 같으면 방향이 그대로
                    if(graph[cur][next] < 0) continue;
                }
                
                // next 번째 비트를 바꿔줘야 함!
                state ^= (1 << next);

                if(visit[next][state]) continue;
                visit[next][state] = true;
                
                if(next == end) {
                    return total + Math.abs(graph[cur][next]);
                }
                
                pq.offer(new int[]{ next, state, total + Math.abs(graph[cur][next]) });
            }
        }
        
        return -1;
    }
}