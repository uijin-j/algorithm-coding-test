import java.util.*;

class Solution {
    List<List<Integer>> list;
    boolean[] visited;
    public int solution(int n, int[][] wires) {
        list = new ArrayList<>();
        for(int i = 0; i <= n; ++i) list.add(new ArrayList<>());
        for(int[] wire : wires) {
            list.get(wire[0]).add(wire[1]);
            list.get(wire[1]).add(wire[0]);
        }
        
        int min = n;
        for(int[] wire : wires) { // wire를 끊기
            visited = new boolean[n+1];
            visited[wire[0]] = true;
            int connection = dfs(wire[0], wire, list);
            min = Math.min(min, Math.abs(n - connection - connection));
            System.out.println();
        }
        
        return min;
    }
    
    // except를 제외했을 때, point와 연결된 송신탑의 수
    public int dfs(int point, int[] except, List<List<Integer>> wires) {
        int count = 1;
        for(int node : wires.get(point)) {
            if((point == except[0] && node == except[1]) ||  (point == except[1] && node == except[0])) {
                continue;
            }
            
            if(!visited[node]) {
                visited[node] = true;
                count += dfs(node, except, wires);
            }
        }
        
        return count;
    }
}