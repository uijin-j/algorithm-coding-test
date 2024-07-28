import java.util.*;

class Solution {
    // 남은 시간이 모두 비슷해야 함!
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int work : works) { // O(works.length) => 20,000
            pq.offer(work);
        }
        
        while(n > 0 && !pq.isEmpty()) { // O(n) => 1,000,000
            int max = pq.poll();
            max--; n--; // 1시간을 max 작업에 씀!
            
            if(max > 0) pq.offer(max);
        }
        
        long answer = 0;
        while(!pq.isEmpty()) {
            answer += (long) Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}