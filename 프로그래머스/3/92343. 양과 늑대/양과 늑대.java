import java.util.*;

// 18:55 START!
class Solution {
    /**
     * 이분탐색? X
     * 그리디? 항상 가장 가까운 양(만약 양이 없으면 가장 양과 가까운 늑대)을 데려가다가, 갈 수 없으면 그대로 끝!
     * 1. 모든 늑대 노드에서 가장 가까운 양까지 거리 구하기 (아래 방향으로만) O(n^2)
     * 2. 루트 노드부터 bfs with 우선순위 큐
     */
    public class Node {
        int num;
        int type; // 양은 0, 늑대는 1
        int distToSheep; // 가장 가까운 양까지의 거리
        int numOfSheep; // 가장 가까운 양의 수
        
        public Node(int num, int type, int dist, int numOfSheep) {
            this.num = num;
            this.type = type;
            this.distToSheep = dist;
            this.numOfSheep = numOfSheep;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            tree.add(new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
        }
        
        Map<Integer, Node> map = new HashMap<>();
        for(int i = 0; i < n; ++i) {
            int type = info[i];
            int[] distAndCount = calculateDistAndCount(i, tree, info);
            map.put(i, new Node(i, type, distAndCount[0], distAndCount[1]));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.distToSheep - b.distToSheep);
        
        pq.offer(map.get(0));
        
        int sheep = 0;
        int wolf = 0;
        while(!pq.isEmpty()) {
            int possible = Math.max(0, sheep - 1 - wolf);
            
            PriorityQueue<Node> pq2 = new PriorityQueue<>((a, b) -> b.numOfSheep - a.numOfSheep);
            while(!pq.isEmpty() && pq.peek().distToSheep <= possible) {
               pq2.offer(pq.poll());
            }
            
            if(pq2.isEmpty()) break;
            Node node = pq2.poll();
            
            while(!pq2.isEmpty()) {
               pq.offer(pq2.poll());
            }
            
            if(node.type == 0) {
                sheep += 1;
            } else {
                wolf += 1;
                if(sheep <= wolf) break;
            }
            
            for(int next : tree.get(node.num)) {
                pq.offer(map.get(next));
            }
        }
        
        return sheep;
    }
    
    public int[] calculateDistAndCount(int start, List<List<Integer>> tree, int[] info) {
        if(info[start] == 0) return new int[]{0, 1};
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int min = Integer.MAX_VALUE;
        int count = 0;
        int dist = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            
            for(int i = 0; i < size; ++i) {
                int node = q.poll();
                
                for(int next : tree.get(node)) {
                    if(info[next] == 0) {
                        min = Math.min(min, dist);
                        count++;
                    };
                    
                    q.offer(next);
                }
            }
            
            dist++;
        }
        
        return new int[]{min, count};
    }
}