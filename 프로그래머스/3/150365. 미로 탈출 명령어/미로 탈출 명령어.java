import java.util.*;

class Solution {
    /**
     * (x, y) -> (r, c)로 가는데 중복 방문 ⭕️, 거리 고정 ⭕️
     * 최단거리를 구하는 dfs 방식으로는 시간초과 O(4^k)
     * => 계산을 줄일 수 있는 방법을 찾아보기!
     */
    int n, m;
    int[] target;
    boolean found;
    String answer = "impossible";
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        target = new int[]{ r - 1, c - 1 };
        
        int min = Math.abs(x - r) + Math.abs(y - c); // 최단거리
        if( k < min || (k - min) % 2 == 1) {
            return "impossible"; // 도달할 수 없는 경우
        }
        
        dfs(0, x - 1, y - 1, k, new StringBuilder());
        
        return answer;
    }
    
    String[] d = { "d", "l", "r", "u" };
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, -1, 1, 0 };
    public void dfs(int level, int x, int y, int goal, StringBuilder sb) {
        if(found) {
            return;
        }
        
        if(level == goal) {
            if(x == target[0] && y == target[1]) {
                found = true;
                answer = sb.toString();
            }
            
            return;
        }
        
        int min = Math.abs(x - target[0]) + Math.abs(y - target[1]);
        if(goal - level < min || (goal - level - min) % 2 == 1) return; // 도달할 수 없는 경우
        
        for(int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                sb.append(d[i]);
                dfs(level + 1, nx, ny, goal, sb);
                sb.deleteCharAt(level);
            }
        }
    }
}