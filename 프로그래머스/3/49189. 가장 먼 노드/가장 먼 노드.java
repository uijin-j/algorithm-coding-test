import java.util.*;

class Solution {
    public class Node {
        int n, dist;
        
        public Node(int n, int dist) {
            this.n = n;
            this.dist = dist;
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<List<Integer>> list = new ArrayList<>(); // list.get(i)는 i와 연결된 노드
        for(int i = 0; i <= n; ++i) {
            list.add(new ArrayList<>());
        }
        
        for(int[] e : edge) {
            list.get(e[0]).add(e[1]);
            list.get(e[1]).add(e[0]);
        }
        
        int[] dist = new int[n+1];
        for(int i = 0; i <= n; ++i) {
            dist[i] = Integer.MAX_VALUE;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.add(new Node(1, 0));
        int max = 1;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(dist[node.n] != Integer.MAX_VALUE) continue;
            dist[node.n] = node.dist;
            max = Math.max(max, dist[node.n]);
            
            for(int next : list.get(node.n)) {
                if(dist[node.n] + 1 < dist[next]) {
                    pq.add(new Node(next, dist[node.n] + 1));
                }
            }
        }
        
        int count = 0;
        for(int i = 1; i <= n; ++i) {
            if(dist[i] == max) count++;
        }
        
        return count;
    }
}