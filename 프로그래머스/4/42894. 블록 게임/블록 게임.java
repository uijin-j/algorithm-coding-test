import java.util.*;

// 16:05 시작!
class Solution {
    /**
     * 1번 블록은 3-4번 방향일 때 제거 가능 / 2번 블록은 2-3번 방향일 때 제거 가능 / 3번 블록은 1번 방향일 때 제거 가능
     * 위에서부터 먼저 만나는 블록을 가능한 제거
     * O(n^2) ~= O(2_500)
     */
    int[][] board;
    boolean[][] visit;
    boolean[] blocked;
    boolean[] removed;
    int n;
    public int solution(int[][] board) {
        this.board = board;
        n = board.length;
        visit = new boolean[n][n];
        blocked = new boolean[n];
        removed = new boolean[201];
        int answer = 0;
        for(int i = 0; i < n; ++i) {
            PriorityQueue<Block> pq = new PriorityQueue<>((a, b) -> a.maxX - b.maxX);
            for(int j = 0; j < n; ++j) {
                if(board[i][j] == 0) continue;
                if(visit[i][j]) continue;
                
                // 새로운 블록 발견
                Block block = new Block(board[i][j]);
                visit[i][j] = true;
                block.add(i,j);
                dfs(i, j, block);
                
                pq.offer(block);
            }
            
            while(!pq.isEmpty()) {
                Block block = pq.poll();
                
                if(block.isRemovable()) {
                    removed[block.num] = true;
                    answer++;
                    continue;
                }
                
                for(int col = block.minY; col <= block.maxY; ++col) blocked[col] = true;
            }
        }
        
        return answer;
    }
    
    public class Block {
        int num, minX, minY, maxX, maxY;

        public Block(int num) {
            this.num = num;
            this.minX = n;
            this.maxX = -1;
            this.minY = n;
            this.maxY = -1;
        }
        
        public void add(int x, int y) {
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
        
        public boolean isRemovable() {
            int width = maxY - minY + 1;
            boolean[] hasBlock = new boolean[width];
            for(int x = minX; x <= maxX; ++x) {
                for(int y = minY; y <= maxY; ++y) {
                    if(board[x][y] == num) {
                        hasBlock[y - minY] = true;
                        continue;
                    }
                    
                    if(board[x][y] == 0 || removed[board[x][y]]) {
                        if(blocked[y] || hasBlock[y - minY]) return false;
                        continue;
                    }
                    
                    // 다른 블록
                    return false;
                }
            }
            
            return true;
        }
    }
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    public void dfs(int x, int y, Block block) {
        for(int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == block.num && !visit[nx][ny]) {
                visit[nx][ny] = true;
                block.add(nx, ny);
                dfs(nx, ny, block);
            }
        }
    }
}