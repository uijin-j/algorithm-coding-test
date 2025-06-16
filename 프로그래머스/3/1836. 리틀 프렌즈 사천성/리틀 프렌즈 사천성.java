import java.util.*;

// 22:37 시작!
class Solution {
    /**
     * 완탐? 제거하는 순서를 정하기 O(26!) => 시간초과
     * A-Z까지 삭제할 수 있으면 삭제 -> 다시 A-Z까지 삭제할 수 있으면 삭제 -> 더 이상 삭제되는 타일이 없을 때까지 반복!
     * O(nm * 26 * 26) ~= O(9_000_000) OK
     *
     * A..C
     * ..C.
     * ....
     * ...A
     */
    int m, n;
    String[] board;
    boolean[] removed;
    boolean stop;
    public String solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        this.board = board;
        this.removed = new boolean[26];
        
        Map<Character, int[]> tiles = new HashMap<>();
        int numOfTiles = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                char type = board[i].charAt(j);
                
                if(type == '.' || type == '*') continue;
                tiles.putIfAbsent(type, new int[]{i, j});
                numOfTiles++;
            }
        }
        
        StringBuilder answer = new StringBuilder();
        boolean found = true;
        while(found) {
            found = false;
            
            for(int i = 0; i < 26; ++i) {
                char ch = (char) ('A'+i);
                if(!tiles.containsKey(ch)) continue;

                int[] pos = tiles.get(ch);
                boolean[][] visit = new boolean[m][n];
                visit[pos[0]][pos[1]] = true;
                stop = false;
                if(search(ch, pos, -1, -1, visit)) {
                    removed[ch-'A'] = true;
                    tiles.remove(ch);
                    answer.append(ch);
                    found = true;
                    break;
                }
            }
        }
        
        if(answer.length() != numOfTiles / 2) return "IMPOSSIBLE";
        return answer.toString();
    }
    
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    public boolean search(char target, int[] point, int count, int direction, boolean[][] visit) {
        if(stop) return true;
        
        for(int d = 0; d < 4; ++d) {
            int nx = point[0] + dx[d];
            int ny = point[1] + dy[d];
            
            if(nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx].charAt(ny) != '*' && !visit[nx][ny]) {
                if(direction != d && count >= 1) continue;
                
                char type = board[nx].charAt(ny);
                if(type == target) return true;
                if(type != '.' && !removed[type-'A']) continue;
                
                visit[nx][ny] = true;
                if(search(target, new int[]{nx, ny}, (direction != d)? count+1 : count, d, visit)) {
                    return stop = true;
                }
                visit[nx][ny] = false;
            }
        }
        
        return false;
    }
}