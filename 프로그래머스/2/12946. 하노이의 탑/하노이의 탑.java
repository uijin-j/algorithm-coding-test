import java.util.*;

class Solution {
    List<int[]> way = new ArrayList<>();
    
    public int[][] solution(int n) {
        hanoi(n, 1, 3, 2);
        
        int[][] answer = new int[way.size()][2];
        int idx = 0;
        for(int[] step : way) {
            answer[idx++] = step;
        }
        
        return answer;
    }
    
    public void hanoi(int n, int from, int to, int remain) {
        if(n == 1) {
            way.add(new int[]{from, to});
            return;
        }
        
        hanoi(n-1, from, remain, to);
        hanoi(1, from, to, remain);
        hanoi(n-1, remain, to, from);
    }
}