import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long[] counter = new long[1001];
        for(int weight : weights) counter[weight]++;
        
        long count = 0;
        for(int weight = 100; weight <= 1_000; ++weight) {
            long countOfWeight = counter[weight];
            if(countOfWeight == 0) continue;

            if(countOfWeight > 1) {
                count += countOfWeight * (countOfWeight - 1) / 2;
            }
            
            if(weight % 3 == 0 && weight / 3 * 4 <= 1_000 && counter[weight / 3 * 4] != 0) {
                count += countOfWeight * counter[weight * 4 / 3];
            }
            
            if(weight * 2 <= 1_000 && counter[weight * 2] != 0) {
                count += countOfWeight * counter[weight * 2];
            }
            
            if(weight % 2 == 0 && weight / 2 * 3 <= 1_000 && counter[weight / 2 * 3] != 0) {
                count += countOfWeight * counter[weight / 2 * 3];
            }
        }
        
        return count;
    }
}