import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long max = (long) times[times.length - 1] * n;
        long min = -1;
        long answer = max;
        
        while(min <= max) {
            long mid = (max + min) / 2;
            long sum = 0;
            
            for(int time : times) {
                sum += mid / time;
            }
            
            if(sum >= n) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        
        return answer;
    }
}