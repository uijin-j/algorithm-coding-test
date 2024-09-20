import java.util.*;

// 20:25 START!
class Solution {
    /**
     * 노드(n)는 최대 101개
     * 처음 상태를 설정해야 하는데, 모든 경우를 생각해야 할까? 1번 노드 자식 수 * 2번 노드 자식 수 * ... * n번 노드 자식 수 
     * 일단 해보자..
     * 
     * 첫 상태는 가장 작은 노드로 고정되어 있었음..^^..🥲
     */
    public class Node {
        int number;
        List<Node> children;
        int idx; // 길인 간선이 가리키는 자식 노드의 인덱스
        
        public Node(int number) {
            this.number = number;
            this.children = new ArrayList<>();
            this.idx = 0;
        }
        
        public void addChild(Node child) {
            children.add(child);
        }
        
        public int pass() {
            if(children.size() == 0) { // leaf 노드
                return number;
            }
            
            Node child = children.get(idx);
            idx = (idx + 1) % children.size();
            return child.pass();
        }
    }
    
    public int[] solution(int[][] edges, int[] target) {
        Map<Integer, Node> numToNode = new HashMap<>();
        int n = target.length; // 노드의 수
        int toReach = 0; // 도달해야 하는 노드의 수
        
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            Node parent = numToNode.getOrDefault(from, new Node(from));
            Node child = numToNode.getOrDefault(to, new Node(to));
            numToNode.put(from, parent);
            numToNode.put(to, child);
            
            parent.addChild(child);
        }
        
        // 각 노드의 자식 노드를 오름차순 정렬
        for(int key : numToNode.keySet()) {            
            numToNode.get(key)
                .children
                .sort((a, b) -> a.number - b.number);
        }
        
        for(int t : target) {
            if(t > 0) toReach++;
        }
        
        // 게임 시작!
        int[] counts = new int[n+1]; // 각 노드에 숫자가 도달한 수를 카운트
        boolean[] check = new boolean[n+1]; // 만족시킨 노드를 체크하는 배열
        List<Integer> order = new ArrayList<>(); // 숫자가 도달하는 노드의 순서
        Node root = numToNode.get(1);
        while(true) {
            int leaf = root.pass(); // 도달한 리프 노드
            order.add(leaf);
            counts[leaf]++;
            
            if(counts[leaf] > target[leaf-1]) {
                // 정답을 만들 수 없음
                return new int[]{-1};
            }
            
            if(counts[leaf] * 3 >= target[leaf-1] && !check[leaf]) {
                // 이 노드는 이제 target을 만족시킬 수 있음
                check[leaf] = true;
                toReach--;
            }
            
            if(toReach == 0) {
                // 정답을 만들 수 있음!
                int[] answer = new int[order.size()];
                for(int i = 0; i < order.size(); ++i) { // 숫자 정하기
                    int node = order.get(i);
                    int num = Math.max(target[node-1] - (counts[node] - 1) * 3, 1); // 최대한 작은 수를 설정
                    answer[i] = num;
                    target[node-1] -= num;
                    counts[node]--;
                }
                
                return answer;
            }
        }
    }
}