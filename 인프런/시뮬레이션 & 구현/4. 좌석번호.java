import java.util.*;
class Solution {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

	public int[] solution(int c, int r, int k){
        if(k > r * c) return new int[]{0, 0};

        int x = 1, y = 1;
        int direction = 0;
        int[][] seats = new int[c + 1][r + 1];
        seats[1][1] = 1;
        for(int i = 2; i <= k; ++i) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if(nx >= 1 && nx <= c && ny >= 1 && ny <= r && seats[nx][ny] == 0) {
                x = nx;
                y = ny;
                seats[x][y] = i;
            } else {
                direction = (direction + 1) % 4;
                --i;
            }
        }
		
		return new int[]{x, y};
	}

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(Arrays.toString(T.solution(6, 5, 12)));	
		System.out.println(Arrays.toString(T.solution(6, 5, 20)));
		System.out.println(Arrays.toString(T.solution(6, 5, 30)));
		System.out.println(Arrays.toString(T.solution(6, 5, 31)));
	}
}
