import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] dp = new int[sequence.length];
        dp[0] = sequence[0];
        
        if(dp[0] == k) return new int[]{ 0, 0 };
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int[] answer = new int[]{ 0, sequence.length - 1 };
        int len = sequence.length;
        for(int i = 1; i < sequence.length; ++i) {
            dp[i] = dp[i-1] + sequence[i];
            if(map.containsKey(dp[i] - k)) {
                int from = map.get(dp[i] - k) + 1;
                if(len > i - from + 1) {
                    len = i - from + 1;
                    answer = new int[]{ from, i};   
                }
            }
            
            map.put(dp[i], i);
        }
        
        return answer;
    }
}