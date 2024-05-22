import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        
        List<Set<Integer>> dp = new ArrayList<>();
        for(int i = 0; i < 9; ++i) {
            dp.add(new HashSet<>());
        }
        
        dp.get(1).add(N);
        if(N == number) return 1;
        
        for(int i = 2; i < 9; ++i) { // N을 i개 사용
            int num = N;
            for(int j = 0; j < i - 1; ++j) {
                num *= 10;
                num += N;
            }
            
            dp.get(i).add(num);
            
            for(int j = 1; j < i; ++j) {
                int k = i - j;
                for(int num1 : dp.get(j)) {
                    for(int num2 : dp.get(k)) {
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 * num2);
                        dp.get(i).add(num1 - num2);
                        if(num2 != 0) {
                            dp.get(i).add(num1 / num2);
                        }
                    }
                }
            }
            
            if(dp.get(i).contains(number)) {
                return i;
            }
            
        }
        
        return -1;
    }
}