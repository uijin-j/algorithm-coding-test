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
        
        List<Integer> possible = new ArrayList<>();
        possible.add(0);
        dfs(0, 0, 0, possible);
        
        return max;
    }
    
    public void dfs(int cur, int sheep, int wolf, List<Integer> possible) {
        if(info[cur] == 0) sheep++;
        else wolf++;
        
        if(sheep <= wolf) return;

        max = Math.max(max, sheep);
        
        List<Integer> next = new ArrayList<>();
        next.addAll(possible);
        next.remove(Integer.valueOf(cur));
        for(int child : tree[cur]) {
            next.add(child);
        }
        
        for(int i : next) {
            dfs(i, sheep, wolf, next);
        }
    }
}