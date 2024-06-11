import java.util.*;
import java.util.regex.*;

class Solution {
    Pattern pattern;
    public class File {
        String name;
        String head;
        int number;
        int order;
        
        public File(String name, int order) {
            String[] parsed = parse(name);
            this.head = parsed[0].toUpperCase();
            this.number = Integer.parseInt(parsed[1]);
            this.name = name;
            this.order = order;
        }
        
        private String[] parse(String name) {
            Matcher matcher = pattern.matcher(name);
            String[] result = new String[3];
            while(matcher.find()) {
                result[0] = matcher.group(1);
                result[1] = matcher.group(2);
            }
            
            return result;
        }
    }
    
    public String[] solution(String[] files) {
        pattern = Pattern.compile("([^0-9]+)([0-9]+)([^0-9]*[a-zA-Z0-9 .-]*)?$");
        String[] answer = new String[files.length];
        
        PriorityQueue<File> pq = new PriorityQueue<>(
            (a, b) -> (a.head).compareTo(b.head) == 0 ? 
                (a.number - b.number) == 0 ? a.order - b.order : a.number - b.number 
                : (a.head).compareTo(b.head));
        
        for(int i = 0; i < files.length; ++i) {
            pq.add(new File(files[i], i));
        }
        
        int idx = 0;
        while(!pq.isEmpty()) {
            answer[idx++] = pq.poll().name;
        }
        
        return answer;
    }
}