import java.util.*;

// 19:20 START! 20:38 END! (1시간 20분)
class Solution {
    /**
     * 처음에는 단순히 number를 이진수로 바꾼 문자열이 있을 때, 뒤에서부터 홀수번째는 리프노드니까, 짝수번째 노드만 돌면서 0이면 false를 해야지 라고 생각!
     * but 리프노드가 아니여도 0일 수 있음! (양쪽 자식 노드가 모두 0일 때)
     * 
     * 즉, 최상위 root 노드부터 재귀적으로 올바른지 확인해야 함!
     *  - 먼저 트리의 level이 몇인지 알아야 함! 
     *    but, 이진수의 앞쪽에 0이 있으면 10진수로 바꿀 때 사라짐ㅜㅜ (미리 각 레벨별 포화 이진 트리의 노드수를 구해서 이용해야 함!)
     * 
     */ 
    public int[] solution(long[] numbers) {
        // dp[i]: 높이가 i인 포화 이진 트리의 노드 수 (ex. dp[1] = 1, dp[2] = 3, dp[3] = 7 ...)
        // 10^15를 2진수로 바꾸면 51자리가 나옴! 그래서 63까지만! (Long.toBinaryString(10^15)를 활용)
        int[] dp = { 0, 1, 3, 7, 15, 31, 63};
        
        int[] answer = new int[numbers.length];
        int idx = 0;
        for(long num : numbers) {
            String binary = Long.toBinaryString(num); // num을 2진수로 바꾸기
            int len = binary.length();
            
            for(int i = 1; i < dp.length; ++i) {
                if(len <= dp[i] && len > dp[i-1]) {
                    // level이 i!
                    binary = "0".repeat(dp[i] - len) + binary;
                    answer[idx++] = canConvert(i, binary) ? 1 : 0;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    public boolean canConvert(int level, String binary) {
        if(level == 1) return true; // 리프 노드는 0이든 1이든 필요없음!
        
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