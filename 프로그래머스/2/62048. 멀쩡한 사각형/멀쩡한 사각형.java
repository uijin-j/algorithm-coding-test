class Solution {
    // y = (-h / w) * x + h
    public long solution(int w, int h) {
        long total = w * (long) h; // 자료형에 주의하기!
        
        double before = h;
        long count = 0;
        for(long x = 1; x <= w; ++x) {
            double y = -1 * h * x / (double) w + h; // 정확성을 위해 * 를 먼저 해야함!
            count += before - Math.floor(y);
            before = Math.ceil(y);
        }
        
        return total - count;
    }
}