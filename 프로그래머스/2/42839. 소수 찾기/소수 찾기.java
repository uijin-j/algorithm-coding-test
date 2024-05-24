import java.util.*;

class Solution {
    public boolean[] isSelected;
    public Set<Integer> set;
    public int count;
    public int solution(String numbers) {
        isSelected = new boolean[numbers.length()];
        set = new HashSet<>();
        count = 0;
    
        for(int i = 1; i <= numbers.length(); ++i) {
            dfs(0, i, "", numbers);   
        }
        
        return count;
    }
    
    public void dfs(int cnt, int n, String s, String numbers) {
        if(cnt == n) {
            int number = Integer.parseInt(s);
            if(set.contains(number)) return;
            if(isPrime(number)) {
                set.add(number);
                count++;
            }
            
            return;
        }
        
        for(int i = 0; i < numbers.length(); ++i) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                dfs(cnt + 1, n, s + numbers.charAt(i), numbers);
                isSelected[i] = false;
            }
        }
    }
    
    public boolean isPrime(int number) {
        if(number <= 1) return false;
        
        for(int i = 2; i <= Math.sqrt(number); ++i) {
            if(number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}