import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        // n을 k진수로 바꾼다.
        String changed = toK(n, k);
        
        // 0을 기준으로 자른다.
        String[] nums = changed.split("0");
        for(String num : nums) {
            // 빈문자열은 skip한다.
            if(num.length() == 0) continue;
            
            // 소수인지 확인한다.
            if(isPrime(Long.parseLong(num))) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private String toK (int number, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        while(number >= k) {
            stack.push(number % k);
            number /= k;
        }
        
        stack.push(number);
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(String.valueOf(stack.pop()));
        }
        
        return sb.toString();
    }

    private boolean isPrime(long number) {        
        if(number <= 1) return false;
        
        for(int i = 2; i <= Math.sqrt(number); ++i) {
            if(number % i == 0) {
                return false;
            }
        }

        return true;
    }
    
}