import java.util.*;

class Solution {
    public class Node {
        int number, index;
        
        public Node(int number, int index) {
            this.number = number;
            this.index = index;
        }
    }
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Deque<Node> stack = new ArrayDeque<>();
        
        for(int i = 0; i < numbers.length; ++i) {
            int number = numbers[i];
            while(!stack.isEmpty() && number > stack.peek().number) {
                Node node = stack.pop();
                answer[node.index] = number;
            }
            
            stack.push(new Node(number, i));
        }
        
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            answer[node.index] = -1;
        }
        
        return answer;
    }
}