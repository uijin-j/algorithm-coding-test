/**
 * 22:27 시작! 23:30중지!
 * 23:50 재시작!
 */
class Solution {
    /**
     * 완전 탐색?
     * 4방향 * 가로 N * 세로 N => OK
     */
    int N, M;
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        
        int need = 0; // 채워야 하는 홈의 수
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                if(lock[i][j] == 0) {
                    need++;
                }
            }
        }
        
        for(int d = 0; d < 4; ++d) {
            key = clock(key);
            for(int i = -1 * (M - 1); i < N; ++i) {
                for(int j = -1 * (M - 1); j < N; ++j) {
                    // key의 모든 좌표를 (i, j)만큼 이동
                    int count = 0; // 열쇠의 돌기가 자물쇠의 홈과 맞는 수
                    boolean impossible = false;
                    for(int x = i; x < M + i; ++x) {
                        if(x >= N) break;
                        if(x < 0) continue;
                        
                        for(int y = j; y < M + j; ++y) {
                            if(y >= N) break;
                            if(y < 0) continue;
                            
                            if(key[x-i][y-j] == 1 && lock[x][y] == 0) count++;
                            if(key[x-i][y-j] == 1 && lock[x][y] == 1) {
                                impossible = true;
                                break;
                            }
                        }
                        
                        if(impossible) break;
                    }
                    
                    if(!impossible && count == need) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public int[][] clock(int[][] key) {
        int len = key.length;
        int[][] result = new int[len][len];
        
        for(int i = 0; i < len; ++i) { // i번 행이 len - 1 - i 열이 됨!
            for(int j = 0; j < len; ++j) {
                result[j][len - 1 - i] = key[i][j];
            }
        }
        
        return result;
    }
    
    public int[][] copyAndShift(int[][] arr, int dx, int dy) {
        int len = arr.length;
        int[][] result = new int[len][len];
        
        for(int i = 0; i < len; ++i) {
            for(int j = 0; j < len; ++j) {
                result[i][j] = arr[i][j];
            }
        }
        
        return result;
    }
}