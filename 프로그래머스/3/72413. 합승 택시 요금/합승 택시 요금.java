import java.util.*;

/**
 * 15:25 START!
 * S, A, B를 모두 포함하는 집합의 최단 비용
 * S에서 A, B까지의 최단거리를 구해서 더하기? (이렇게 하면 안됨! 각각의 최단거리가 아니여도 합이 최소가 될 수 있기 때문)
 * dfs?
 *
 * 모든 노드에서 모든 노드로의 최단거리를 구한 뒤(플로이드 와샬) O(n^3) ~= 8_000_000
 * S노드에서 1..N 노드까지 함께 택시를 타고, 하차 노드에서 A, B 노드까지 최소 비용을 구해서 더하기?
 */ 
class Solution {
    static final int INF = 20_000_000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dist = new int[n+1][n+1]; // dist[i][j]는 i노드에서 j노드까지의 최단거리!
        
        for(int i = 0; i <= n; ++i) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        for(int[] fare : fares) {
            dist[fare[0]][fare[1]] = fare[2];
            dist[fare[1]][fare[0]] = fare[2];
        }
        
        for(int k = 1; k <= n; ++k) { // ❗️ 플로이드 와샬은 경유지 K가 젤 바깥 for문에 위치한다..
            for(int i = 1; i <= n; ++i) {
                for(int j = 1; j <= n; ++j) {
                    if(i == j || i == k || j == k) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]); 
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= n; ++i) {
            min = Math.min(min, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        
        return min;
    }
}