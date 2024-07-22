import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> results = new ArrayList<Integer>();
        results.add(k);
        while(k != 1) {
            if(k % 2 == 0) {
                k /= 2;
                results.add(k);
                continue;
            }
            
            k = k * 3 + 1;
            results.add(k);
        }
        
        int n = results.size();
        
        double[] sum = new double[n];
        for(int i = 1; i < n; ++i) {
            double result = (results.get(i-1) + results.get(i)) / (double) 2;
            sum[i] = sum[i-1] + result;
        }
        
        double[] answer = new double[ranges.length];
        int idx = 0;
        for(int[] range : ranges) {
            if(range[0] == range[1]) {
                answer[idx++] = sum[n-1];
                continue;
            }
            
            range[1] += n - 1;
            
            if(range[0] > range[1]) {
                answer[idx++] = -1;
                continue;
            }
            
            answer[idx++] = sum[range[1]] - sum[range[0]];
        }
        
        return answer;
    }
}