import java.util.*;

class Solution {
    // ❗️ 시간/범위가 겹치는 것을 구할 때는 정렬이 좋은 Hint!
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
        
        int answer = 0;
        int before = -30000; // 이전 cctv 범위가 끝나는 값
        for(int[] route : routes) {
            if(before < route[0]) {
                answer++;
                before = route[1];
                continue;
            }
            
            before = Math.min(before, route[1]);
        }
        
        return answer;
    }
}