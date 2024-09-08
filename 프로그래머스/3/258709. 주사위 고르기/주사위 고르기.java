import java.util.*;

class Solution {
    int n;
    int[] selected, answer;
    int[][] dice;
    int max = Integer.MIN_VALUE;
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        n = dice.length;
        answer = new int[n/2];
        selected = new int[n/2];
        
        select(0, 0); // 주사위 n/2를 선택 (조합)
        
        return answer;
    }
    
    private void select(int level, int start) {
        if(level == n / 2) { // 주사위 선택 완료!
            int[] sumA = getAllCase(selected); // A가 선택한 주사위들를 던져서 나올 수 있는 합의 모든 경우
            int[] sumB = getAllCase(getUnselected(selected)); // B가 선택한 주사위들를 던져서 나올 수 있는 합의 모든 경우
            
            int counts = getTotalCountsAWins(sumA, sumB); // A가 이기는 경우의 수
            
            if(counts > max) {
                max = counts;
                
                int idx = 0;
                for(int dice : selected) {
                    answer[idx++] = dice + 1;
                }
            }
            
            return;
        }
        
        for(int i = start; i < n; ++i) {
            selected[level] = i;
            select(level + 1, i + 1);
        }
    }
    
    private int[] getAllCase(int[] selected) {
        List<Integer> list = new ArrayList<>();
        
        roll(0, 0, selected, list);
        
        return list.stream()
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    private void roll(int level, int sum, int[] selected, List<Integer> list) {
        if(level == n / 2) {
            list.add(sum);
            return;
        }
        
        for(int num : dice[selected[level]]) {
            roll(level + 1, sum + num, selected, list);
        }
    }
    
    // selected 배열을 통해 선택하지 않은 주사위 리스트를 반환
    private int[] getUnselected(int[] selected) {
        int[] unselected = new int[n/2];
        int idx = 0;
        for(int i = 0; i < n; ++i) {
            boolean isSelected = false;
            for(int j = 0; j < n / 2; ++j) {
                if(selected[j] == i) {
                    isSelected = true;
                    break;
                }
            }
            
            if(!isSelected) unselected[idx++] = i;
        }
        
        return unselected;
    }
    
    private int getTotalCountsAWins(int[] sumA, int[] sumB) {
        int total = 0;
        
        int p = 0;
        for(int a : sumA) {
            while(p < sumB.length && a > sumB[p]) {
                p++;
            }
            
            total += p;
        }
        
        return total;
    }
}