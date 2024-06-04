import java.util.*;

class Solution {
    public class Element {
        int price, index;
        
        public Element(int price, int index) {
            this.price = price;
            this.index = index;
        }
    }
    
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Element> stack = new ArrayDeque<>();
        for(int i = 0; i < prices.length; ++i) {
            int price = prices[i];
            while(!stack.isEmpty() && stack.peek().price > price) {
                Element e = stack.pop();
                answer[e.index] = i - e.index;
            }
            
            stack.push(new Element(price, i));
        }
        
        while(!stack.isEmpty()) {
            Element e = stack.pop();
            answer[e.index] = (prices.length - 1) - e.index;
        }
        
        return answer;
    }
}