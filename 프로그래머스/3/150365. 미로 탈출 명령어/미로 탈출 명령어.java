import java.util.*;

// 17:06 START!
class Solution {
    /**
     * 그래프 탐색 (bfs)
     * - 일반적인 bfs와 다른 점: 또 방문할 수 있음 / 최단거리가 아닌 일정 거리 k로 가는 방법을 구해야 함
     * 깊이가 k로 정해져 있으니까 dfs가 더 나을 것 같다! => 중복 방문이 허용되기 때문에 O(4^k).. 시초ㅜㅜ
     * 
     * 최단거리를 구한 뒤 상쇄하는 방식은 어떨까? 근데 최단거리를 구한 뒤 상쇄한 방식이 돌아가는 것보다 사전순으로 빠르다고 할 수 있을까?
     * ❗️ 애초에 S, E를 알고 있으니, 최단거리는 알고 있는거나 다름 없음!
     */
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        
        // S에서 E로 가기 위해 최소로 가야하는 방향 수
        int down = Math.max(r - x, 0);
        int left = Math.max(y - c, 0);
        int up = Math.max(x - r, 0);
        int right = Math.max(c - y, 0);
        
        if(k < down + left + up + right) return "impossible";
        if((k - (down + left + up + right)) % 2 == 1) return "impossible"; // 상쇄하려면 2개씩 짝을 지어야 함
        
        int pair = (k - (down + left + up + right)) / 2;
        
        for(int i = 0; i < k; ++i) { // k번 움직이기
            // 아래로 갈 수 있다면, 아래로 가는 것이 이득!
            if(x < n && (down > 0 || pair > 0)) {
                answer += "d";
                x += 1;
                if(down > 0) {
                    down--;
                } else {
                    pair--;
                    up += 1;
                }
                
                continue;
            }
            
            // 왼쪽으로 갈 수 있다면, 왼쪽으로 가는 것이 이득!
            if(y > 1 && (left > 0 || pair > 0)) {
                answer += "l";
                y -= 1;
                if(left > 0) {
                    left--;
                } else {
                    pair--;
                    right += 1;
                }
                
                continue;
            }
            
            // 오른쪽으로 갈 수 있다면, 오른쪽으로 가는 것이 이득!
            if(y < m && (right > 0 || pair > 0)) {
                answer += "r";
                y += 1;
                if(right > 0) {
                    right--;
                } else {
                    pair--;
                    left += 1;
                }
                
                continue;
            }
            
            // 위로 갈 수 있다면, 위로 가는 것이 이득!
            if(x > 1 && (up > 0 || pair > 0)) {
                answer += "u";
                x -= 1;
                if(up > 0) {
                    up--;
                } else {
                    pair--;
                    down += 1;
                }
    
                continue;
            }
        }
        
        return answer;
    }
}