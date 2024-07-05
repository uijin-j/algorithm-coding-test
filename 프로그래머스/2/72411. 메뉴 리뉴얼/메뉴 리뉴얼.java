import java.util.*;
import java.util.stream.*;

class Solution {
    /**
     * 모든 주문을 돌면서(최대 20), 해당 주문에서 나올 수 있는 모든 조합(최대 10C2 + ... + 10C10)을 count!
     * map에 key를 돌면서 course에 해당 하는 크기의 주문을 찾고 count가 큰 것 부터! (count가 1이면, 넘기기)
     */
    StringBuilder selected;
    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> map = new HashMap<>();
        
        for(String order : orders) {
            char[] chars = order.toCharArray();

            Arrays.sort(chars);
            
            StringBuilder sorted = new StringBuilder();
            for(char ch : chars) sorted.append(ch);
            String sortedOrder = sorted.toString();
            
            // System.out.println("====== " + sortedOrder + " ======");
            for(int i = 2; i <= sortedOrder.length(); ++i) {
                // System.out.println(i + "개");
                // order.length()개 중에서 i개를 선택
                selected = new StringBuilder();
                combi(0, 0, i, sortedOrder, map);
            }
        }
        
        List<String> menus = new ArrayList<>();
        for(int num : course) {
            List<String> list = map.keySet()
                .stream()
                .filter(key -> key.length() == num && map.get(key) >= 2)
                .collect(Collectors.toList());
            
            if(list.size() == 0) continue;
            
            list.sort((a, b) -> map.get(b) - map.get(a));
            
            int max = map.get(list.get(0));
            for(String candi : list) {
                if(map.get(candi) < max) {
                    break;
                }
                
                if(map.get(candi) == max) {
                    menus.add(candi);
                }
            }
        }
        
        menus.sort((a, b) -> a.compareTo(b));
        
        int size = menus.size();
        String[] answer = new String[size];
        int idx = 0;
        for(String menu : menus) {
            answer[idx++] = menu;
        }
        
        return answer;
    }
    
    public void combi(int start, int cnt, int target, String order, Map<String, Integer> map) {
        if(cnt == target) {
            // System.out.println(selected.toString());
            map.put(selected.toString(), map.getOrDefault(selected.toString(), 0) + 1);
            return;
        }
        
        if(start >= order.length()) return;
        
        for(int i = start; i < order.length(); ++i) {    
            selected.append(order.charAt(i) + "");
            combi(i+1, cnt+1, target, order, map);
            selected.deleteCharAt(selected.length() - 1);
        }
    }
}