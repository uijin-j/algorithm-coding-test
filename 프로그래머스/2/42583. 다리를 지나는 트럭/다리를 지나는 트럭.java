import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Deque<Integer> bridge = new ArrayDeque<>();
        int total = 0, timer = 0, idx = 0;
        while(idx < truck_weights.length) {
            if(bridge.size() == bridge_length) {
                total -= bridge.poll();
            }
            
            if(truck_weights[idx] + total > weight) {
                bridge.offer(0);
            } else {
                bridge.offer(truck_weights[idx]);
                total += truck_weights[idx];
                idx++;
            }
            
            timer++;
        }
        
        return timer + bridge_length;
    }
}