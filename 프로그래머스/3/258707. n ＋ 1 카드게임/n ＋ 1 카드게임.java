import java.util.*;

/**
 * 23:00 시작!
 * 합이 n+1인 카드 2장을 내고 다음 라운드로 넘어가는 게임을 진행
 * n/3장의 카드를 가지고 시작
 * 매 라운드마다 2장의 카드를 확인 후, 코인을 주고 가지거나 버림
 *
 * 도달 가능한 최대 라운드?
 * 
 * 일단 지금 보유하고 있는 카드랑 짝이면 가지는 것이 이득
 * 만약, 지금 보유하고 있는 카드랑 짝이 아니면? 미래에 나오는 카드와 짝이 될 수 있으니 마킹
 * 만약, 마킹된 카드랑 짝이면? 코인 2개 주고 살 수 있는 카드가 있다고 체크
 * 그리디하게 최대한 코인을 적게 쓰면서 라운드를 진행!
 */
class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        boolean[] mine = new boolean[n+1];
        boolean[] seen = new boolean[n+1];
        int pair = 0;
        int spare = 0;
        
        for(int i = 0; i < n/3; ++i) {
            int card = cards[i];
            if(mine[n+1-card]) pair++;
            mine[card] = true;
        }
        
        int answer = 1;
        for(int i = n/3; i < n; i += 2, answer+=1) {    
            for(int j = 0; j < 2; ++j) {
                int card = cards[i+j];
                if(mine[n+1-card] && coin > 0) {
                    coin--;
                    pair++;
                    mine[card] = true;
                }
                
                if(seen[n+1-card]) {
                    spare++;
                }
                seen[card] = true;
            }
            
            if(pair > 0) {
                pair--;
                continue;
            }
            
            if(spare > 0 && coin >= 2) {
                spare--;
                coin-=2;
                continue;
            }
            
            break;
        }
        
        return answer;
    }
}