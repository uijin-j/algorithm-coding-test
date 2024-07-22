class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = len;
        
        for(int i = 1; i < len; ++i) { // i개 단위로 압축
            StringBuilder sb = new StringBuilder();
            String word = s.substring(0, i);
            int count = 1;
            int j = i;
            while(j + i < len) {
                String substring = s.substring(j, j + i);
                if(word.equals(substring)) {
                    count++;
                    j += i;
                    continue;
                }
                
                // 같지 않으면
                if(count > 1) sb.append(String.valueOf(count));
                sb.append(word);
                word = substring;
                count = 1;
                
                j += i;
            }
            
            String substring = s.substring(j);
            if(word.equals(substring)) {
                sb.append(++count).append(word);
            } else {
                if(count > 1) sb.append(String.valueOf(count));
                 sb.append(word).append(substring);
            }
            
            answer = Math.min(answer, sb.length());
        }
        
        return answer;
    }
}