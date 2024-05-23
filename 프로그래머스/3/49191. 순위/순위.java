class Solution {
    public int solution(int n, int[][] results) {
        int[][] arr = new int[n+1][n+1];
        for(int[] result : results) {
            int win = result[0];
            int lose = result[1];
            arr[win][lose] = 1;
            arr[lose][win] = -1;
        }
        
        for(int k = 1; k <= n; ++k) {
            for(int i = 1; i <= n; ++i) {
                for(int j = 1; j <= n; ++j) {
                    if(arr[i][k] == 1 && arr[k][j] == 1) arr[i][j] = 1;
                    if(arr[i][k] == -1 && arr[k][j] == -1) arr[i][j] = -1;
                }
            }
        }
        
        int answer = 0;
        for(int i = 1; i <= n; ++i) {
            int count = 0;
            for(int j = 1; j <= n; ++j) {
                if(arr[i][j] != 0) count++;
            }
            
            if(count == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
}