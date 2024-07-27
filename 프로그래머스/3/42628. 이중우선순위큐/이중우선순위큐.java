import java.util.*;

class Solution {
    public class DoublePriorityQueue {
        private PriorityQueue<Integer> asc; // 증가 (작은 것부터)
        private PriorityQueue<Integer> desc; // 감소 (큰 것부터)
        
        public DoublePriorityQueue() {
            this.asc = new PriorityQueue<>((a, b) -> a - b);
            this.desc = new PriorityQueue<>((a, b) -> b - a);
        }
        
        public void add(Integer e) {
            asc.add(e);
            desc.add(e);
        }
        
        public void deleteMax() {
            if(desc.isEmpty()) return;
            
            Integer toDelete = desc.poll();
            asc.remove(toDelete);
        }
        
        public void deleteMin() { 
            if(asc.isEmpty()) return;
            
            Integer toDelete = asc.poll();
            desc.remove(toDelete);
        }
        
        public int getMax() {
            return desc.isEmpty() ? 0 : desc.peek();
        }
        
        public int getMin() {
            return asc.isEmpty() ? 0 : asc.peek();
        }
    }
    
    public int[] solution(String[] operations) {
        DoublePriorityQueue pq = new DoublePriorityQueue();
        for(String operation : operations) {
            String command = operation.split(" ")[0];
            int number = Integer.parseInt(operation.split(" ")[1]);
            
            if(command.equals("I")) {
                pq.add(number);
                continue;
            }
            
            if(number == 1) {
                pq.deleteMax();
                continue;
            }
            
            pq.deleteMin();
        }
        
        return new int[]{ pq.getMax(), pq.getMin() };
    }
}