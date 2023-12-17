import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int level: scoville) {
            pq.offer(level);
        }
        
        while(true) {
            if(pq.peek() >= K) {
                break;
            }
            
            if(pq.size() == 1) return -1;
            
            int first = pq.poll();
            int second = pq.poll();
            
            int mixed = first + (second * 2);
            pq.offer(mixed);
            
            answer++;
        }
        
        return answer;
    }
}