class Solution {
    public int solution(int [][]board) {
        int answer = 0;
        int[][] dp = new int[board.length + 1][board[0].length + 1];
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                if(board[i][j] == 1) {
                    dp[i+1][j+1] = 1 + Math.min(dp[i][j], Math.min(dp[i][j+1], dp[i+1][j]));
                    answer = Math.max(answer, dp[i+1][j+1] * dp[i+1][j+1]);
                }
            }
        }
 
        return answer;
    }
}