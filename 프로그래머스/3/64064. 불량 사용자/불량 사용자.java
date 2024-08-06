import java.util.*;

class Solution {
    // 각 banned_id 요소에 매치되는 아이디들을 탐색
    // 가능한 조합을 dfs!
    // 중복을 허용하지 않기 떄문에 추가적인 작업이 필요!
    boolean[] check;
    List<List<Integer>> matchs;
    Set<String> set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        matchs = new ArrayList<>();
        for(int idx = 0; idx < banned_id.length; ++idx) {
            matchs.add(new ArrayList<>());
        }
        
        for(int idx = 0; idx < user_id.length; ++idx) { // O(n^2) => 64
            for(int i = 0; i < banned_id.length; ++i) {
                if(match(user_id[idx], banned_id[i])) matchs.get(i).add(idx);
            }
        }
        
        check = new boolean[user_id.length];
        dfs(0, banned_id.length);
        
        return set.size();
    }
                   
    public boolean match(String name, String target) {
        if(name.length() != target.length()) return false;
        
        for(int idx = 0; idx < name.length(); ++idx) {
            if(target.charAt(idx) != '*' && target.charAt(idx) != name.charAt(idx)) {
                return false;
            }
        }
        
        return true;
    }
    
    public void dfs(int level, int goal) {
        if(level == goal) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < check.length; ++i) {
                if(check[i]) sb.append(i);
            }
            
            set.add(sb.toString());
            return;
        }
        
        for(int candi : matchs.get(level)) {
            if(check[candi]) continue;
            
            check[candi] = true;
            dfs(level + 1, goal);
            check[candi] = false;
        }
    }
}