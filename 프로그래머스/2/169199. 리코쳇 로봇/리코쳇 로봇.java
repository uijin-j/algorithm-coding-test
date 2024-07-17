import java.util.*;

// 최단거리를 구하는 문제이기 때문에, BFS
// 하지만, 상하좌우 1칸이 아닌 계속 나아가는 것 
// Q. 길이 없을 때는 어떻게 멈추지? 처음에는 방문 check! (지나왔던 길을 또가면 루프로 간주!) but 시간초과.. 풀이를 보니, 방문 길이를 저장해서 더 짧으면 지나갈 수 있게!
class Solution {
    int height, width;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    
    class Node {
        int[] point;
        int count;
        
        public Node(int[] point, int count) {
            this.point = point;
            this.count = count;
        }
    }
    
    public int solution(String[] board) {
        height = board.length;
        width = board[0].length();
        
        int[][] check = new int[height][width];
        for(int i = 0; i < height; ++i) Arrays.fill(check[i], Integer.MAX_VALUE);
        
        int[] start = {-1, -1};
        int[] goal = {-1, -1};
        for(int x = 0; x < board.length; ++x) {
            int idx = board[x].indexOf('R');
            if(idx != -1) start = new int[]{x, idx};
            
            idx = board[x].indexOf('G');
            if(idx != -1) goal = new int[]{x, idx};
            
            if(start[0] != -1 && goal[0] != -1) break;
        }
        
        check[start[0]][start[1]] = 0;
        
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            for(int direction = 0; direction < 4; ++direction) {
                int[] next = go(direction, node.point, board);
                int nx = next[0];
                int ny = next[1];

                if(check[nx][ny] < node.count + 1) continue;
                if(nx == goal[0] && ny == goal[1]) return node.count + 1;

                check[nx][ny] = node.count + 1;
                q.add(new Node(new int[]{nx, ny}, node.count + 1));
            }
        }
        
        return -1;
    }
    
    public int[] go(int direction, int[] point, String[] board) {
        int x = point[0];
        int y = point[1];
        
        while(true) {
            x += dx[direction];
            y += dy[direction];
            
            if(x < 0 || x >= height || y < 0 || y >= width || board[x].charAt(y) == 'D') {
                break;
            }
        }
            
        x -= dx[direction];
        y -= dy[direction];
        
        return new int[]{x, y};
    }
} 