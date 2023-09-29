class Solution {
    boolean[] check;
    
    public void dfs(int com, int[][] computers) {
        check[com] = true;
        
        for(int i = 0; i < computers.length; ++i) {
            if(com != i && computers[com][i] == 1 && !check[i]) {
                check[i] = true;
                dfs(i, computers);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        check = new boolean[n];
        
        for(int i = 0; i < n; ++i) {
            if(check[i]) continue;
            answer++;
            dfs(i, computers);
        }
        return answer;
    }
}