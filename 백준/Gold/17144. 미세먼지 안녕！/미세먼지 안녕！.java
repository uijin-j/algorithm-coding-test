import java.util.*;

public class Main {
    static int R, C, T;
    static int[] dx = {-1, 0 , 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Point {
        int x, y, dust;

        public Point(int x, int y, int dust) {
            this.x = x;
            this.y = y;
            this.dust = dust;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setDust(int dust) {
            this.dust = dust;
        }

        public Point afterAirClean(int a, int b) {
            if(x == a || x == b) {
                if(y != C -1) return new Point(x, y + 1, dust);
            }

            if(x == 0 || x == R - 1) {
                if(y != 0) return new Point(x, y - 1, dust);
            }

            if(y == C - 1) {
                if(x <= a) return new Point(x - 1, y, dust);
                if(x >= b) return new Point(x + 1, y, dust);
            }

            if(y == 0) {
                if(x <= a) return new Point(x + 1, y, dust);
                if(x >= b) return new Point(x - 1, y, dust);
            }

            return this;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();

        Queue<Point> q = new LinkedList<>();
        int[] ac = new int[2];
        for(int i = 0; i < R; ++i) {
            for(int j = 0; j < C; ++j) {
                int data = sc.nextInt();
                if(data > 0) 
                    q.offer(new Point(i, j, data));

                if(data == -1) {
                    if(ac[0] == 0) ac[0] = i;
                    else  ac[1] = i;
                }
            }
        }
    
        while(T > 0) {
            if(q.isEmpty()) {
                System.out.println(0);
                return;
            }

            int size = q.size();

            int[][] room = new int[R][C];
            for(int i = 0; i < size; ++i) {
                Point now = q.poll();

                int count = 0;
                int spreading = now.dust / 5;
                for(int j = 0; j < 4; ++j) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];
                    if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        if(!(ny == 0 && (nx == ac[0] || nx == ac[1]))) {
                            count++;
                            Point before = new Point(nx, ny, spreading);
                            Point after = before.afterAirClean(ac[0], ac[1]);
                            room[after.x][after.y] += after.dust;
                        }
                    }
                }

                Point after = now.afterAirClean(ac[0], ac[1]);
                room[after.x][after.y] += after.dust - spreading * count;
            }

            for(int i = 0; i < R; ++i) {
                for(int j = 0; j < C; ++j) {
                    int data = room[i][j];
                    if(j == 0 && (i == ac[0] || i == ac[1])) continue;
                    if(data > 0) q.offer(new Point(i, j, data));
                }
            }

            T--;
        }

        int answer = 0;
        for(Point p : q) answer += p.dust;

        System.out.println(answer);
    }
}