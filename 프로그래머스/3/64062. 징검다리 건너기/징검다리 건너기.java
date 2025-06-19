import java.util.*;

// 23:31 시작!
class Solution {
    /**
     * 완탐? O(200_000 * 200_000_000) 시간초과
     * K 크기의 구간들 중 구간의 최댓값이 최소인 구간을 찾기!
     * 투 포인터? 
     */
    public int solution(int[] stones, int k) {
        int n = stones.length;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.num - a.num);
        for(int i = 0; i < k; ++i) {
            pq.offer(new Node(i, stones[i]));
        }
        
        int min = pq.peek().num;
        for(int to = k, from = 1; to < n; ++to, ++from) {
            pq.offer(new Node(to, stones[to]));
            while(pq.peek().idx < from) {
                pq.poll();
            }
            
            min = Math.min(min, pq.peek().num);
        }
        
        return min;
    }
    
    public class Node {
        int idx, num;
        
        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
}