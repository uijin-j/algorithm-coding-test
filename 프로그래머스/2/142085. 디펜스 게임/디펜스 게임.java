import java.util.*;

class Solution {
    // 이분탐색
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        int left = 1, right = enemy.length;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(canComplete(mid, n, k, enemy)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    public boolean canComplete(int round, int n, int k, int[] enemy) {
        int[] enemys = new int[round];
        for(int i = 0; i < round; ++i) enemys[i] = enemy[i];
        
        Arrays.sort(enemys); // 4 2 4 5 -> 2 (4 4 5)
        
        long sum = 0; // 숫자가 큰데, sum을 한다면 범위에 주의하기!
        for(int i = 0; i < round - k; ++i) {
            sum += enemys[i];
        }
        
        if(sum <= n) return true;
        return false;
    }
}