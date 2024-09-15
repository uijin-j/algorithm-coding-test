import java.util.*;

// 17:06 START!
class Solution {
    /**
     * 그래프 탐색 (bfs)
     * - 일반적인 bfs와 다른 점: 또 방문할 수 있음 / 최단거리가 아닌 일정 거리 k로 가는 방법을 구해야 함
     * 깊이가 k로 정해져 있으니까 dfs가 더 나을 것 같다! => 중복 방문이 허용되기 때문에 O(4^k)..
     */
    String answer;
    boolean found;
    int[] target;
    int n, m, k, r, c;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "";
        target = new int[]{r-1, c-1};
        this.n = n;
        this.m = m;
        this.k = k;
        
        int minDistance = Math.abs(x - r) + Math.abs(y - c);
        if(k < minDistance) return "impossible";
        if((k - minDistance) % 2 == 1) return "impossible";
        
        StringBuilder sb = new StringBuilder();
        dfs(0, x-1, y-1, new StringBuilder());
        
        return answer == "" ? "impossible" : answer;
    }
    
    String[] d = {"d", "l", "r", "u"};
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    public void dfs(int level, int x, int y, StringBuilder sb) {
        if(found) return;
        if(level == k) {
            if(x == target[0] && y == target[1]) {
                answer = sb.toString();
                found = true;
            }
            return;
        }
        
        int minDistance = Math.abs(x - target[0]) + Math.abs(y - target[1]);
        if(k - level < minDistance) return;
        
        for(int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                sb.append(d[i]);
                dfs(level + 1, nx, ny, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}