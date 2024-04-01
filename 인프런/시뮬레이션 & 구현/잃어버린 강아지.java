import java.util.*;
class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static class Moveable {
        int x, y;
        int direction;

        public Moveable(int x, int y) {
            this.x = x;
            this.y = y;
            direction = 0;
        }

        public void move(int[][] board) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if(nx >= 0 && nx < 10 && ny >= 0 && ny < 10 && board[nx][ny] != 1) {
                x = nx;
                y= ny;
            } else {
                turn();
            }
        }

        public void turn() {
            direction = (direction + 1) % 4;
        }

        public int[] getPosition() {
            return new int[]{x, y};
        }
    }

	public int solution(int[][] board){
        Moveable dog = new Moveable(0, 0), hyunsu = new Moveable(0, 0);
        for(int i = 0; i < 10; ++i) {
            for(int j = 0; j < 10; ++j) {
                if(board[i][j] == 2) hyunsu = new Moveable(i, j);
                if(board[i][j] == 3) dog = new Moveable(i, j);
            }
        }

        for(int i = 0; i <= 10000; ++i) {
            int[] hP = hyunsu.getPosition();
            int[] dP = dog.getPosition();

            if(hP[0] == dP[0] && hP[1] == dP[1]) return i;

            hyunsu.move(board);
            dog.move(board);
        }
        
		
		return 0;		
	}

	public static void main(String[] args){
		Solution T = new Solution();
		int[][] arr1 = {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 
			{0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 2, 0, 0}, 
			{1, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 3, 0, 0, 0, 1}, 
			{0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, 
			{0, 1, 0, 1, 0, 0, 0, 0, 0, 0}}; 
		System.out.println(T.solution(arr1));
		int[][] arr2 = {{1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 
			{0, 0, 1, 1, 0, 0, 0, 1, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, 
			{1, 0, 0, 0, 0, 0, 1, 0, 1, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 1, 0, 0, 0, 0, 0, 2, 1}, 
			{0, 0, 0, 1, 0, 1, 0, 0, 0, 1}, 
			{0, 1, 0, 1, 0, 0, 0, 0, 0, 3}}; 
		System.out.println(T.solution(arr2));
	}
}
