import java.util.*;

/**
 * 14:38 시작!
 */
class Solution {
    /**
     * 보드의 한 변(n)은 최대 100
     * (1,1) -> (n, n) 최단거리? => BFS
     * 일반적인 BFS와 다른 점 좌표를 하나만 차지 X 2개의 좌표를 차지 + 회전할 수 있음
     */
    public class Point {
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Point go(int d) {
            return new Point(x + dx[d], y + dy[d]);
        }
        
        public boolean isValid() {
            return x >= 0 && x < n && y >= 0 && y < n && board[x][y] == 0;
        }
        
        public boolean isArrive() {
            return x == n - 1 && y == n - 1;
        }
    }
    
    public class Robbot {
        Point first, second; // first: 왼쪽 또는 위, second: 오른쪽 또는 아래
        
        public Robbot(Point first, Point second) {
            if(first.x > second.x || first.y > second.y) {
                this.first = second;
                this.second = first;
            } else {
                this.first = first;
                this.second = second;
            }
        }
        
        public boolean isValid() {
            return first.isValid() && second.isValid();
        }
        
        public boolean isArrive() {
            return first.isArrive() || second.isArrive();
        }
        
        public Robbot go(int dir) {
            return new Robbot(first.go(dir), second.go(dir));
        }
        
        public Robbot rotate(int option) {
            // 0: first 기준 시계
            if(option == 0) {
                return retateFirstClock();
            }
            
            // 1: first 기준 반시계
            if(option == 1) {
                return retateFirstCounterClock();
            }
            
            // 2: second 기준 시계
            if(option == 2) {
                return retateSecondClock();
            }
            
            // 3: second 기준 반시계
            if(option == 3) {
                return retateSecondCounterClock();
            }
            
            return none();
        }
        
        public Robbot retateFirstClock() {
            if(first.x == second.x) {
                Point path = new Point(first.x + 1, first.y + 1);
                if(path.isValid()) {
                    return new Robbot(new Point(first.x, first.y), new Point(first.x + 1, first.y));
                }
                
                return none();
            }
            
            Point path = new Point(first.x + 1, first.y - 1);
            if(path.isValid()) {
                return new Robbot(new Point(first.x, first.y), new Point(first.x, first.y - 1));
            }

            return none();
        }
        
        public Robbot retateFirstCounterClock() {
            if(first.x == second.x) {
                Point path = new Point(first.x - 1, first.y + 1);
                if(path.isValid()) {
                    return new Robbot(new Point(first.x, first.y), new Point(first.x - 1, first.y));
                }
                
                return none();
            }
            
            Point path = new Point(first.x + 1, first.y + 1);
            if(path.isValid()) {
                return new Robbot(new Point(first.x, first.y), new Point(first.x, first.y + 1));
            }

            return none();
        }
        
        public Robbot retateSecondClock() {
            if(first.x == second.x) {
                Point path = new Point(second.x - 1, second.y - 1);
                if(path.isValid()) {
                    return new Robbot(new Point(second.x - 1, second.y), new Point(second.x, second.y));
                }
                
                return none();
            }
            
            Point path = new Point(second.x - 1, second.y + 1);
            if(path.isValid()) {
                return new Robbot(new Point(second.x, second.y + 1), new Point(second.x, second.y));
            }

            return none();
        }
        
        public Robbot retateSecondCounterClock() {
            if(first.x == second.x) {
                Point path = new Point(second.x + 1, second.y - 1);
                if(path.isValid()) {
                    return new Robbot(new Point(second.x + 1, second.y), new Point(second.x, second.y));
                }
                
                return none();
            }
            
            Point path = new Point(second.x - 1, second.y - 1);
            if(path.isValid()) {
                return new Robbot(new Point(second.x, second.y - 1), new Point(second.x, second.y));
            }

            return none();
        }
        
        public Robbot none() {
            return new Robbot(new Point(-1, -1), new Point(-1, -1));
        }
    }
    
    int n;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int[][] board;
    boolean[][][][] check; // BFS이긴 하지만 중복 방문할 가능성이 있음!
    public int solution(int[][] board) {
        this.board = board; 
        n = board.length;
        
        check = new boolean[n][n][n][n];
        
        Queue<Robbot> q = new LinkedList<>();
        q.offer(new Robbot(new Point(0, 0), new Point(0, 1)));
        
        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; ++i) {
                Robbot bot = q.poll();

                // 상 하 좌 우
                //System.out.println("상 하 좌 우");
                for(int d = 0; d < 4; ++d) {
                    Robbot nR = bot.go(d);
                    
                    //System.out.println("(" + nR.first.x + ", " + nR.first.y + ") " + "(" + nR.second.x + ", " + nR.second.y + ") ");
                    
                    if(nR.isValid()) {
                        if(check[nR.first.x][nR.first.y][nR.second.x][nR.second.y]) continue;
                        check[nR.first.x][nR.first.y][nR.second.x][nR.second.y] = true;
                        
                        //System.out.println(" 유효! ");
                        
                        if(nR.isArrive()) return level + 1;
                
                        q.offer(nR);
                    }
                }
                
                //System.out.println("시계 반시계 시계 반시계");
                // 회전(first 기준 시계/반시계, second 기준 시계/반시계)
                for(int j = 0; j < 4; ++j) {
                    Robbot nR = bot.rotate(j);
                    //System.out.println("(" + nR.first.x + ", " + nR.first.y + ") " + "(" + nR.second.x + ", " + nR.second.y + ") ");
                    
                    if(nR.isValid()) {
                        if(check[nR.first.x][nR.first.y][nR.second.x][nR.second.y]) continue;
                        check[nR.first.x][nR.first.y][nR.second.x][nR.second.y] = true;
                        
                        //System.out.println(" 유효! ");
                        
                        if(nR.isArrive()) return level + 1;
                        
                        q.offer(nR);
                    }
                }
            }
            
            level++;
        }
        
        return level;
    }
}