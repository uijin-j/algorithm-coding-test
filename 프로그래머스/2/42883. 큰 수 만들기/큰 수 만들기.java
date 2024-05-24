import java.util.*;

class Solution {
    public boolean[] selected;
    public String solution(String number, int k) {
        int n = number.length();
        selected = new boolean[n];
        
        int last = -1;
        for(int i = 0; i < n - k; ++i) {
            last = getMaxValueIdx(last + 1, k + i, number);
            selected[last] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            if(selected[i]) {
                sb.append(number.charAt(i));
            }
        }
        
        return sb.toString();
    }
    
    // number.substring(start, end + 1) 에서 가장 큰 수의 인덱스
    public int getMaxValueIdx(int start, int end, String number) {
        int max = number.charAt(start) - '0';
        int idx = start;
        for(int i = start + 1; i <= end; ++i) {
            int num = number.charAt(i) - '0';
            if(num > max) {
                max =num;
                idx = i;
            }
        }
        
        return idx;
    }
}
