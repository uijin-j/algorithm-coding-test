class Solution {
    /**
     * 두 플레이어가 최선의 게임을 했을 때, 총 이동 횟수
     */
    int n, m;
    int INF = 25;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        
        return play(board, aloc, bloc).move;
    }
    
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    public Result play(int[][] board, int[] player, int[] waiter) {
        int x = player[0];
        int y = player[1];
        
        if(board[x][y] == 0) return new Result(false, 0);
        
        int win = INF;
        int lose = -INF;
        for(int d = 0; d < 4; ++d) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if(checkBoundary(nx, ny) && board[nx][ny] == 1) { // 갈 수 있다면
                board[x][y] = 0;
                Result result = play(board, waiter, new int[]{nx, ny});
                
                if(result.win) { // player가 lose
                    lose = Math.max(lose, 1 + result.move);
                } else { // player가 win
                    win = Math.min(win, 1 + result.move);
                }
                
                board[x][y] = 1;
            }
        }
        
        if(win == INF && lose == -INF) {
            return new Result(false, 0);
        }
        
        if(win != INF) { // 이길 수 있으면 무조건 이기기
            return new Result(true, win);
        }
        
        return new Result(false, lose);
    }
    
    public boolean checkBoundary(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
    
    public class Result {
        boolean win;
        int move;
        
        public Result(boolean win, int move) {
            this.win = win;
            this.move = move;
        }
    }
}