import java.util.*;

// 00:19 시작
class Solution {
    /**
     * prefix로 단어를 찾기? => 트라이
     */
    public class Trie {
        Map<Character, Trie> children;
        int numOfChildren;
        
        public Trie() {
            children = new HashMap<>();
            numOfChildren = 0;
        }
    }
    
    public int solution(String[] words) {
        Trie root = new Trie();
        for(String word : words) {
            Trie node = root;
            node.numOfChildren++;
            for(int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                node.children.putIfAbsent(ch, new Trie());
                node = node.children.get(ch);
                node.numOfChildren++;
            }
        }
        
        int answer = 0;
        for(String word : words) {
            Trie node = root;
            for(int i = 0; i < word.length(); ++i) {
                answer++;
                char ch = word.charAt(i);
                node = node.children.get(ch);
                if(node.numOfChildren == 1) break;
            }
        }
        
        return answer;
    }
}