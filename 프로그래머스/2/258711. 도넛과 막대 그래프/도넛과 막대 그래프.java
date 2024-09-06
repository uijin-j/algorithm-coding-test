import java.util.*;

// 00:09 START!
// 그래프 탐색
class Solution {
    public int[] solution(int[][] edges) {
        /**
         * 💡 생성한 정점은 어떻게 구할까?
         *  - in이 없고 out만 2개 이상 (*도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 수의 합은 2이상)
         *
         * 💡 모든 그래프의 특징을 정리해보자
         *  - 도넛 모양 그래프: 모든 정점이 in 1 / out 1 (돌다보면 자신으로 돌아옴)
         *  - 막대 모양 그래프: 정점이 in 1 / out 1 but 시작 정점은 out 1, 마지막 정점은 in 1 (돌다보면 out이 없는 정점이 있음)
         *  - 8자 모양 그래프: 모든 정점이 in 1 / out 1 but 중간에 in 2 / out 2 정점이 존재 (돌다보면 in 2 / out 2 정점이 있음)
         */
        int[] answer = new int[4];
        Map<Integer, List<Integer>> gragh = new HashMap<>(); // gragh.get(i): i에서 갈 수 있는 노드
        int[] inCount = new int[1000001]; // inCount[i]: i로 들어오는 노드 수
        int[] outCount = new int[1000001]; // outCount[i]: i에서 나가는 노드 수
        for(int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            
            List<Integer> list = gragh.getOrDefault(from, new ArrayList<>());
            list.add(to);
            gragh.put(from, list);
            
            outCount[from]++;
            inCount[to]++;
        }
        
        // 1. 생성한 정점 찾기
        for(int node : gragh.keySet()) {
            if(inCount[node] == 0 && outCount[node] >= 2) {
                answer[0] = node;
                break;
            }
        }
        
        // 2. 정점이 가르키는 점들이 속한 그래프 종류 구하기
        for(int node : gragh.getOrDefault(answer[0], new ArrayList<>())) {
            inCount[node]--;
            answer[decideType(node, node, gragh, inCount, outCount)]++;
        }
        
        return answer;
    }
    
    private int decideType(int node, int first, Map<Integer, List<Integer>> gragh, int[] inCount, int[] outCount) {
        // 막대 타입
        if(inCount[node] == 0 || outCount[node] == 0) return 2;
      
        // 8자 타입
        if(outCount[node] == 2) return 3;
        
        int next = gragh.get(node).get(0);
        
        // 도넛 타입
        if(next == first) return 1;
        
        return decideType(next, first, gragh, inCount, outCount);
    }
}