import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        Queue<String> q = new LinkedList<>();
        Set<String> check = new HashSet<>();
        check.add(begin);
        q.offer(begin);
        
        int level = 0;
        while(!q.isEmpty()) {    
            int size = q.size();
            
            for(int i = 0; i < size; ++i) {
                String word = q.poll();
                
                for(int idx = 0; idx < words.length; ++idx) {
                    String next = words[idx];
                    
                    if(check.contains(next)) continue;
                    if(!canConvert(word, next)) continue;
                    if(next.equals(target)) return level + 1;
                    
                    check.add(next);
                    q.offer(next);
                }
            }
            
            level++;
        }
        
        return 0;
    }
    
    public boolean canConvert(String from, String to) {
        int len = from.length();
        int count = 0;
        for(int i = 0; i < len; ++i) {
            if(from.charAt(i) != to.charAt(i)) count++;
            if(count > 1) return false;
        }
           
        return true;
    }
}