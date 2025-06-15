import java.util.*;


class Solution {    
    public int solution(int[][] board) {
        int n = board.length;
        Set<String> visited = new HashSet<>();
        visited.add("0_0_0");
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], o = cur[2], t = cur[3];

            if ((x == n - 1 && y == n - 2 && o == 0)
                || (x == n - 2 && y == n - 1 && o == 1)) {
                return t;
            }

            if (o == 0) { // 가로
                if (y < n - 2 && board[x][y + 2] == 0) {
                    handle(x, y + 1, 0, t + 1, q, visited); // 오른쪽
                }
                if (y > 0 && board[x][y - 1] == 0) {
                    handle(x, y - 1, 0, t + 1, q, visited); // 왼쪽
                }
                if (x < n - 1 && board[x + 1][y] == 0 && board[x + 1][y + 1] == 0) {
                    handle(x + 1, y, 0, t + 1, q, visited); // 아래
                    handle(x, y, 1, t + 1, q, visited);
                    handle(x, y + 1, 1, t + 1, q, visited);
                }
                if (x > 0 && board[x - 1][y] == 0 && board[x - 1][y + 1] == 0) {
                    handle(x - 1, y, 0, t + 1, q, visited); // 위
                    handle(x - 1, y, 1, t + 1, q, visited);
                    handle(x - 1, y + 1, 1, t + 1, q, visited);
                }
            } else { // 세로
                if (y < n - 1 && board[x][y + 1] == 0 && board[x + 1][y + 1] == 0) {
                    handle(x, y + 1, 1, t + 1, q, visited); // 오른쪽
                    handle(x, y, 0, t + 1, q, visited);
                    handle(x + 1, y, 0, t + 1, q, visited);
                }
                if (y > 0 && board[x][y - 1] == 0 && board[x + 1][y - 1] == 0) {
                    handle(x, y - 1, 1, t + 1, q, visited); // 왼쪽
                    handle(x, y - 1, 0,  t + 1, q, visited); 
                    handle(x + 1, y - 1, 0, t + 1, q, visited);
                }
                if (x < n - 2 && board[x + 2][y] == 0) {
                    handle(x + 1, y, 1, t + 1, q, visited); // 아래
                }
                if (x > 0 && board[x - 1][y] == 0) {
                    handle(x - 1, y, 1, t + 1, q, visited); // 위
                }
            }
        }
       
        return -1;
    }
   
    private void handle(int x, int y, int orientation, int time, Queue<int[]> q, Set<String> visited) {
        if (visited.add(String.format("%d_%d_%d", x, y, orientation))) {
            q.add(new int[]{x, y, orientation, time});
        }
    }
}