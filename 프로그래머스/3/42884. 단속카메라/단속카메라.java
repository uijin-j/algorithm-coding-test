import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
        
        int answer = 0;
        int before = -30000;
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