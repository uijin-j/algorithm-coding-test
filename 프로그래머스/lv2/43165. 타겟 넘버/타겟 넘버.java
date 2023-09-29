import java.util.*;

class Solution {
    int answer = 0;
    
    public void dfs(int l, int sum, int[] numbers, int target) {
        if(l == numbers.length) {
            if(sum == target) answer++;
            return;
        }
        
        dfs(l+1, sum + numbers[l], numbers, target);
        dfs(l+1, sum - numbers[l], numbers, target);
    }
    
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }
}