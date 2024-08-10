import java.util.*;
import java.util.Collectors.*;

class Solution {
    // dfs로 풀면 되겠다!
    Map<String, List<String>> map;
    List<String> route;
    String[] answer;
    boolean flag;
    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        map = new HashMap<>();
        
        for(String[] ticket : tickets) {
            map.putIfAbsent(ticket[0], new ArrayList<>());
            map.get(ticket[0]).add(ticket[1]);
        }
        
        for(String key : map.keySet()) {
            map.put(key, 
                    map.get(key)
                        .stream()
                        .sorted()
                        .collect(Collectors.toList())
                   );
        }
        
        route = new ArrayList<>();
        route.add("ICN");
        
        dfs(0, n);
        
        return answer;
    }
    
    public void dfs(int level, int goal) {
        if(flag) return;
        if(level == goal) {
            answer = new String[route.size()];
            int idx = 0;
            for(String r : route) {
                answer[idx++] = r;
            }

            flag = true;
            return;
        }
        
        for(String to : map.get(route.get(level))) {
            route.add(to);
            dfs(level + 1, goal);
            route.remove(level + 1);
        }
    }
}