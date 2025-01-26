import java.util.*;

// 16:21 START!
class Solution {
    /**
     * 문제들 중 가장 많은 alp를 요구하는 문제의 alp, 가장 많은 cop를 요구하는 문제의 cop를 얻어야 함 O(problems 길이) ~= 100
     */ 
    public int solution(int alp, int cop, int[][] problems) {
        int n = problems.length;
        int targetAlp = 0;
        int targetCop = 0;
        for(int[] problem : problems) {
            targetAlp = Math.max(targetAlp, problem[0]);
            targetCop = Math.max(targetCop, problem[1]);
        }
        
        int[][] dp = new int[targetAlp + 1][targetCop + 1]; // dp[i][j]는 i알고력과 j코딩력을 갖는데 걸리는 최단 시간
        for(int i = 0; i <= targetAlp; ++i) {
            Arrays.fill(dp[i], 300); // 최댓값으로 설정
        }
        
        alp = Math.min(alp, targetAlp);
        cop = Math.min(cop, targetCop);
        dp[alp][cop] = 0;
        for(int i = alp; i <= targetAlp; ++i) {
            for(int j = cop; j <= targetCop; ++j) {
                int nextAlp = Math.min(i + 1, targetAlp);
                dp[nextAlp][j] = Math.min(dp[nextAlp][j], dp[i][j] + 1); // 1시간 쓰고 알고력 +1
                
                int nextCop = Math.min(j + 1, targetCop);
                dp[i][nextCop] = Math.min(dp[i][nextCop], dp[i][j] + 1); // 1시간 쓰고 코딩력 +1
                
                for(int[] problem : problems) {
                    if(problem[0] <= i && problem[1] <= j) { // 풀 수 있는 문제
                        nextAlp = Math.min(i + problem[2], targetAlp);
                        nextCop = Math.min(j + problem[3], targetCop);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        
        return dp[targetAlp][targetCop];
    }
}