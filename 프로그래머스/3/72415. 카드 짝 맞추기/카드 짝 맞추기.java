import java.util.*;

// 22:03 START!  23:30 STOP! (1시간 30분)
// 17:22 RESTART!
class Solution {
    /**
     * 1. 각 카드를 뽑는 순서를 정한다. 최대 8개 O(8! * 2^8)
     * 2. 순서대로 카드를 뽑는다. (최소한의 움직임으로!)
     * 참고) 엔터 입력은 카드 수만큼 한다.
     */
    int[][] board;
    int[] start;
    HashMap<Integer, List<int[]>> map = new HashMap<>(); 
    int numOfPair = 0;
    boolean[] check;
    int min = Integer.MAX_VALUE;
    public int solution(int[][] board, int r, int c) {    
        this.board = board;
        start = new int[]{r, c};
        
        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                if(board[i][j] > 0) {
                    int num = board[i][j];
                    map.putIfAbsent(num, new ArrayList<>());
                    map.get(num).add(new int[]{i, j});
                }
            }
        }
        
        numOfPair = map.size();

        check = new boolean[9];
        Deque<int[]> stack = new ArrayDeque<>();
        play(0, stack);
    
        return min;
    }
    
    public void play(int level, Deque<int[]> stack) {
        if(level == numOfPair) {
            int[] point = new int[]{start[0], start[1]};
            int[][] copy = copyArray(board);
            int total = 0;
            
            for(int[] target : stack) {
                int count = go(point, target, copy);
                total += count + 1;
                point = new int[]{target[0], target[1]};
                copy[target[0]][target[1]] = 0;
            }

            min = Math.min(min, total);
            return;
        }
        
        for(int num : map.keySet()) {
            if(!check[num]) {
                check[num] = true;
                
                for(int i = 0; i < 2; ++i) {
                    stack.push(map.get(num).get(i));
                    stack.push(map.get(num).get((i + 1) % 2));
                    play(level + 1, stack);
                    stack.pop();
                    stack.pop();
                }
                
                check[num] = false;
            }
        }
    }
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    
    // point에서 target으로 가는 최단거리
    public int go(int[] point, int[] target, int[][] board) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(point[0], point[1], 0));
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            if(node.x == target[0] && node.y == target[1]) {
                return node.count;
            }
            
            for(int d = 0; d < 4; ++d) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                
                if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {    
                    q.offer(new Node(nx, ny, node.count + 1));
                }
                
                // ctrl
                int[] np = ctrl(d, node.x, node.y, board);
                nx = np[0];
                ny = np[1];
                if(nx == target[0] && ny == target[1]) {
                    point = new int[]{nx, ny};
                    return node.count + 1;
                }
                q.offer(new Node(nx, ny, node.count + 1));
            }
        }
        
        return -1;
    }
    
    public int[] ctrl(int d, int x, int y, int[][] board) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        while(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
            if(board[nx][ny] > 0) return new int[]{nx, ny};
            nx += dx[d];
            ny += dy[d];
        }
        
        nx -= dx[d];
        ny -= dy[d];
        return new int[]{nx, ny};
    }
    
    public class Node {
        int x;
        int y;
        int count;
        
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    public int[][] copyArray(int[][] board) {
        int[][] result = new int[4][4];
        
        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                result[i][j] = board[i][j];
            }
        }
        
        return result;
    }
}