import java.util.*;

// 20:37 START!
class Solution {
    /**
     * 1. privacies를 돌면서 해당 개인정보가 만료되는 날짜를 구한다.=> O(n), 1 <= n <= 100
     *  - 약관 종류를 통해 유효기간을 파악하기 위해서 Map 자료구조를 사용한다 => O(m), 1 <= m <= 20
     * 2. 만료날짜와 오늘 날짜를 비교한다.
     */
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> map = new HashMap<>();
        for(String term : terms) {
            String[] infos = term.split(" ");
            map.put(infos[0], Integer.parseInt(infos[1]) * 28);
        }
        
        int todays = toDays(today); // 년/월/일을 '일'로 바꾸기!
        List<Integer> needToRemove = new ArrayList<>();
        for(int i = 0; i < privacies.length; ++i) {
            String[] infos = privacies[i].split(" ");
            int beginAt = toDays(infos[0]);
            int expaireAt = beginAt + map.get(infos[1]);
            
            if(todays >= expaireAt) {
                needToRemove.add(i + 1);
            }
        }
        
        int[] answer = new int[needToRemove.size()];
        for(int i = 0; i < needToRemove.size(); ++i) {
            answer[i] = needToRemove.get(i);
        }
        
        return answer;
    }
    
    public int toDays(String date) {
        String[] infos = date.split("\\.");
        
        int days = 0;
        days += Integer.parseInt(infos[0]) * 12 * 28;
        days += Integer.parseInt(infos[1]) * 28;
        days += Integer.parseInt(infos[2]);
        
        return days;
    }
}