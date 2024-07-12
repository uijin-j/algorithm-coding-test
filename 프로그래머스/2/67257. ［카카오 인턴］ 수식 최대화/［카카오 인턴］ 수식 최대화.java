import java.util.*;

class Solution {
    String[][] cases = {
        {"*", "+", "-"}, 
        {"*", "-", "+"}, 
        {"+", "*", "-"}, 
        {"+", "-", "*"}, 
        {"-", "+", "*"}, 
        {"-", "*", "+"}
    };
    
    public long solution(String expression) {
        long max = Long.MIN_VALUE;
        List<String> opers = new ArrayList<>();
        
        long number = 0;
        for(char ch : expression.toCharArray()) {
            if(ch == '*' || ch == '-' || ch == '+') {
                opers.add(String.valueOf(number));
                opers.add(String.valueOf(ch));
                number = 0;
                continue;
            }
            
            number *= 10;
            number += ch - '0';
        }
        
        opers.add(String.valueOf(number));
        
        for(int i = 0; i < 6; ++i) {
            List<String> copy = new ArrayList<>();
            copy.addAll(opers);
            
            for(int j = 0; j < 3; ++j) {
                String op = cases[i][j];
                
                for(int k = 0; k < copy.size(); ++k) {
                    String oper = copy.get(k);
                        
                    if(oper.equals(op)) {
                        String operand1 =  copy.get(k-1);
                        String operand2 =  copy.get(k+1);
                        long result = calculate(Long.parseLong(operand1), op, Long.parseLong(operand2));
                        copy.remove(k-1);
                        copy.remove(k-1);
                        copy.remove(k-1);
                        copy.add(k-1, String.valueOf(result));
                        --k;
                    }
                }
            }
            
            max = Math.max(max, Math.abs(Long.parseLong(copy.get(0))));
        }
        
        
        return max;
    }
    
    public long calculate(long operand1, String op, long operand2) {
        if(op.equals("*")) return operand1 * operand2;
        if(op.equals("+")) return operand1 + operand2;
        if(op.equals("-")) return operand1 - operand2;
        
        return -1;
    }
    
    
}