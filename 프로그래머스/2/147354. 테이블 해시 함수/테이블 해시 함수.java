import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) { 
        Arrays.sort(data, (a, b) -> a[col-1] - b[col-1] == 0 ? b[0] - a[0] : a[col-1] - b[col-1]);
        
        int answer = 0;
        for(int i = row_begin; i <= row_end; ++i) {
            int sum = 0;
            for(int column : data[i-1]) {
                sum += column % i;
            }
            
            answer = answer ^ sum;
        }
        
        return answer;
    }
}