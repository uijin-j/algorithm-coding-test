import java.util.*;


// 01:42 시작!
class Solution {
    /**
     * 접두사로 문자열을 탐색? --> 트라이 (문자열을 저장하는 알고리즘)
     * 접미사로 탐색할 때는? --> 문자열을 반대로 뒤집고 트라이를 생성
     * 
     * 일단, 단어의 수가 같아야 하기 때문에 단어의 길이 별로 트라이를 생성
     *  - word를 돌면서 트라이 생성 O(1_000_000)
     *  - queries를 돌면서 트라이 탐색 O(1_000_000)
     *  - 원래 트라이는 is_last와 같은 flag를 두지만, 이 문제의 경우 전체 매칭이 아니기 때문에 자신의 수(children)를 저장
     */
    public class Trie {
        Trie[] children = new Trie[26]; // 알파벳 소문자로만 이루어져 있기 때문에 굳이 HashMap 사용 X
        int numOfChildren= 0;
    }
    
    public int[] solution(String[] words, String[] queries) {
        Map<Integer, Trie> map = new HashMap<>();
        Map<Integer, Trie> reverseMap = new HashMap<>();
        
        // 트라이 생성
        for (String word : words) {
            int len = word.length();
            
            map.putIfAbsent(len, new Trie());
            Trie trie = map.get(len);
            trie.numOfChildren++;
            for (char ch : word.toCharArray()) {
                if (trie.children[ch-'a'] == null) {
                    trie.children[ch-'a'] = new Trie();
                }
                
                trie = trie.children[ch-'a'];
                trie.numOfChildren++;
            }
            
            String reverseWord = new StringBuilder(word).reverse().toString();
            reverseMap.putIfAbsent(len, new Trie());
            Trie reversTrie = reverseMap.get(len);
            reversTrie.numOfChildren++;
            for (char ch : reverseWord.toCharArray()) {
                if (reversTrie.children[ch-'a'] == null) {
                    reversTrie.children[ch-'a'] = new Trie();
                }
                
                reversTrie = reversTrie.children[ch-'a'];
                reversTrie.numOfChildren++;
            }
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            String query = queries[i];
            int len = query.length();
            if (!map.containsKey(len)) continue;
            
            int match = 0;
            if (query.charAt(0) == '?') match = count(new StringBuilder(query).reverse().toString(), reverseMap.get(len), 0);
            else match = count(query, map.get(len), 0);
            
            answer[i] = match;
        }
        
        
        return answer;
    }
                                
    public int count(String query, Trie trie, int idx) {
        char ch = query.charAt(idx);
        if (ch == '?') return trie.numOfChildren;
        if (trie.children[ch - 'a'] == null) return 0;
        return count(query, trie.children[ch - 'a'], idx + 1);
    }
}