import java.util.*;

class Solution {
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    boolean[][][][] visit = new boolean[11][11][11][11];
    Map<Character, Integer> map = new HashMap<>();
    int count = 0;
    public int solution(String dirs) {
        map.put('U', 0);
        map.put('R', 1);
        map.put('D', 2);
        map.put('L', 3);
        
        int[] position = { 0, 0 };
        for(char dir : dirs.toCharArray()) {
            position = move(map.get(dir), position);
        }
        
        return count;
    }
    
    public int[] move(int dir, int[] position) {
        int x = position[0];
        int y = position[1];
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        
        if(nx >= -5 && nx <= 5 && ny >= -5 && ny <= 5) {
            int tx = x < 0 ? Math.abs(x) + 5 : x;
            int ty = y < 0 ? Math.abs(y) + 5 : y;
            int tnx = nx < 0 ? Math.abs(nx) + 5 : nx;
            int tny = ny < 0 ? Math.abs(ny) + 5 : ny;
            
            if(!visit[tx][ty][tnx][tny]) {
                count++;
                visit[tx][ty][tnx][tny] = true;
                visit[tnx][tny][tx][ty] = true;
                return new int[]{ nx, ny };
            }
            
            return new int[]{ nx, ny };
        }
        
        return position;
    }
    
    
}