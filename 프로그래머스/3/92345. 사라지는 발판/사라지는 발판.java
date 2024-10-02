import java.util.*;

// 10:45 START!
class Solution {
    /**
     * 완탐? 
     * 한번 이동 할 때마다, board의 상태가 변경됨
     *
     * 종료 케이스
     * - A와 B가 같은 위치에 있으면, 먼저 움직이는 사람이 승리!
     * - 이동할 곳이 없으면 패배
     *
     * Q. 항상 이기는 사람을 어떻게 구할 수 있을까?
     */
    int min = Integer.MAX_VALUE;
    int n, m;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        
        return play(board, aloc, bloc);
    }
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    public int play(int[][] board, int[] aloc, int[] bloc) {
        int x = aloc[0];
        int y = aloc[1];

        if(board[x][y] == 0) return 0;
        if(canNotMove(aloc, board)) return 0;
        
        board[x][y] = 0;

        int result = 0;
        for(int d = 0; d < 4; ++d) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 1) {
                int count = 1 + play(board, bloc, new int[]{nx, ny});

                if(result % 2 == 0) { //  짝수면 A 입장에서 지는 것 / 원래 A가 지는 경우만 있었음
                    if(count % 2 == 0) { // 지는 경우면, 최대한 많이 움직이기!
                        result = Math.max(result, count);
                    } else { // 홀수면 A 입장에서 이기는 것 / 이길 수 있으면 이겨야 함!
                        result = count;
                    }
                } else { // 원래 A가 이기는 경우만 있었음
                    if(count % 2 == 1) { // 이기는 경우면, 최대한 조금 움직이기!
                        result = Math.min(result, count);
                    }
                }
            }
        }
        
        board[x][y] = 1;
        
        return result;
    }
    
    public boolean canNotMove(int[] loc, int[][] board) {
        for(int d = 0; d < 4; ++d) {
            int nx = loc[0] + dx[d];
            int ny = loc[1] + dy[d];

            if(nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && board[nx][ny] == 1) {
                return false;
            }
        }
        
        return true;
    }
}