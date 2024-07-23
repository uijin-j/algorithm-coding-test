class Solution {
    public long solution(int w, int h) {
        long total = w * (long) h;
        
        // y = (-h / w)*x + h
        
        double before = h;
        long count = 0;
        for(long x = 1; x <= w; ++x) {
            double y = -1 * h * x / (double) w + h;
            count += before - Math.floor(y);
            before = Math.ceil(y);
        }
        
        return total - count;
    }
}