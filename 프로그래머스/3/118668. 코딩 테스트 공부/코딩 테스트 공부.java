import java.util.*;

// 16:21 START!
class Solution {
    /**
     * 문제들 중 가장 많은 alp를 요구하는 문제의 alp, 가장 많은 cop를 요구하는 문제의 cop를 얻어야 함 O(problems 길이) ~= 100
     *
     * 알고력과 코딩력을 높일 수 있는 방법이 많음
     * 1. alp: 1 / cop: 0 / cost: 1
     * 2. alp: 0 / cop: 1 / cost: 1
     * ... (총 problems의 길이 + 2)
     * 
     * 최단시간으로 목표하는 alp, cop에 도달하자
     * 완탐? 102 ^ 300 -> 시초
     * 
     * 어렵다.. 찾아보니 DP 문제
     */ 
    public int solution(int alp, int cop, int[][] problems) {
        int n = problems.length;
        int[] target = new int[2];
        for(int[] problem : problems) {
            target[0] = Math.max(target[0], problem[0]);
            target[1] = Math.max(target[1], problem[1]);
        }
        
        int[][] dp = new int[target[0] + 1][target[1] + 1]; // dp[i][j]는 i알고력과 j코딩력을 갖는데 걸리는 최단 시간
        for(int i = 0; i <= target[0]; ++i) {
            Arrays.fill(dp[i], 300);
        }
        
        alp = Math.min(alp, target[0]);
        cop = Math.min(cop, target[1]);
        dp[alp][cop] = 0;
        for(int i = alp; i <= target[0]; ++i) {
            for(int j = cop; j <= target[1]; ++j) {
                int nextAlp = Math.min(i+1, target[0]);
                dp[nextAlp][j] = Math.min(dp[nextAlp][j], dp[i][j] + 1); // 1시간 쓰고 알고력 +1
                
                int nextCop = Math.min(j + 1, target[1]);
                dp[i][nextCop] = Math.min(dp[i][nextCop], dp[i][j] + 1); // 1시간 쓰고 코딩력 +1
                
                for(int[] problem : problems) {
                    if(problem[0] <= i && problem[1] <= j) { // 풀 수 있는 문제
                        nextAlp = Math.min(i + problem[2], target[0]);
                        nextCop = Math.min(j + problem[3], target[1]);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        
        return dp[target[0]][target[1]];
    }
}