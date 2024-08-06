import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> types = new HashSet<>();
        for(String gem : gems) types.add(gem);
        
        int total = types.size(); // total 종류의 보석이 있음
        Map<String, Integer> count = new HashMap<>();
        int length = gems.length;
        int[] answer = new int[]{1, length};
        int left = 0, right = 0;
        while(right < gems.length) {
            count.put(gems[right], count.getOrDefault(gems[right], 0) + 1);
            
            while(count.get(gems[left]) > 1) { // ❗️ 얘를 while로 해야함! 새로운 left도 확인해야 해서!
                count.put(gems[left], count.get(gems[left]) - 1);
                left++;
            }
            
            if(count.size() == total && length > (right - left + 1)) { // ❗️ 조건에 만족하고, 길이가 더 짧으면 선택!
                length = right - left + 1;
                answer = new int[]{left + 1, right + 1};
            }
            
            right++;
        }
        
        return answer;
    }
}