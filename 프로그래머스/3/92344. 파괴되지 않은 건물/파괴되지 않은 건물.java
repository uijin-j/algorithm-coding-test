class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] sum = new int[n+1][m+1];
        
        for(int[] info : skill) {
            int type = info[0];
            int r1 = info[1];
            int c1 = info[2];
            int r2 = info[3];
            int c2 = info[4];
            int degree = info[5];
            
            if(type == 1) degree *= -1;
            
            sum[r1][c1] += degree;
            sum[r1][c2+1] += degree * -1;
            sum[r2+1][c1] += degree* -1;
            sum[r2+1][c2+1] += degree;
        }
        
        for(int i = 0; i < n; ++i) {
            for(int j = 1; j < m; ++j) {
                sum[i][j] += sum[i][j-1];
            }
        }
        
        for(int i = 1; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                sum[i][j] += sum[i-1][j];
            }
        }
        
        int answer = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}