import java.util.*;

class Solution {
    // 각 banned_id 요소에 매치되는 아이디들을 탐색
    boolean[] check;
    int[] selected;
    List<List<Integer>> matchs;
    Set<String> set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        matchs = new ArrayList<>();
        for(int idx = 0; idx < banned_id.length; ++idx) {
            matchs.add(new ArrayList<>());
        }
        
        for(int idx = 0; idx < user_id.length; ++idx) {
            for(int i = 0; i < banned_id.length; ++i) {
                if(match(user_id[idx], banned_id[i])) matchs.get(i).add(idx);
            }
        }
        
        check = new boolean[user_id.length];
        selected = new int[banned_id.length];
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
            int[] copy = Arrays.copyOf(selected, selected.length);
            Arrays.sort(copy);
            set.add(Arrays.toString(copy));
            return;
        }
        
        for(int candi : matchs.get(level)) {
            if(check[candi]) continue;
            check[candi] = true;
            selected[level] = candi;
            dfs(level + 1, goal);
            check[candi] = false;
        }
    }
}