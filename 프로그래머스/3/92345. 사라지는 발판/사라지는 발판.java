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
    int aMax = 0;
    int bMax = 0;
    int min = Integer.MAX_VALUE;
    char winner;
    int[][] fix = new int[2][2];
    int n, m;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        
        fix[0][0] = aloc[0];
        fix[0][1] = aloc[1];
        fix[1][0] = bloc[0];
        fix[1][1] = bloc[1];

        play('a', board, aloc, bloc, 0); 
        
        System.out.println(aMax);
        System.out.println(bMax);
        
        return winner == 'a' ? aMax : bMax;
    }
    
    public char findWinner(int[] aloc, int[] bloc, int[][] board) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{aloc[0], aloc[1], 0});
        
        while(!q.isEmpty()) {
            int[] node = q.poll();
            
            for(int d = 0; d < 4; ++d) {
                int nx = node[0] + dx[d];
                int ny = node[1] + dy[d];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 1) {
                    if(nx == bloc[0] && ny == bloc[1]) {
                        int min = node[2] + 1;
                        if(min % 2 == 0) return 'a';
                        return 'b';
                    }
                    q.offer(new int[]{nx, ny, node[2] + 1});
                }
            }
        }
        
        return 'a';
    }
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    public void play(char turn, int[][] board, int[] aloc, int[] bloc, int count) {
        System.out.println("[" + count + "] " + (char) turn + " turn!");
        System.out.println("a: [" + aloc[0] + ", " + aloc[1] + "]");
        System.out.println("b: [" + bloc[0] + ", " + bloc[1] + "]");
        
        if(turn == 'a') {
            if(canNotMove(aloc, board)) {
                bMax = Math.max(bMax, count);
                if(min > count) {
                    min = count;
                    winner = 'b';
                }
                return;
            }
            
            if(aloc[0] == bloc[0] && aloc[1] == bloc[1]) {
                aMax = Math.max(aMax, count + 1);
                if(min > count) {
                    min = count;
                    winner = 'a';
                }
                return;
            }
            
            changeToNotAvaliable(board, aloc[0], aloc[1]);
            for(int d = 0; d < 4; ++d) {
                int nx = aloc[0] + dx[d];
                int ny = aloc[1] + dy[d];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 1) {
                    play('b', board, new int[]{nx, ny}, bloc, count + 1);
                }
            }
        } else {
            if(canNotMove(bloc, board)) {
                aMax = Math.max(aMax, count);
                if(min > count) {
                    min = count;
                    winner = 'a';
                }
                return;
            }
            
            if(aloc[0] == bloc[0] && aloc[1] == bloc[1]) {
                bMax = Math.max(bMax, count + 1);
                if(min > count) {
                    min = count;
                    winner = 'b';
                }
                return;
            }
            
            changeToNotAvaliable(board, bloc[0], bloc[1]);
            
            for(int d = 0; d < 4; ++d) {
                int nx = bloc[0] + dx[d];
                int ny = bloc[1] + dy[d];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 1) {
                    play('a', board, aloc, new int[]{nx, ny}, count + 1);
                }
            }
        }
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
    
    public void changeToNotAvaliable(int[][] board, int x, int y) {        
        board[x][y] = 0;
    }
}