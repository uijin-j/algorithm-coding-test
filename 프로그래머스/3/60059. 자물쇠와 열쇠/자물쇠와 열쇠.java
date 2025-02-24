class Solution {
    /**
     * 4방향으로 돌려서 모든 경우를 확인!
     */
    public boolean solution(int[][] key, int[][] lock) {
        int n = key.length;
        int m = lock.length;
        int[][] temp = new int[m+(n-1)*2][m+(n-1)*2];
        int count = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < m; ++j) {
                temp[i+n-1][j+n-1] = lock[i][j];
                if(lock[i][j] == 0) count++;
            }
        }
        
        lock = temp;
        
        for(int k = 0; k < 4; ++k) {
            // 1. 열쇠를 90도 회전
            key = rotate(key);
            
            // 2. 모든 칸을 맞춰보기
            for(int i = 0; i < m+n-1; ++i) {
                for(int j = 0; j < m+n-1; ++j) {
                    int match = 0;
                    boolean possible = true;
                    for(int x = 0; x < n; ++x) {
                        for(int y = 0; y < n; ++y) {
                            if(x+i < n-1 || x+i >= n+m-1) continue;
                            if(y+j < n-1 || y+j >= n+m-1) continue;
                            
                            if(lock[x+i][y+j] == key[x][y]) {
                                possible = false;
                                break;
                            }
                            
                            if(lock[x+i][y+j] == 0) {
                                match++;
                            }
                        }
                        
                        if(!possible) break;
                    }
                    
                    if(possible && match == count) return true;
                }
            }
        }
        
        return false;
    }
    
    public int[][] rotate(int[][] key) {
        int n = key.length;
        int[][] result = new int[n][n];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                result[j][n-i-1] = key[i][j];
            }
        }
        
        return result;
    }
}