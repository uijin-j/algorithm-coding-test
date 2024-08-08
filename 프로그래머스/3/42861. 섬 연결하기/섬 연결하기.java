import java.util.*;

class Solution {
    // 최단거리 문제 ... X 최소 스패닝 트리 문제!
    // MST:  최소 비용으로 모든 노드를 연결하고자 할 때 사용 (크루스칼/프림 알고리즘)
    // 다익스트라: 단일 출발점에서 다른 모든 정점까지의 최단 경로 찾기
    int[] parent;
    public int solution(int n, int[][] costs) {
        // 크루스칼 알고리즘
        Arrays.sort(costs, (a, b) -> a[2] - b[2]); // 엣지를 비용을 기준으로 오름차순
        
        parent = new int[n];
        for(int i = 0; i < n; ++i) {
            parent[i] = i;
        }
        
        int answer = 0;
        for(int[] cost : costs) { // 가장 작은 비용부터 선택
            if(find(cost[0]) != find(cost[1])) { // 아직 같은 그래프에 있지 않으면!
                union(cost[0], cost[1]);
                answer += cost[2];
            }
        }
        
        return answer;
    }
    
    public int find(int x) {
        if(parent[x] == x) return x;
        return find(parent[x]);
    }
    
    public void union(int a, int b) {
        int parentA = find(a);
        int patentB = find(b);
        
        if(parentA == patentB) return;
        parent[patentB] = parentA;
    }
}