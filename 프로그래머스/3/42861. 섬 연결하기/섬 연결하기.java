import java.util.*;

class Solution {
    // 최단거리 문제 -> 다익스트라?
    // 근데, 지나온 길을 알아야함.. 오토에버 문제랑 비슷한 느낌..
    public class Node {
        int to;
        int cost;        
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    public int solution(int n, int[][] costs) {    
        List<List<Node>> list = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            list.add(new ArrayList<>());
        }
        
        for(int[] cost : costs) {
            list.get(cost[0]).add(new Node(cost[1], cost[2]));
            list.get(cost[1]).add(new Node(cost[0], cost[2]));
        }
        
        boolean[] visited = new boolean[n];
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.add(new Node(0, 0));
        int answer = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(visited[node.to]) continue; // 방문했으면 건너뛰기
            visited[node.to] = true;
            answer += node.cost;
            
            for(Node next : list.get(node.to)) {
                if(visited[next.to]) continue;
                pq.add(new Node(next.to, next.cost));
            }
        }
        
        return answer;
    }
}