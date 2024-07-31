/**
 * 재귀?
 * n개의 자연수가 합이 s일 때, 자연수들의 곱이 최대인 경우를 찾아라 => 모든 수가 서로 비슷해야 곱이 가장 큼!
 * => s/n을 하나 선택한 뒤, n-1개의 자연수가 합이 s - (s/n)일 때, 자연수들의 곱이 최대인 경우를 찾아라 (❌)
 * 💡 나머지를 이용해서 풀기! (풀이봄)
 */
class Solution {
    public int[] solution(int n, int s) {
        if(n > s) return new int[]{-1};
        
        int share = s / n;
        int remainder = s % n;
        
        int[] answer = new int[n];
        int idx = n - 1;
        for(int i = 0; i < remainder; ++i) {
            answer[idx--] = share + 1;
        }
        
        while(idx >= 0) {
            answer[idx--] = share;
        }
        
        return answer;
    }
}