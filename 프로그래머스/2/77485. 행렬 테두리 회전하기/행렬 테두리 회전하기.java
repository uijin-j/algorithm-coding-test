import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] board = new int[rows+1][columns+1];
        for(int i = 1; i <= rows; ++i) {
            for(int j = 1; j <= columns; ++j) {
                board[i][j] = (i-1) * columns + j;
            }
        }
        
        int idx = 0;
        for(int[] query : queries) {            
            int x1 = query[0];
            int y1 = query[1];
            int x2 = query[2];
            int y2 = query[3];
            
            int before = board[x1][y1];
            int min = before;
            for(int column = y1 + 1; column <= y2; ++column) {
                // x1 고정
                int temp = board[x1][column];
                board[x1][column] = before;
                before = temp;
                min = Math.min(min, before);
            }
            
            for(int row = x1 + 1; row <= x2; ++row) {
                // y2 고정
                int temp = board[row][y2];
                board[row][y2] = before;
                before = temp;
                min = Math.min(min, before);
            }
            
            for(int column = y2 - 1; column >= y1; --column) {
                // x2 고정
                int temp = board[x2][column];
                board[x2][column] = before;
                before = temp;
                min = Math.min(min, before);
            }
            
            for(int row = x2 - 1; row >= x1; --row) {
                // y1 고정
                int temp = board[row][y1];
                board[row][y1] = before;
                before = temp;
                min = Math.min(min, before);
            }
            
            answer[idx] = min;
            idx++;
        }
        
        return answer;
    }
}