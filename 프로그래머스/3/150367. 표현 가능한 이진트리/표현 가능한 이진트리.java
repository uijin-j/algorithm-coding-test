import java.util.*;

/**
 * n은 최대 10_000
 * 
 * 1. 일단 numbers를 돌면서 2진수로 변환 (앞에 0을 어떻게 채울까?)
 * 2. 재귀적으로 탐색하면서 2진 트리로 표현 가능한지 확인
 */
class Solution {
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        
        for(int i = 0; i < n; ++i) {
            long number = numbers[i];
            
            // 1. 이진수로 변환
            StringBuilder sb = new StringBuilder();
            while(number >= 1) {
                if(number % 2 == 0) {
                    sb.insert(0, "0");
                } else {
                    sb.insert(0, "1");
                    number--;
                }
                
                number /= 2;
            }
            
            if(number == 1) sb.insert(0, "1");
            
            // 2. 0 갯수 맞추기
            // 1(=1+2*0) 3(=1+2*1) 7(=3+2*2) 15(=7+2*4) ...
            int len = sb.length();
            int num = 1;
            int idx = 1;
            while(num < len) {
                num += 2 * idx;
                idx *= 2;
            }
            
            for(int j = 0; j < num-len; ++j) sb.insert(0, "0");
            String binary = sb.toString();
            
            // 3. 표현 가능한지 확인
            boolean possible = check(binary, 0, binary.length() - 1);
            if(possible) answer[i] = 1;
        }
        
        return answer;
    }
    
    public boolean check(String binary, int left, int right) {
        // left~right가 표현가능한 이진 트리인지 확인
        if(left == right) return true;
        
        int mid = (left + right) / 2;
        if (binary.charAt(mid) == '0') {
            // 처음에 바로 false를 리턴해서 틀림!
            int leftRoot = (left + mid - 1) / 2;
            int rightRoot = (mid + 1 + right) / 2;
            
            if(binary.charAt(leftRoot) != '0' || binary.charAt(rightRoot) != '0') return false;
        }
        
        if(check(binary, left, mid - 1) && check(binary, mid + 1, right)) return true;
        return false;
    }
}