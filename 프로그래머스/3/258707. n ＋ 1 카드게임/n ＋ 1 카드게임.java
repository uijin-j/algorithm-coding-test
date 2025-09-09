class Solution {
    /**
     * 모든 카드는 n+1을 만들 수 있는 짝이 딱 1장 있음!
     *  - 첫번째 목표는 현재 라운드를 통과하기 (합이 n+1인 쌍을 만들기)
     *  - 두번째 목표는 앞으로 라운드도 통과할 수 있도록 하기 (합이 n+1인 쌍을 최대한 많이 확보 + 최대한 빨리 확보)
     */
    public int solution(int coin, int[] cards) {
        int n = cards.length; // n은 최대 1000
        int target = n + 1;
        boolean[] inHand = new boolean[n+1]; // 내가 손에 쥐고 있는 카드
        boolean[] canBuy = new boolean[n+1]; // 내가 코인 주고 살 수 있는 카드
        int pair = 0;
        for(int i = 0; i < n / 3; ++i) {
            if(inHand[target - cards[i]]) {
                pair++;
                continue; // 무조건 한 쌍이니까, 굳이 hand에 체크 해 놓을 필요가 없음!
            }
            
            inHand[cards[i]] = true;
        }
        
        int round = 1;
        int idx = n / 3;
        int pairInCanBuy = 0; // canBuy 배열에 있는 카드 중 페어의 갯수
        while(idx < n) {
            for(int i = 0; i < 2; ++i) {
                if(coin == 0) break; // 코인이 없으면 무조건 버려야 함
                
                int card = cards[idx++];
                if(inHand[target - card]) { // 손에 있는 애랑 짝이면 무조건 사기 (코인 1개만 써도 되기 때문)
                    coin--;
                    pair++;
                    continue;
                }

                if(canBuy[target - card]) {
                    pairInCanBuy++;
                    continue;
                }

                canBuy[card] = true; // 미래를 위해 살 수 있는 리스트에 추가
            }
            
            if(pair > 0) {
                pair--;
                round++;
                continue;
            }
            
            if(coin >= 2 && pairInCanBuy > 0) {
                coin -= 2;
                pairInCanBuy -= 1;
                round++;
                continue;
            }
            
            return round;
        }
        
        return round;
    }
}