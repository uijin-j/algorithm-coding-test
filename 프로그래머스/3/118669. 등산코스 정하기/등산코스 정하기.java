import java.util.*;

// 15:41 START! 16:27 END! (50분)
class Solution {
    /**
     * 모든 산 봉우리에서 게이트로 가는 최소 intensity를 구하고 그 중에 최솟값을 리턴! => O(n * nlogn) ~= 2_500_000_000 애매하네..
     * 
     * 다익스트라와 비슷하게 최소 intensity를 구하지만, 거리가 아닌 intensity라는 점이 다름!
     */
    public class Node {
        int node;
        int intensity;
        
        public Node(int node, int intensity) {
            this.node = node;
            this.intensity = intensity;
        }
    }
    
    List<List<Node>> graph;
    Set<Integer> gateSet, summitSet; // 정상에서 탐색할 때, 다른 정상은 가면 안되고 게이트를 발견하면 탐색을 중단해야 하기 때문에 필요!
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[]{ 0, Integer.MAX_VALUE };
        
        graph = new ArrayList<>();
        for(int i = 0; i <= n; ++i) graph.add(new ArrayList<>());
        for(int[] path : paths) {
            int n1 = path[0];
            int n2 = path[1];
            int time = path[2];
            
            graph.get(n1).add(new Node(n2, time));
            graph.get(n2).add(new Node(n1, time));
        }
        
        gateSet = new HashSet<>();
        for(int gate : gates) gateSet.add(gate);
        
        summitSet = new HashSet<>();
        for(int summit : summits) summitSet.add(summit);
        
        for(int summit : summits) {
            int intensity = findBestIntensity(summit, n); // summit에서 any gate로 가는 최단 intensity
            
            if(intensity < answer[1]) {
                answer[0] = summit;
                answer[1] = intensity;
            } else if(intensity == answer[1]) answer[0] = Math.min(answer[0], summit);
        }
        
        return answer;
    }
    
    public int findBestIntensity(int summit, int n) {
        boolean[] check = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.intensity - b.intensity); // intensity가 작은 애가 먼저 나옴!
        pq.offer(new Node(summit, 0));
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(check[node.node]) continue;
            check[node.node] = true;
            
            if(gateSet.contains(node.node)) {
                return node.intensity;
            }
            
            for(Node next : graph.get(node.node)) {
                if(!check[next.node] && !summitSet.contains(next.node)) {
                    pq.offer(new Node(next.node, Math.max(node.intensity, next.intensity)));
                }
            }
        }
        
        return Integer.MAX_VALUE;
    }
}