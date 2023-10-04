import java.util.*;

public class Main {
    static int r, c;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; 
    static boolean[] check;
    static int[][] board;
    static int answer;

    static void dfs(int x, int y) {
      if(check[board[x][y]]) {
        int count = 0;
        for(boolean ch: check) if(ch) count++;

        answer = Math.max(answer, count);
        return;
      }

      check[board[x][y]] = true;

      for(int i = 0; i < 4; ++i) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if(nx >= 0 && nx < r && ny >= 0 && ny < c ) dfs(nx, ny);
      }

      check[board[x][y]] = false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        r = sc.nextInt();
        c = sc.nextInt();
        board = new int[r][c];
        check = new boolean[26];

        sc.nextLine();
        for(int i = 0; i < r; ++i) {
          char[] s = sc.nextLine().toCharArray();
          for(int j = 0; j < c; ++j) 
            board[i][j] = s[j] - 'A';
        }

        dfs(0, 0);

        System.out.println(answer);
    }
}