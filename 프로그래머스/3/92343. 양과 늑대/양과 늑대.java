import java.util.*;

class Solution {
    /**
     * 그래프 모양이기 때문에 그래프 탐색(DFS or BFS)
     */
    int n, max;
    int[] info;
    List<Integer>[] tree;
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        
        n = info.length;
        tree = new List[n];
        for(int i = 0; i < n; ++i) {
            tree[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
        
        dfs(0, 0, 0, 1);
        
        return max;
    }
    
    // 매 탐색마다 자식들 + '부모가 갈 수 있었던 노드'들을 탐색해야 하기 때문에 possible이 따로 필요!
    public void dfs(int cur, int sheep, int wolf, int possible) {
        if(info[cur] == 0) sheep++;
        else wolf++;
        
        if(sheep <= wolf) return;
        max = Math.max(max, sheep);
        
        int next = possible; // Step 1. 부모가 갈 수 있었던 노드들을 복사
        next &= ~(1 << cur); // Step 2. 자신은 제거
        for(int child : tree[cur]) { // Step 3. 자식 추가
            next |= (1 << child);
        }
        
        for(int i = 0; i < n; ++i) {
            if((next & (1 << i)) == 0) continue;
            dfs(i, sheep, wolf, next);
        }
    }
}