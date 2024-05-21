import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int[] numbers) {
        List<String> sorted = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
            .collect(Collectors.toList());
        
        if(sorted.get(0).charAt(0) == '0') return "0";
        
        StringBuilder sb = new StringBuilder();
        for(String num : sorted) {
            sb.append(num);
        }
        
        return sb.toString();
    }
}