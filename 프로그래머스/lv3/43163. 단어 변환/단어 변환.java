import java.util.*;

class Solution {
    Queue<Integer> q = new LinkedList<>();
    int[] dis;
    
    public void bfs(String target, String[] words) {
        while(!q.isEmpty()) {
            int now = q.poll();
            
            for(int i = 0; i < words.length; ++i) {
                if(dis[i] == 0 && canChange(words[now], words[i])) {
                    dis[i] = dis[now] + 1;
                    q.offer(i);
                }
            }
        }
    }
    
    public boolean canChange(String s1, String s2) {
        int miss = 0;
        for(int i = 0; i < s1.length(); ++i) {
            if(s1.charAt(i) != s2.charAt(i)) {
                if(miss == 1) return false;
                miss++;
            }
        }
        return true;
    }
    
    public int solution(String begin, String target, String[] words) {
        dis = new int[words.length];
        
        int targetIndex = -1;
        for(int i = 0; i < words.length; ++i) {
            if(words[i].equals(target)) targetIndex = i;
            if(canChange(begin, words[i])) {
                q.offer(i);
                dis[i] = 1;
            }
        }
        if(targetIndex == -1) return 0;
        
        bfs(target, words);
        
        if(dis[targetIndex] == 0) return 0;
        return dis[targetIndex];
    }
}