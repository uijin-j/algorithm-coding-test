import java.util.*;

public class Main {
  static int l, r, c;
  static boolean[][][] building;
  static int[] dx = {0, 0, -1, 0, 1, 0};
  static int[] dy = {0, 0, 0, 1, 0, -1};
  static int[] dz = {1, -1, 0, 0, 0, 0};

  static class Point {
    int x, y, z;

    Point() {
    }

    Point(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    while(true) {
      l = sc.nextInt();
      r = sc.nextInt();
      c = sc.nextInt();
      sc.nextLine();
      building = new boolean[r][c][l];

      if(l == 0 && r == 0 && c == 0) {
        break;
      }

      Point start = new Point(), end = new Point();
      for(int i = 0; i < l; ++i) {
        for(int j = 0; j < r; ++j) {
          String input = sc.nextLine();
          for(int k = 0; k < c; ++k) {
            char now = input.charAt(k);
            if(now == 'S') {
              start = new Point(j, k, i);
            } else if(now == 'E') {
              end = new Point(j, k, i);
            } 
            
            if(now != '#') {
              building[j][k][i] = true;
            }
          }
        }
        sc.nextLine();
      }

      int answer = bfs(start, end);
      if(answer == -1) {
        System.out.println("Trapped!");
      } else {
        System.out.println("Escaped in " + answer + " minute(s).");
      }
    }
  }
  static int bfs(Point start, Point end) {
    Queue<Point> q = new LinkedList<>();
    building[start.x][start.y][start.z] = false;
    q.offer(start);

    int depth = 0;
    while(!q.isEmpty()) {
      int size = q.size();

      for(int j = 0; j < size; ++j) {
        Point now = q.poll();

        for(int i = 0; i < 6; ++i) {
          Point next = new Point(now.x + dx[i], now.y + dy[i], now.z + dz[i]);

          if(next.x >= 0 && next.x < r && next.y >= 0 && next.y < c && next.z >= 0 && next.z < l 
                && building[next.x][next.y][next.z]) {
            if(next.x == end.x && next.y == end.y && next.z == end.z) {
              return depth + 1;
            }

            building[next.x][next.y][next.z] = false;
            q.offer(next);
          }
        }
      }

      depth++;
    }

    return -1;
  }
}