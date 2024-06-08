import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int count = 0;
        for(String skill_tree : skill_trees) {
            int last = skill_tree.length();
            boolean canLearn = true, needToCheck = false;
            for(int i = skill.length() - 1; i >= 0; --i) {
                char ch = skill.charAt(i);
                int idx = skill_tree.indexOf(ch);
                if(needToCheck && (idx == -1 || idx >= last)) {
                    canLearn = false;
                    break;
                }
                if(!needToCheck && idx == -1) continue;
                
                needToCheck = true;
                last = idx;
            }
            
            if(canLearn) count++;
        }
        
        return count;
    }
}