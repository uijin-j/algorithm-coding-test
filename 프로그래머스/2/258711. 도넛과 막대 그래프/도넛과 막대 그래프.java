import java.util.*;

class Solution {
    int[] answer;
    
    public int[] solution(int[][] edges) {
        // 1. 생성한 정점 찾기: 들어오는 엣지 X / 나가는 엣지 2개 이상
        // 2. 생성한 정점이 가르키는 것이 각 그래프
        // 3. 그래프의 한 점이 주어졌을 때, 어떤 그래프인지 확인하기
        answer = new int[4];
        Map<Integer, List<Integer>> out = new HashMap<>(); // out.get(i): i에서 갈 수 있는 노드
        Map<Integer, List<Integer>> in = new HashMap<>(); // in.get(i): i로 들어오는 노드
        for(int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            
            List<Integer> list = out.getOrDefault(from, new ArrayList<>());
            list.add(to);
            out.put(from, list);
            
            list = in.getOrDefault(to, new ArrayList<>());
            list.add(from);
            in.put(to, list);
        }
        
        // 1. 생성한 정점 찾기: 나가는 엣지 2개 이상 + 들어오는 엣지 X / 
        for(int key: out.keySet()) {
            if(out.get(key).size() >= 2 && !in.containsKey(key)) {
                answer[0] = key;
                break;
            }
        }
        
        for(int node : out.get(answer[0])) decideType(node, node, in, out);
        
        return answer;
    }
    
    private void decideType(int node, int first, Map<Integer, List<Integer>> in, Map<Integer, List<Integer>> out) {
        if(!out.containsKey(node) || !in.containsKey(node)) {
            answer[2] += 1;
            return;
        }
        
        if(out.get(node).size() > 1) {
            answer[3] += 1;
            return;
        }
        
        int next = out.get(node).get(0);
        if(next == first) {
            answer[1] += 1;
            return;
        }
        
        decideType(next, first, in, out);
    }
}