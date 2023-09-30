import java.util.*;

class Solution {
    List<String> paths = new ArrayList<>();
    boolean[] check;
    int n;
    
    public void dfs(int l, String from, String path, String[][] tickets) {
        if(l == n) {
            paths.add(path);
            return;
        }
        
        for(int i = 0; i < n; ++i) {
            String[] ticket = tickets[i];
            if(ticket[0].equals(from) && !check[i]) {
                check[i] = true;
                String to = ticket[1];
                dfs(l+1, to, path + " " + to, tickets);
                check[i] = false;
            }
        }
    }
    
    
    public String[] solution(String[][] tickets) {
        n = tickets.length;
        check = new boolean[n];
        
        dfs(0, "ICN", "ICN", tickets);
        
        Collections.sort(paths);
        
        return paths.get(0).split(" ");
    }
}