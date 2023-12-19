import java.util.*;

class Solution {
    static class Job {
        int requiredAt, cost; // 작업 요청 시간, 작업 소요 시간
        
        public Job(int requiredAt, int cost) {
            this.requiredAt = requiredAt;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] jobs) {
        int sum = 0; // 작업 요청 후 소요 시간 합
        int currentTime = 0;
        int countOfProcessed = 0; // 처리된 작업 수
        int next = 0; // 다음에 큐에 들어갈 작업
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // 시간 순으로 정렬
        PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost); // 작업큐
    
        while(countOfProcessed < jobs.length) {
            while(next < jobs.length && jobs[next][0] <= currentTime) {
                pq.offer(new Job(jobs[next][0], jobs[next][1]));
                next++;
            }
            
            if(pq.isEmpty()) {
                currentTime = jobs[next][0];
            } else {
                Job job = pq.poll();
                currentTime += job.cost; // 작업 수행
                sum += currentTime - job.requiredAt;  
                countOfProcessed++;
            }
        }
        
        return sum / jobs.length;
    }
}