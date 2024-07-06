import java.util.*;

class Solution {
    static class Node {
        int to, time;
        
        public Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }
    
    // 한 노드에서 다른 노드까지의 최단거리를 구하는 문제 -> 다익스트라
    public int solution(int N, int[][] road, int K) {
        int[][] dist = new int[N+1][N+1];
        for(int i = 0; i <= N; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        
        for(int[] info : road) {
            int from = info[0];
            int to = info[1];
            int time = info[2];
            
            dist[from][to] = Math.min(dist[from][to], time);
            dist[to][from] = Math.min(dist[to][from], time);
        }
        
        boolean[] visited = new boolean[N+1]; // 방문체크
        int[] mins = new int[N+1]; // 최단거리
        Arrays.fill(mins, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        pq.offer(new Node(1, 0));
        visited[1] = true;
        mins[1] = 0;
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(!visited[node.to]) {
                visited[node.to] = true;
                mins[node.to] = node.time;
            }
            
            for(int i = 1; i <= N; ++i) {
                if(dist[node.to][i] != Integer.MAX_VALUE && !visited[i]) {
                    pq.offer(new Node(i, Math.min(dist[1][i] , node.time + dist[node.to][i])));
                }
            }
        }
        
        int answer = 0;
        for(int i = 1; i <= N; ++i) {
            if(mins[i] <= K) {
                answer++;
            }
        }
        
        
        return answer;
    }
}