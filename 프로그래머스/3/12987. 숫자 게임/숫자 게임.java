import java.util.*;
import java.util.Collections.*;

class Solution {
    // 이길 수 있는 숫자들 중에서 가장 작은 숫자만 선택 (그리디)
    // ❗️ 이분탐색 내장 메서드를 사용하지 않도록 주의
    public int solution(int[] A, int[] B) {    
        List<Integer> list = new ArrayList<>();
        for(int b : B) list.add(b);
        
        Collections.sort(list);
        
        int answer = 0;
        for(int a : A) {
            int idx = searchFirstBiggerThan(a, list);
            if(idx >= list.size()) {
                list.remove(0);
                continue;
            }
            
            list.remove(idx);
            answer++;
        }
        
        return answer;
    }
    
    public int searchFirstBiggerThan(int target, List<Integer> list) {
        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = list.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(list.get(mid) > target) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}