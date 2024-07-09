import java.util.*;

class Solution {
    public String solution(String p) {
        return convert(p);
    }
    
    public String convert(String input) {
        if(input.equals("")) return "";
        
        StringBuilder sb = new StringBuilder();
        int idx = split(input);
        String u = input.substring(0, idx + 1);
        String v = input.substring(idx + 1);
        
        if(isValid(u)) {
            return sb.append(u)
                .append(convert(v))
                .toString();
        }
        
        return sb.append("(")
            .append(convert(v))
            .append(")")
            .append(cutAndReverse(u))
            .toString();
    }
    
    public boolean isValid(String u) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for(char ch : u.toCharArray()) {
            if(stack.isEmpty()) {
                stack.push(ch);
                continue;
            }
            
            if(stack.peek() == '(' && ch == ')') stack.pop();
            else stack.push(ch);
        }
        
        return stack.isEmpty();
    }
    
    public int split(String input) {
        int left = 0;
        int right = 0;
        int idx = 0;
        
        for(char ch : input.toCharArray()) {
            if(ch == '(') {
                left++;
            }
            else right++;
            
            if(left == right) return idx;
            idx++;
        }
        
        return input.length() - 1;
    }
    
    public String cutAndReverse(String input) {
        if(input.length() <= 2) return "";
        
        String truncated = input.substring(1, input.length() - 1);
        StringBuilder sb = new StringBuilder();
        for(char ch : truncated.toCharArray()) {
            if(ch == '(') sb.append(")");
            else sb.append("(");
        }
        
        return sb.toString();
    }
}