import java.util.*;

class Solution {
    /**
     * 완전 탐색? 1명, 2명, 3명 ... m명(최대 8명)
     * Q. m명의 사람으로 모두 커버할 수 있는지는 어떻게 알까?
     * m명의 사람을 나열(m!) / x지점(1, 2 ... 최대 15)부터 차례대로 배치한 뒤 모두 커버하는지 확인
     * 
     * 시간 복잡도? O(m(m!*x)) ~= 8 * 8! * 15 = 4_838_400 
     */
    boolean[] check;
    int[] select, weak, dist;
    int n, numOfFriend, numOfWeak;
    boolean possible;
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        numOfFriend = dist.length;
        numOfWeak = weak.length;
        
        for(int need = 1; need <= numOfFriend; ++need) { // i명의 사람을 선택해서 줄세우기
            check = new boolean[numOfFriend];
            select = new int[need]; // 선택된 사람들 (순서 O)
            dfs(0, need);
            
            if(possible) return need;
        }
        
        return -1;
    }
    
    public void dfs(int level, int goal) {
        if(possible) return; // early return

        if(level == goal) { // 다 뽑았다면?
            for(int i = 0; i < numOfWeak; ++i) {
                // i번째 결함부터 커버하기 시작!
                Queue<Integer> q = new LinkedList<>();
                for(int j = i; j < numOfWeak; ++j) {
                    q.offer(weak[j]);
                }
                
                for(int j = 0; j < i; ++j) {
                    q.offer(n + weak[j]);
                }
                
                int covered = 0;
                for(int j = 0; j < goal; ++j) {
                    if(q.isEmpty()) break;
                    
                    covered = q.poll() + dist[select[j]];
                    while(!q.isEmpty() && q.peek() <= covered) {
                        q.poll();
                    }
                }
                
                if(q.isEmpty()) {
                    possible = true;
                    return;
                }
            }
            
            return;
        }
        
        for(int i = 0; i < numOfFriend; ++i) {
            if(check[i]) continue;
            check[i] = true;
            select[level] = i;
            dfs(level + 1, goal);
            check[i] = false;
        }
    }
}