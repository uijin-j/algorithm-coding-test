import java.util.*;

class Solution {
    // 그리디
    public int solution(int storey) {        
        int answer = 0;
        
        while(storey > 0) {
            int num = storey % 10;
            storey /= 10;
            
            if(num > 5) {
                answer += 10 - num;
                storey++;
            } else if(num < 5) {
                answer += num;
            } else {
                int next = storey % 10;
                if(next >= 5) {
                    answer += 10 - num;
                    storey++;
                } else {
                    answer += num;
                }
            }
        }
        
        return answer;
    }
}