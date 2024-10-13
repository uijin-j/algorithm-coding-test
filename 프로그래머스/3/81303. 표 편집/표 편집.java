import java.util.*;

// 22:20 START!
class Solution {
    public String solution(int n, int k, String[] cmd) {
        int cursor = k;
        Stack<Integer> history = new Stack<>();
        int[] prev = new int[n];
        int[] next = new int[n];
        
        for(int i = 0; i < n; ++i) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        
        next[n-1] = -1;
        
        for(String command : cmd) {
            String[] info = command.split(" ");
            
            if(info[0].equals("U")) {
                int size = Integer.valueOf(info[1]);
                while(size > 0) {
                    cursor = prev[cursor];
                    size--;
                }
                continue;
            }
            
            if(info[0].equals("D")) {
                int size = Integer.valueOf(info[1]);
                while(size > 0) {
                    cursor = next[cursor];
                    size--;
                }
                continue;
            }
            
            if(info[0].equals("C")) {
                history.push(cursor);
                if(prev[cursor] != -1) next[prev[cursor]] = next[cursor];
                if(next[cursor] != -1) prev[next[cursor]] = prev[cursor];
                if(next[cursor] != -1) cursor = next[cursor];
                if(cursor == history.peek()) cursor = prev[cursor];
                continue;
            }
            
            if(info[0].equals("Z")) {
                Integer toZ = history.pop();
                if(prev[toZ] != -1) next[prev[toZ]] = toZ;
                if(next[toZ] != -1) prev[next[toZ]] = toZ;
                continue;
            }
        }
        
        StringBuilder sb = new StringBuilder("O".repeat(n));
        for(int deleted : history) sb.setCharAt(deleted, 'X');
        
        return sb.toString();
    }
}