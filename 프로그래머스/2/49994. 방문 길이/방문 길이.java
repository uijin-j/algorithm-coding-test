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
        
        int[] point = { 0, 0 };
        for(char dir : dirs.toCharArray()) {
            point = move(map.get(dir), point);
        }
        
        return count;
    }
    
    public int[] move(int dir, int[] point) {
        int x = point[0];
        int y = point[1];
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        
        if(nx >= -5 && nx <= 5 && ny >= -5 && ny <= 5) {
            if(!visit[convert(x)][convert(y)][convert(nx)][convert(ny)]) {
                count++;
                visit[convert(x)][convert(y)][convert(nx)][convert(ny)] = true;
                visit[convert(nx)][convert(ny)][convert(x)][convert(y)] = true;
                return new int[]{ nx, ny };
            }
            
            return new int[]{ nx, ny };
        }
        
        return point;
    }
    
    public int convert(int coordinate) {
        return coordinate >= 0 ? coordinate : Math.abs(coordinate) + 5;
    }
    
    
}