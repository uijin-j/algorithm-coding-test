class Solution {
    int count = 0;
    boolean[] col;
    int[] select;
    public int solution(int n) {
        col = new boolean[n];
        select = new int[n];
        
        dfs(0, n);
        
        return count;
    }
    
    public void dfs(int x, int n) {
        if(x == n) {
            count++;
            return;
        }
        
        for(int y = 0; y < n; ++y) {
            if(col[y]) continue;
            
            boolean possible = true;
            for(int i = 0; i < x; ++i) {
                int difX = Math.abs(x - i);
                int difY = Math.abs(y - select[i]);
                
                if(difX == difY) {
                    possible = false;
                    break;
                }
            }
            
            if(!possible) continue;
            
            col[y] = true;
            select[x] = y;
            dfs(x + 1, n);
            col[y] = false;
        }
    }
}