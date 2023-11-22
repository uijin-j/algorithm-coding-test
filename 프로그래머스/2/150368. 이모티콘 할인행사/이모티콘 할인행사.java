class Solution {
    int[][] originalUsers;
    int[] answer;
    
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        originalUsers = users;
        
        // user[i][0]: i+1 유저의 할인율 / user[i][0]: i+1 유저의 이모티콘+ 돈 (유저는 최대 100, 비율을 최대 40%, 가격은 1,000,000원)
        // emoticons[i]: i+1번 이모지의 정가 (이모지는 최대 7)
        // 이모지가 가질 수 있는 할인율 10, 20, 30, 40
        // 모든 경우?
        // dp또는 분할 정복으로 풀 수 있을 것 같다.
        // dp[i][j] = 0 ~ i번째 이모지까지 있을 때, 
        // dfs()로 일단 풀어볼까?
        
        dfs(0, users, emoticons);
        
        return answer;
    }
    
    public void dfs(int depth, int[][] users, int[] emoticons) {
        if(depth == emoticons.length) { // 모든 이모지의 할인율을 결정했다면
            int[] result = getCountAndTotal(users);
            int count = result[0];
            int total = result[1];
            
            if(answer[0] < count) {
                answer[0] = count;
                answer[1] = total;
            } else if(answer[0] == count && answer[1] < total) {
                answer[1] = total;
            }
            
            return;
        }
        
        int emoticon = emoticons[depth];
        int[] money = new int[4];
        for(int discount = 10, i = 0; discount < 50; discount += 10, ++i) {
            money[i] = emoticon - (emoticon * discount / 100);
        }
            
        for(int discount = 10, i = 0; discount < 50; discount += 10, ++i) {
            int[][] newUsers = new int[users.length][2];
            
            for(int j = 0; j < users.length; ++j) {
                if(users[j][0] <= discount) { // 이모지를 산다.
                    newUsers[j][0] = users[j][0];
                    newUsers[j][1] = users[j][1] - money[i];
                } else {
                    newUsers[j][0] = users[j][0];
                    newUsers[j][1] = users[j][1];
                }
            }
            
            dfs(depth+1, newUsers, emoticons);
        }
    }
    
    public int[] getCountAndTotal(int[][] users) {
        int count = 0, total = 0;
        for(int i = 0; i < users.length; ++i) {
            int[] user = users[i];
            if(user[1] <= 0) count++;
            else total += originalUsers[i][1] - user[1];
        }
        
        return new int[]{count, total};
    }
}