import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        // p + m * (t - 1) 개만 구하면 됨
        // 1 ~ ?까지의 숫자를 n진법으로 변환
        
        int target = p + m * (t - 1);
        List<Character> results = new ArrayList<>();
        results.add('0');
        int number = 1;
        while(results.size() < target) {
            List<Character> converted = convertToN(number, n);
            results.addAll(converted);
            number++;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = p - 1; i < target; i += m) {
            sb.append(String.valueOf(results.get(i)));
        }
        
        return sb.toString();
    }
    
    // number를 n진수로 변환
    private List<Character> convertToN(int number, int n) {
        List<Character> result = new ArrayList<>();
        
        while(number > 0) {
            char ch = Character.forDigit(number % n, n);
            if(ch >= 'a' || ch <= 'f') ch = Character.toUpperCase(ch);
            result.add(0, ch);
            number /= n;
        }
        
        return result;
    }
}