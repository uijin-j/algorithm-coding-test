import java.util.*;

class Solution {
    // 1번 노드부터 모든 노드까지 최단거리를 구한 뒤, 그 값들 중 가장 긴 거리를 선택?
    // O(nlogm)
    public class Node {
        int to;
        int cost;
        
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    public int solution(int n, int[][] edge) {
        List<List<Node>> list = new ArrayList<>();
        for(int i = 0; i <= n; ++i) list.add(new ArrayList<>());
        for(int[] e : edge) {
            list.get(e[0]).add(new Node(e[1], 1));
            list.get(e[1]).add(new Node(e[0], 1));
        }
        
        boolean[] check = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Node(1, 0));
        int max = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(check[node.to]) continue;
            dist[node.to] = node.cost;
            check[node.to] = true;
            max = Math.max(max, dist[node.to]);
            
            for(Node next : list.get(node.to)) {
                if(!check[next.to] && dist[next.to] > node.cost + next.cost) {
                    pq.offer(new Node(next.to, node.cost + next.cost));
                }
            }
        }
        
        int count = 0;
        for(int d : dist) {
            if(d == max) count++;
        }
        
        return count;
    }
}