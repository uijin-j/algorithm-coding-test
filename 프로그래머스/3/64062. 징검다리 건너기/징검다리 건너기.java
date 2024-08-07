import java.util.*;

class Solution {
    // 이분탐색?
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200_000_001;
        int answer = 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(pass(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    // target 명의 사람이 지나갈 수 있나요?
    public boolean pass(int[] stones, int k, int target) {
        int count = 0;
        for(int i = 0; i < stones.length; ++i) {
            if(stones[i] - target < 0) {
                count++;
            } else {
                if(count >= k) return false;
                count = 0;
            }
        }
        
        return (count < k) ? true : false;
    }
}