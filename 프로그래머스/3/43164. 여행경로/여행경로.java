import java.util.*;

class Solution {
    // ❗️ 리스트를 가지고 있는 리스트를 정렬해야 한다면, 리스트를 String으로 변환해서 정렬하기! 
    PriorityQueue<String> results;
    Map<String, List<Integer>> map;
    boolean[] check;
    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        
        results = new PriorityQueue<>((a, b) -> a.compareTo(b));
        map = new HashMap<>();
        check = new boolean[n];
        
        for(int i = 0; i < n; ++i) {
            String[] ticket = tickets[i];
            String from = ticket[0];
            
            List<Integer> list = map.getOrDefault(from, new ArrayList<>());
            list.add(i);
            map.put(from, list);
        }

        dfs("ICN", 0, n, tickets, "ICN");
        
        return results.isEmpty() ? new String[]{} : results.poll().split(" ");
    }
    
    public void dfs(String from, int level, int goal, String[][] tickets, String path) {
        if(level == goal) {
            results.add(path);
            return;
        }
        
        for(int num : map.getOrDefault(from, new ArrayList<>())) {
            if(!check[num]) {
                String to = tickets[num][1];
                check[num] = true;
                dfs(to, level+1, goal, tickets, path + " " + to);
                check[num] = false;
            }
        }
    }
}