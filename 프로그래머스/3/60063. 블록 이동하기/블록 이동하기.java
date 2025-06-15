import java.util.*;


class Solution {    
    public int solution(int[][] board) {
        int n = board.length;
        Set<String> visited = new HashSet<>();
        visited.add("0_0_0");
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        int steps = 0;
       
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int y = cur[0], x = cur[1], o = cur[2];
               
                if ((y == n - 1 && x == n - 2 && o == 0)
                    || (y == n - 2 && x == n - 1 && o == 1)) {
                    return steps;
                }
               
                if (o == 0) {
                    if (x < n - 2 && board[y][x + 2] == 0) {
                        handle(y, x + 1, 0, q, visited);
                    }
                    if (x > 0 && board[y][x - 1] == 0) {
                        handle(y, x - 1, 0, q, visited);
                    }
                    if (y < n - 1 && board[y + 1][x] == 0 && board[y + 1][x + 1] == 0) {
                        handle(y + 1, x, 0, q, visited);
                        handle(y, x, 1, q, visited);
                        handle(y, x + 1, 1, q, visited);
                    }
                    if (y > 0 && board[y - 1][x] == 0 && board[y - 1][x + 1] == 0) {
                        handle(y - 1, x, 0, q, visited);
                        handle(y - 1, x, 1, q, visited);
                        handle(y - 1, x + 1, 1, q, visited);
                    }
                } else {
                    if (x < n - 1 && board[y][x + 1] == 0 && board[y + 1][x + 1] == 0) {
                        handle(y, x + 1, 1, q, visited);
                        handle(y, x, 0, q, visited);
                        handle(y + 1, x, 0, q, visited);
                    }
                    if (x > 0 && board[y][x - 1] == 0 && board[y + 1][x - 1] == 0) {
                        handle(y, x - 1, 1, q, visited);
                        handle(y, x - 1, 0, q, visited);
                        handle(y + 1, x - 1, 0, q, visited);
                    }
                    if (y < n - 2 && board[y + 2][x] == 0) {
                        handle(y + 1, x, 1, q, visited);
                    }
                    if (y > 0 && board[y - 1][x] == 0) {
                        handle(y - 1, x, 1, q, visited);
                    }
                }
            }
            steps++;
        }
       
        return -1;
    }
   
    private void handle(int y, int x, int orientation, Queue<int[]> q, Set<String> visited) {
        if (visited.add(String.format("%d_%d_%d", y, x, orientation))) {
            q.add(new int[]{y, x, orientation});
        }
    }
}