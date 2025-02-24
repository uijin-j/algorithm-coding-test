import java.util.*;

class Solution {
    /**
     * 가장 가까운 카드부터 없애는 것이 최선의 방법은 아님! 왜냐하면 그 카드를 통해서 shift할 때 더 빨리 갈수도 있기 때문!
     * 완탐? 남은 카드의 종류가 N개 일 때, O(N! * 2^N) ~= O(6!*2^6) ~= 72,000
     */
    int[][] board;
    Map<Integer, List<Integer>> map;
    List<int[]> cards;
    int[] selected;
    boolean[] check;
    int[] start;
    int min = Integer.MAX_VALUE;
    public int solution(int[][] board, int r, int c) {
        this.board = board;
        start = new int[]{ r, c };
        map = new HashMap<>();
        cards = new ArrayList<>();
        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                if(board[i][j] > 0) {
                    int type = board[i][j];
                    cards.add(new int[]{ i, j });
                    map.putIfAbsent(type, new ArrayList<>());
                    map.get(type).add(cards.size() - 1);
                }
            }
        }
        
        int total = cards.size();
        selected = new int[total];
        check = new boolean[total/2 + 1];
        dfs(0, total);
        
        return min + total;
    }
    
    public void dfs(int level, int goal) {
        if(level == goal) {
            int total = 0;
            int[] before = start;
            boolean[][] hidden = new boolean[4][4];
            for(int i = 0; i < goal; ++i) {
                int next = selected[i];
                int[] target = cards.get(next);
                total += calculate(before, target, hidden);
                before = target;
                hidden[target[0]][target[1]] = true;
                
                if(total >= min) return;
            }
            
            min = Math.min(min, total);
            return;
        }
        
        for(int type = 1; type <= goal/2; ++type) {
            if(check[type]) continue;
            check[type] = true;
            
            selected[level] = map.get(type).get(0);
            selected[level+1] = map.get(type).get(1);
            dfs(level+2, goal);
            
            selected[level] = map.get(type).get(1);
            selected[level+1] = map.get(type).get(0);
            dfs(level+2, goal);
            
            check[type] = false;
        }
    }
    
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    public int calculate(int[] from, int[] to, boolean[][] hidden) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ from[0], from[1], 0 });
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            
            if(x == to[0] && y == to[1]) {
                return cost;
            }
            
            for(int d = 0; d < 4; ++d) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                
                if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
                    q.offer(new int[]{ nx, ny, cost + 1 });
                }
            }
            
            for(int d = 0; d < 4; ++d) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                
                while(true) {
                    if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                        q.offer(new int[]{ nx - dx[d], ny - dy[d], cost + 1 });
                        break;
                    }
                    
                    if(board[nx][ny] > 0 && !hidden[nx][ny]) {
                        q.offer(new int[]{ nx, ny, cost + 1 });
                        break;
                    }
                    
                    nx += dx[d];
                    ny += dy[d];
                }
            }
        }
        
        return -1;
    }
}