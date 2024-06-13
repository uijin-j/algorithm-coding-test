class Solution {
    int[] dx = { 0, 1, 1 };
    int[] dy = { 1, 1, 0 };
    boolean[][] check;
    int count;
    public int solution(int m, int n, String[] board) {
        count = 0;
        
        char[][] charBoard = new char[m][n];
        for(int i = 0; i < m; ++i) {
            charBoard[i] = board[i].toCharArray();
        }
        
        do {
            check = new boolean[m][n];    
        } while(mark4(m, n, charBoard));
        
        return count;
    }
    
    public boolean mark4(int m, int n, char[][] board) {
        boolean answer = false;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                char ch = board[i][j];
                if(ch == 'X') continue;
                boolean toRemove = true;
                for(int k = 0; k < 3; ++k) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || nx >= m || ny < 0 || ny >= n || ch != board[nx][ny]) {
                        toRemove = false;
                    }
                }
                
                if(toRemove) {
                    answer = true;
                    check[i][j] = true;
                    for(int k = 0; k < 3; ++k) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        check[nx][ny] = true;
                    }
                }
            }
        }
        
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(check[i][j]) {
                    count++;
                    int x = i;
                    while(x >= 0) {
                        board[x][j] = x-1 < 0 ? 'X' : board[x-1][j];
                        x--;
                    }
                }
            }
        }
        
        return answer;
    }
}