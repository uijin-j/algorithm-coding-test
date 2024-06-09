import java.util.*;

class Solution {
    public int solution(int[] order) {
        int n = order.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int belt = 1, count = 0;
        for(int box : order) {
            while(belt < box) {
                stack.push(belt++);
            }
            
            if(belt == box) {
                belt += 1;
                count++;
                continue;
            }
            
            if(!stack.isEmpty() && stack.peek() == box) {
                stack.pop();
                count++;
                continue;
            }
            
            return count;
        }
        
        return count;
    }
}