// 09:37 START!
class Solution {
    /**
     * solution 1. 모든 경우를 계산? O(4^m) ~= 16384
     */
    int[][] users;
    int[] emoticons;
    int[] discounts = {10, 20, 30, 40};
    int[] answer = new int[2];
    int n, m;
    int[] selected;
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        n = users.length;
        m = emoticons.length;
        selected = new int[m];
        
        dfs(0);
        
        return answer;
    }
    
    public void dfs(int level) {
        if(level == m) {
            int count = 0, total = 0;
            for(int[] user : users) {
                int sum = 0;
                for(int i = 0; i < m; ++i) {
                    if(selected[i] >= user[0]) {
                        sum += emoticons[i] * (100 - selected[i]) / 100;
                    }
                }
                
                if(sum >= user[1]) {
                    count++;
                } else {
                    total += sum;
                }
            }
            
            if(count > answer[0] || (count == answer[0] && total > answer[1])) {
                answer = new int[]{count, total};
            }
            
            return;
        }
        
        for(int i = 0; i < 4; ++i) {
            selected[level] = discounts[i]; 
            dfs(level + 1);
        }
    }
}