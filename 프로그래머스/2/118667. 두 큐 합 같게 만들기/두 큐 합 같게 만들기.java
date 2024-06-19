import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
    
        long totoal = 0, sum1 = 0, sum2 = 0;
        for(int i = 0; i < n; ++i) {
            // Q1
            totoal += queue1[i];
            sum1 += queue1[i];
            q1.offer(queue1[i]);
            
            // Q2
            totoal += queue2[i];
            sum2 += queue2[i];
            q2.offer(queue2[i]);
        }
        
        int count = 0;
        while(sum1 != sum2) {
            if(count > 4 * n) {
                return -1;
            }
            
            if(sum1 < sum2) {
                int e = q2.poll();
                sum1 += e;
                sum2 -= e;
                q1.offer(e);
            } else {
                int e = q1.poll();
                sum1 -= e;
                sum2 += e;
                q2.offer(e);
            }
            
            count++;
        }
        
        return count;
    }
}