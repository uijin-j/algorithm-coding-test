class Solution {
    public long solution(int k, int d) {
        // x^2 + y^2 = d^2
        long answer = 0;
        for(int x = 0; x <= d; x += k) {
            int y = (int) Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));
            answer += y / k + 1;
        }
        
        return answer;
    }
}