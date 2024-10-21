import java.util.*;

/**
 * 19:20 시작
 */ 
class Solution {
    int[][] board;
    int n, count = 0;
    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        board = new int[n + 1][n + 1];
        for(int[] command : build_frame) { // O(1_000)
            excIfPossible(command);
        }
        
        int[][] answer = new int[count < 0 ? 0 : count][3];
        int idx = 0;
        for(int i = 0; i < n + 1; ++i) {
            for(int j = 0; j < n + 1; ++j) {
                if(board[i][j] == 1) {
                    answer[idx++] = new int[]{i, j, 0};
                }
                
                if(board[i][j] == 2) {
                    answer[idx++] = new int[]{i, j, 1};
                }
                
                if(board[i][j] == 3) {
                    answer[idx++] = new int[]{i, j, 0};
                    answer[idx++] = new int[]{i, j, 1};
                }
            }
        }
        
        Arrays.sort(answer, (a, b) -> a[0] - b[0] == 0 ? (a[1] - b[1] == 0 ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]);
        
        return answer;
    }
    
    public void excIfPossible(int[] command) {
        int x = command[0];
        int y = command[1];
        int type = command[2] + 1; // 1: 기둥 2: 보 3:둘 다
        int cmd = command[3];
        
        if(cmd == 0) { // 삭제
            if(type == 1) { // 기둥
                // 연결될 수 있는 보들을 확인 + 위아래 기둥
                boolean result = true;
                count--;
                board[x][y] -= type; // 일단 삭제
                
                if(isB(x - 1, y) && !checkB(x - 1, y)) result = false;
                if(isB(x, y) && !checkB(x, y)) result = false;
                if(isB(x - 1, y + 1) && !checkB(x - 1, y + 1)) result = false;
                if(isB(x, y + 1) && !checkB(x, y + 1)) result = false;
                if(isG(x, y - 1) && !checkG(x, y - 1)) result = false;
                if(isG(x, y + 1) && !checkG(x, y + 1)) result = false;
                
                 if(!result) {
                     board[x][y] += type; // 롤백
                     count++;
                 }
            } else { // 보
                // 연결될 수 있는 좌우 보와 보 아래의 기둥들 확인
                boolean result = true;
                count--;
                board[x][y] -= type; // 일단 삭제
                
                if(isB(x - 1, y) && !checkB(x - 1, y)) result = false;
                if(isB(x + 1, y) && !checkB(x + 1, y)) result = false;
                if(isG(x, y) && !checkG(x, y)) result = false;
                if(isG(x + 1, y) && !checkG(x + 1, y)) result = false;
                
                 if(!result) {
                     board[x][y] += type; // 롤백 
                     count++;
                 }
            }
        }
        
        if(cmd == 1) { // 삽입
             if(type == 1) { // 기둥
                 if(checkG(x, y)) {
                     board[x][y] += type;
                     count++;
                 }
             } else { // 보
                 if(checkB(x, y)) {
                     board[x][y] += type;
                     count++;
                 }
             }
         }
    }
    
    // 기둥이 이 자리에 있을 수 있는지 확인
    public boolean checkG(int x, int y) {
        if(y == 0) return true; // 바닥 위에 설치
        if(isG(x, y - 1)) return true; // 다른 기둥 위에 설치
        if(isB(x, y) || isB(x-1, y)) return true; // 보의 한쪽 끝에 설치
        
        return false;
    }
    
    // 보가 이 자리에 있을 수 있는지 확인
    public boolean checkB(int x, int y) {
        if(isG(x, y - 1) || isG(x + 1, y - 1)) return true; // 기둥 위에 설치
        if(isB(x - 1, y) && isB(x + 1, y)) return true; // 다른 보들 사이에 설치

        return false;
    }
    
    // 해당 좌표에 기둥이 있는가?
    public boolean isG(int x, int y) {
        if(x < 0 || x > n || y < 0 || y > n) return false;
        return board[x][y] == 1 || board[x][y] == 3;
    }
    
    // 해당 좌표에 보가 있는가?
    public boolean isB(int x, int y) {
        if(x < 0 || x > n || y < 0 || y > n) return false;
        return board[x][y] == 2 || board[x][y] == 3;
    }
}