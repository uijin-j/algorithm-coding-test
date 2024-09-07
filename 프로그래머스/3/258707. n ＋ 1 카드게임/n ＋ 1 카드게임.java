import java.util.*;

// 03:09 START!
// 이분탐색? 투포인터?
// 일단 그리디는 아님!
class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 1; // 최소 1라운드 passs
        int n = cards.length;
        int terget = n + 1;
        
        Set<Integer> mine = new HashSet<>();
        int pair = 0;
        for(int i = 0; i < n / 3; ++i) {
            if(mine.contains(terget - cards[i])) {
                pair++;
                continue;
            }
            
            mine.add(cards[i]);
        }
        
        Set<Integer> save = new HashSet<>();
        int pairInSave = 0;
        for(int i = n / 3; i < n; i += 2) { // 한 라운드씩 진행
            for(int j = i; j < i + 2; ++j) { // 한 라운드에 2장씩 확인
                if(mine.contains(terget - cards[j])) { // 가져가는게 이득!
                    if(coin <= 0) break;
                    coin--; 
                    pair++;
                    continue;
                }
                
                if(save.contains(terget - cards[j])) {
                    pairInSave++; // 일단 보류
                }
                
                save.add(cards[j]);
            }
            
            if(pair == 0) {
                if(pairInSave > 0 && coin >= 2) {
                    pairInSave--;
                    coin -= 2;
                    answer++;
                    continue;
                }
                
                // 종료
                return answer;
            }
            
            pair--;
            answer++;
        }
        
        return answer;
    }
}