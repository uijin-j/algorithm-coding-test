class Solution {
    public boolean[] isPlus;
    public int count;
    public int solution(int[] numbers, int target) {
        isPlus = new boolean[numbers.length];
        count = 0;
        
        dfs(0, target, numbers.length, numbers); 
        
        return count;
    }
    
    public void dfs(int start,int target, int n, int[] numbers) {        
        if(start == n) {
            int result = 0;
            for(int i = 0; i < n; ++i) {
                if(isPlus[i]) result += numbers[i];
                else result -= numbers[i];
            }
            
            if(result == target) ++count;
            return;
        }
        
        isPlus[start] = true;
        dfs(start + 1, target, n, numbers);
        isPlus[start] = false;
        dfs(start + 1, target,  n,numbers);
    }
}