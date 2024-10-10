import java.util.*;

// 18:15 START!
class Solution {
    /**
     * 직원 수는 최대 300_000
     * 1. 일단 그래프 구조를 만든다. (만들면서 팀 구성을 저장 / map.get(팀장) 해당 팀의 팀원)
     * 2. 분할 정복? DP? 
     */
    int[] sales;
    Map<Integer, List<Integer>> team = new HashMap<>();
    int[] whateverMemory;
    public int solution(int[] sales, int[][] links) {
        this.sales = sales;
        whateverMemory = new int[sales.length];
        Arrays.fill(whateverMemory, Integer.MAX_VALUE);
        
        for(int[] link : links) {
            team.putIfAbsent(link[0], new ArrayList<>());
            team.get(link[0]).add(link[1]);
        }
        
        return whatever(1);
    }
    
    public int whatever(int leader) { // 이 팀에서 누가가든 상관없어
        if(whateverMemory[leader - 1] != Integer.MAX_VALUE) return whateverMemory[leader - 1];
        
        int minOnly = sales[leader - 1]; // minOnly는 이 팀에서 다른 팀에 속하지 않은 팀원(팀장 포함)의 매출액 중 최소
        int sumOfWhatever = 0;
        for(int member : team.get(leader)) {
            if(team.containsKey(member)) {
                sumOfWhatever += whatever(member);
                continue;
            }
            
            minOnly = Math.min(minOnly, sales[member - 1]);
        }
        
        int result = minOnly + sumOfWhatever;
        for(int member : team.get(leader)) {
            if(team.containsKey(member)) {
                result = Math.min(result, sumOfWhatever - whatever(member) + goLeader(member));
            }
        }
        
        return whateverMemory[leader - 1] = result;
    }
    
    public int goLeader(int leader) { // 이 팀에서 리더가 꼭 가야해
        int result = sales[leader - 1];
        
        for(int member : team.get(leader)) { // 리더가 이미 갔으니까 하위 팀은 아무나 가면됨
            if(team.containsKey(member)) {
                result += whatever(member);
            }
        }
        
        return result;
    }
}