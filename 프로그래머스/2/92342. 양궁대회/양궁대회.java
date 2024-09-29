import java.util.*;

// 16:42 START! 
// 어렵당..
class Solution {
    /**
     * 보자마자 배낭 문제가 생각남! (이득을 얻기 위해 손실도 있음)
     *  - 써야하는 화살 => 손실 => 배낭 문제에서 무게
     *  - 얻을 수 있는 점수 => 이득 => 배낭 문제에서 가치
     *
     * 결국 풀이를 봤더니.. 완탐문제였다..
     */ 
    int[] ryan = new int[11];
    int[] apeach = new int[11];
    int[] answer = new int[11];
    int maxDiff = 0;
    public int[] solution(int n, int[] info) {
        apeach = info;
        
        dfs(0, n);
        
        return maxDiff == 0 ? new int[]{-1} : answer;
    }
    
    private void dfs(int level, int remain) {
        if(level == 10) {
            ryan[10] = remain; // 남은 화살은 0에 쏘기
            
            int rScore = 0;
            int aScore = 0;
            for(int i = 0; i <= 10; ++i) {
                if(ryan[i] > apeach[i]) rScore += 10 - i;
                else if(apeach[i] > 0) aScore += 10 - i;
            }
            
            if(rScore - aScore > maxDiff) {
                maxDiff = rScore - aScore;
                answer = copy(ryan);
            }
            
            if(rScore - aScore == maxDiff) {
                for(int i = 10; i >= 0; --i) {
                    if(ryan[i] > answer[i]) {
                        answer = copy(ryan);
                        break;
                    }
                    
                    if(answer[i] > ryan[i]) break;
                }
            }
            
            return;
        }
        
        // level 과녁을 라이언이 맞춤
        if(apeach[level] + 1 <= remain) {
            ryan[level] = apeach[level] + 1;
            dfs(level + 1, remain - (apeach[level] + 1));
            ryan[level] = 0;
        }
        
        // level 과녁을 라이언이 안 맞춤
        dfs(level + 1, remain);
    }
    
    public int[] copy(int[] arr) {
        int[] answer = new int[11];
        for(int i = 0; i <= 10; ++i) {
            answer[i] = arr[i];
        }
        
        return answer;
    }
}