// 19:49 START!
class Solution {
    // 무식한 방법 O(nmk) -> 1000 * 1000 * 250,000 시간초과
    // 어렵다.. 누적합
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] affected = new int[n+1][m+1];
        
        for(int[] s : skill) {
            int toSum = ((s[0] == 1) ? -1 : 1) * s[5];
            int x1 = s[1];
            int y1 = s[2];
            int x2 = s[3];
            int y2 = s[4];
            
            affected[x1][y1] += toSum;
            affected[x1][y2+1] += (toSum * -1);
            affected[x2+1][y1] += (toSum * -1);
            affected[x2+1][y2+1] += toSum;
        }
        
        for(int i = 0; i < n; ++i) {
            for(int j = 1; j < m; ++j) {
                affected[i][j] += affected[i][j-1];
            }
        }
        
        for(int i = 1; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                affected[i][j] += affected[i-1][j];
            }
        }
        
        int count = 0;
         for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(board[i][j] + affected[i][j] > 0) count++;
            }
        }
        
        return count;
    }
}