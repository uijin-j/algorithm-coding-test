import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int i = 0; i < numbers.length; ++i) {
            answer[i] = f(numbers[i]);
        }
        
        return answer;
    }
    
    public long f(long x) {
        if(x % 2 == 0) { // 짝수
            return x + 1;
        }
        
        // 홀수
        String binary = "0" + Long.toBinaryString(x);
        int idx = binary.lastIndexOf('0');
        return Long.parseLong(binary.substring(0, idx) + "10" + binary.substring(idx + 2), 2);
    }
}