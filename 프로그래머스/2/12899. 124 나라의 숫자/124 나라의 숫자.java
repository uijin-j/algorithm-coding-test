import java.util.*;

class Solution {
    public String solution(int n) {
        String[] number = {  "4", "1", "2" };
        
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            int remainder = n % 3; // 1 0 
            n /= 3;
            
            sb.append(number[remainder]);
            
            if(remainder == 0) n--;
        }
        
        return sb.reverse().toString();
    }
}