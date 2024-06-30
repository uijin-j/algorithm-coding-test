import java.util.*;

/** 
 * 풀이 1) 모든 사람들을 비교하면서 짝궁인지 확인! O(n^2) => 시간초과!
 * 풀이 2) 몸무게별로 해당 몸무게를 가진 사람을 count -> 몸무게를 돌며 짝궁 몸무게를 찾아서 경우의 수를 구하기! O(m)
 * 주의사항) 모든 사람이 같은 몸무게를 가질 경우, 경우의 수를 구할 때 int 범위를 넘길 수도 있음! (long)으로 변환해주기!
 */

 /**
  *  a * 4 = b * 2
  *  a * 4 = b * 3
  *  a * 3 = b * 2
  */

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