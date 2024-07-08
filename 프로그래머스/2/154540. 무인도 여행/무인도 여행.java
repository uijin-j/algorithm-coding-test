import java.util.*;
import java.util.stream.*;

class Solution {
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    public int[] solution(String[] maps) {
        List<Integer> islands = new ArrayList<>();
        
        int[][] map = new int[maps.length][maps[0].length()];
        for(int i = 0; i < maps.length; ++i) {
            for(int j = 0; j < maps[i].length(); ++j) {
                map[i][j] = maps[i].charAt(j) == 'X' ? -1 : maps[i].charAt(j) - '0';
            }
        }
        
        for(int i = 0; i < map.length; ++i) {
            for(int j = 0; j < map[i].length; ++j) {
                if(map[i][j] != -1) {
                    islands.add(dfs(i, j, map));
                }
            }
        }
        
        if(islands.isEmpty()) return new int[]{ -1 };
        
        islands.sort((a, b) -> a - b);
        
        int[] answer = new int[islands.size()];
        for(int i = 0; i < islands.size(); ++i) {
            answer[i] = islands.get(i);
        }
        
        return answer;
    }
    
    public int dfs(int x, int y, int[][] map) {
        int days = map[x][y];
        map[x][y] = -1;
        
        for(int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length && map[nx][ny] != -1) {
                days += dfs(nx, ny, map);
            }
        }
        
        return days;
    }
}