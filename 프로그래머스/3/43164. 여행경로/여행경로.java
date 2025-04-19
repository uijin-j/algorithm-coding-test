import java.util.*;

class Solution {
    int n;
    Map<String, List<Ticket>> map;
    boolean[] check;
    String[] path;
    boolean found;
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));
        map = new HashMap<>();
        n = tickets.length;
        for(int i = 0; i < n; ++i) {
            String[] ticket = tickets[i];
            map.putIfAbsent(ticket[0], new ArrayList<>());
            map.get(ticket[0]).add(new Ticket(ticket[1], i));
        }
        
        check = new boolean[n];
        path = new String[n+1];
        found = false;
        dfs(0, "ICN");
        
        return path;
    }
    
    public void dfs(int level, String from) {
        if(found) return;
        
        path[level] = from;
        
        if(level == n) {
            found = true;
            return;
        }
        
        for(Ticket next : map.getOrDefault(from, new ArrayList<>())) {
            if(check[next.id]) continue;
            check[next.id] = true;
            dfs(level + 1, next.to);
            check[next.id] = false;
        }
    }
    
    public class Ticket {
        int id;
        String to;
        
        public Ticket(String to, int id) {
            this.to = to;
            this.id = id;
        }
    }
}