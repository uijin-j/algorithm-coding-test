import java.util.*;

class Solution {
    // 경유지를 1개 선택 O(n) ~= 200
    // 해당 경유지까지 함께 택시를 탔을 때 최소 요금 계산 S -> K + K -> A + K -> B
    
    List<List<Node>> graph;
    int MAX = 20_000_000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList<>();
        for(int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];
            
            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }
        
        int[] distFromS = new int[n+1];
        int[] distFromA = new int[n+1];
        int[] distFromB = new int[n+1];
        
        findDist(s, distFromS);
        findDist(a, distFromA);
        findDist(b, distFromB);
        
        int answer = MAX;
        for(int i = 1; i <= n; ++i) { // i를 경유했을 때 최소 비용
            int total = distFromS[i] + distFromA[i] + distFromB[i];
            answer = Math.min(answer, total);
        }
        
        return answer;
    }
    
    public class Node {
        int to, cost;
        
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    public void findDist(int start, int[] dist) {
        Arrays.fill(dist, MAX);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(dist[cur.to] < cur.cost) continue;
            dist[cur.to] = cur.cost;
            
            for(Node next : graph.get(cur.to)) {
                if(dist[next.to] > cur.cost + next.cost) {
                    dist[next.to] = cur.cost + next.cost;
                    pq.offer(new Node(next.to, cur.cost + next.cost));
                }
            }
        }
    }
}