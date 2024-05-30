import java.util.*;

class Solution {
    public class Word {
        String word;
        int index;
        
        public Word(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
    
    public int[] solution(String msg) {
        List<Integer> compression = new ArrayList<>();
        Map<Character, List<Word>> dictionary = new HashMap<>();
        for(char ch = 'A'; ch < 'A' + 26; ++ch) {
            List<Word> list = new ArrayList<>();
            list.add(new Word(String.valueOf(ch), ch - 'A' + 1));
            dictionary.put(ch, list);
        }
        
        int counter = 26;
        boolean isFinish = false;
        while(msg.length() > 0 && !isFinish) {
            char key = msg.charAt(0);
            List<Word> list = dictionary.get(key);
            list.sort((a, b) -> b.word.length() - a.word.length());
            for(Word word : list) {
                if(msg.startsWith(word.word)) {
                    compression.add(word.index);
                    if(msg.length() == word.word.length()) {
                        isFinish = true;
                        break;
                    }
                    list.add(new Word(word.word + String.valueOf(msg.charAt(word.word.length())), ++counter));
                    msg = msg.substring(word.word.length());
                    break;
                }
            }
        }
        
        return compression.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}
