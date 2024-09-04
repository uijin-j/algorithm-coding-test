import java.util.*;

// 16:00 START!
class Solution {
    /**
     * 다음 달에 가장 많은 선물을 받을 것으로 예상되는 친구가 받을 선물 수를 예측하는 메서드
     */
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int numOfFriends = friends.length;
        int[][] give = new int[numOfFriends][numOfFriends];
        int[] points = new int[numOfFriends];
        Map<String, Integer> nameToIdx = new HashMap<>();
        for(int idx = 0; idx < numOfFriends; ++idx) {
            nameToIdx.put(friends[idx], idx);
        }
        
        // 1. gifts를 통해 선물을 주고 받은 내역을 저장한다. (ex. "muzi frodo"라면 give[muzi][frodo]++;)
        //  - String을 인덱스로 쓸 수 없기 때문에 friends의 index로 사용한다. (name -> index 변환을 위해 map 자료구조를 사용한다)
        //  - 각 친구들의 선물지수도 함께 계산한다. (points[muzi]++, points[frodo]--)
        for(String gift : gifts) {
            String[] history = gift.split(" ");
            int from = nameToIdx.get(history[0]);
            int to = nameToIdx.get(history[1]);
            
            give[from][to]++;
            points[from]++;
            points[to]--;
        }
        
        // 2. 모든 친구들이 받을 선물 양을 예측한다.
        // ex) muzi가 frodo에게 받을 선물 계산법
        // - if(give[muzi][frodo] > give[frodo][muzi]) counts[muzi]++;
        // - else if(give[muzi][frodo] == give[frodo][muzi] && poinst[muzi] > points[frodo]) counts[muzi]++;
        // - 이때 counts가 증가되면 예비 정답 후보와 비교 후 더 큰 값으로 넣는다.
        for(int i = 0; i < numOfFriends; ++i) {
            // 다음달에 i가 받을 선물양을 예측
            int count = 0;
            for(int j = 0; j < numOfFriends; ++j) {
                if(give[i][j] > give[j][i]) {
                    count++;
                    continue;
                }
                
                if(give[i][j] == give[j][i] && points[i] > points[j]) {
                    count++;
                }
            }
            
            answer = Math.max(answer, count);
        }
        
        return answer;
    }
}