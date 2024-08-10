import java.util.*;

class Solution {
    // 최대한 적게 기다리도록 해야 함
    public int solution(int[][] jobs) {
        int n = jobs.length;
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int t = 0;
        int idx = 0;
        int sum = 0;
        while(idx < n || !pq.isEmpty()) {
            if(pq.isEmpty() && t < jobs[idx][0]) {
                t = jobs[idx][0]; 
            }
            
            while(idx < n && jobs[idx][0] <= t) {
                pq.offer(jobs[idx++]);
            }
            
            int[] job = pq.poll();
            t += job[1];
            sum += t - job[0];
        }
        
        return sum / n;
    }
}