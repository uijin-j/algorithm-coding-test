import java.util.*;

// 19:20 START!
class Solution {
    public int[] solution(long[] numbers) {
        List<Integer> dp = new ArrayList<>(); // dp[i]: 높이가 i인 포화 이진 트리의 노드 수
        dp.add(0);
        dp.add(1);
        int i = 2;
        while(dp.get(i-1) <= 51) {
            dp.add(dp.get(i-1) * 2 + 1);
            i++;
        }
        
        int[] answer = new int[numbers.length];
        int idx = 0;
        for(long num : numbers) {
            String binary = Long.toBinaryString(num);
            int len = binary.length();
            
            int left = 1, right = dp.size() - 1;
            while(left <= right) {
                int mid = left + (right - left) / 2;
                
                if(dp.get(mid) >= len && dp.get(mid - 1) < len) { // level이 mid!
                    binary = "0".repeat(dp.get(mid) - len) + binary;
                    answer[idx++] = canConvert(mid, binary) ? 1 : 0;
                    break;
                } else if(dp.get(mid) > len) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        
        return answer;
    }
    
    public boolean canConvert(int level, String binary) {
        if(level == 1) return true;
        
        int len = binary.length();
        int root = len / 2;
        int left = (root - 1) / 2;
        int right = root + (root - left);
        
        if(binary.charAt(root) == '0' && (binary.charAt(left) == '1' || binary.charAt(right) == '1')) {
            return false;
        }
        
        return canConvert(level - 1, binary.substring(0, root)) && canConvert(level - 1, binary.substring(root + 1, len));
    }
}