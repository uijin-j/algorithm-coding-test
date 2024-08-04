class Solution {
    // N이 크기 때문에 O(n) 이하로 해결해야 함!
    // 그리디?
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int range = 1 + 2 * w;
        int toCover = 1;
        for(int station : stations) {
            int begin = station - w;
            int end = station + w;
            
            // toCover 부터 begin-1까지 커버해야 함!
            if(toCover < begin) {
                int gap = begin - toCover;
                answer += gap / range + (((gap % range) > 0) ? 1 : 0); // ❗️ 요부분에 괄호 확실히 넣어주기!
            }
            
            toCover = end + 1;
        }
        
        if(toCover <= n) {
            int gap = n - toCover + 1;
            int count = gap / range + (((gap % range) > 0) ? 1 : 0);
            answer += count;
        }

        return answer;
    }
}