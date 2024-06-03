import java.util.*;

class Solution {
    int[] count = new int[10_001];
    public int solution(int[] topping) {
        Set<Integer> lSet = new HashSet<>();
        Set<Integer> rSet = new HashSet<>();
        int len = topping.length;
        
        for(int i = 0; i < len; ++i) {
            rSet.add(topping[i]);
            count[topping[i]]++;
        }
        
        int answer = 0;
        for(int i = 0; i < len - 1; ++i) {
            lSet.add(topping[i]);
            count[topping[i]]--;
            if(count[topping[i]] == 0) {
                rSet.remove(topping[i]);   
            }
            
            if(lSet.size() == rSet.size()) {
                answer++;
            }
        }

        return answer;
    }
}