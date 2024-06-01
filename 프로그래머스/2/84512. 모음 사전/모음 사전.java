import java.util.*;

class Solution {
    private String[] vowels = { "A", "E", "I", "O", "U" };
    private int[] selected = new int[5];
    private List<String> list = new ArrayList<>();
    public int solution(String word) {
        for(int i = 1; i <= 5; ++i) {
            dfs(0, i);
        }
        
        list.sort((s1, s2) -> {
                for(int i = 0; i < 5; ++i) {
                    char ch1 = (s1.length() < i + 1) ? 'A' : s1.charAt(i);
                    char ch2 = (s2.length() < i + 1) ? 'A' : s2.charAt(i);
                    
                    if(ch1 != ch2) return ch1 - ch2;
                }
                
                return 0;
            });

        return binarySearch(list, word) + 1;
    }
    
    private void dfs(int count, int target) {
        if(count == target) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < target; ++i) {
                sb.append(vowels[selected[i]]);
            }
            
            list.add(sb.toString());
            return;
        }
        
        for(int i = 0; i < 5; ++i) {
            selected[count] = i;
            dfs(count + 1, target);
        }
    }
    
    private int binarySearch(List<String> list, String target) {
        int left = 0;
        int right = list.size() - 1;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(target.equals(list.get(mid))) {
                return mid;
            } else if(target.compareTo(list.get(mid)) > 0) {
                left = mid + 1;
            } else {
                 right = mid - 1;
            }
        }
        
        return -1;
    }
}