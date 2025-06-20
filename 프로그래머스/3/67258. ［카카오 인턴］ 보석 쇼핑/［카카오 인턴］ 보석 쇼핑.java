import java.util.*;

// 21:48 시작!
class Solution {
    /**
     * 1) 이분탐색? k 구간으로 쇼핑을 할 수 있는가? O(nlogn)
     * 2) 투 포인터? 조건을 만족하는 구간을 찾은 후 구간을 줄이기
     */
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for(String gem : gems) set.add(gem);
        int numOfGem = set.size();
        
        int left = 0, right = 0;
        Map<String, Integer> map = new HashMap<>();
        int type = 0;
        while(type < numOfGem) {
            String gem = gems[right++];
            if(map.getOrDefault(gem, 0) == 0) {
                type += 1;
            }
            
            map.put(gem, map.getOrDefault(gem, 0) + 1);
        }
              
        int n = gems.length;
        int[] answer = new int[]{left, right - 1};
        int len = right - left;
        while(right <= n) {
            String toRemove = gems[left++];
            map.put(toRemove, map.get(toRemove) - 1);

            if(map.get(toRemove) == 0) {
                while(right < n && !gems[right].equals(toRemove)) {
                    String toAdd = gems[right++];
                    map.put(toAdd, map.get(toAdd) + 1);
                }

                if(right == n) break;
                map.put(gems[right], 1);
                right++;
            }

            if(len > right - left) {
                answer = new int[]{left, right - 1};
                len = right - left;
            }
        }
        
        for(int i = 0; i < 2; ++i) answer[i] +=1;
        return answer;
    }
}