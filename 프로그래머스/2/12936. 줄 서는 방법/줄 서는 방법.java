import java.util.*;

class Solution {
    // 풀이1) 사전 순으로 차례대로 줄 세우기 O(n!)
    
    long[] facto;
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        facto = new long[n];
        
        if(n == 1) return new int[]{1};
        
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; ++i) list.add(i+1);
        
        k--;
        
        int idx = 0;
        while(!list.isEmpty()) {
            int order = (int) (k / factorial(n-1)); // order번째(0-base)로 작은 수가 현재 자릿수
            answer[idx++] = list.get(order);
            list.remove(order);
            k -= order * factorial(n-1);
            n--;
        }
        
        return answer;
    }
    
    public long factorial(int n) {
        if(n <= 1) return 1;
        if(facto[n] != 0) return facto[n];
        return facto[n] = n * factorial(n-1);
    }
}