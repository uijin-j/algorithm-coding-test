class Solution {
    boolean[] check;
    
    public void dfs(int node, int n, int[][] computers) {
        check[node] = true;
        
        for(int i = 0; i < n; ++i) {
            if(computers[node][i] == 1 && !check[i]) {
                dfs(i, n, computers);
            }
        }
    
    }
    
    public int solution(int n, int[][] computers) {
        check = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; ++i) {
            if(check[i]) continue;
            dfs(i, n, computers);
            count++;
        }
        
        return count;
    }
}