import java.util.*;

// 22:16 시작!
class Solution {
    /**
     * 그래프 BFS?
     * 현재 순서로 인해 방문할 수 없는 노드는 temp에 넣어놓고 추후 다른 노드를 방문 후 재확인
     */
    public boolean solution(int n, int[][] path, int[][] order) {
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; ++i) graph[i] = new ArrayList<>();
        for(int[] edge : path) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        int[] pre = new int[n];
        int[] after = new int[n];
        for(int[] o : order) {
            pre[o[1]] = o[0];
            after[o[0]] = o[1];
        }
        
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> temp = new HashSet<>();
        if(pre[0] != 0) return false;
        visited[0] = true;
        q.add(0);
        while(!q.isEmpty()) {
            int node = q.poll();
            
            for(int next : graph[node]) {
                if(visited[next]) continue;
                if(!visited[pre[next]]) {
                    temp.add(next);
                    continue;
                }
                
                visited[next] = true;
                q.offer(next);
                
                if(temp.contains(after[next])) {
                    visited[after[next]] = true;
                    q.offer(after[next]);
                    temp.remove(after[next]);
                }
            }
        }
        
        for(int i = 0; i < n; ++i) {
            if(!visited[i]) return false;
        }
        
        return true;
    }
}