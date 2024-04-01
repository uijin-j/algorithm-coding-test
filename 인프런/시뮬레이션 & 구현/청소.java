import java.util.*;
class Solution {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1}; 
	
	public static class Cleaner {
		private int x = 0;
		private int y = 0;
		private int direction = 1;
	
		public void move(int[][] board) {
		    int n = board.length;
		    int nx = x + dx[direction];
		    int ny = y + dy[direction];
		
		    if(nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0) {
			x = nx;
			y = ny;
		    } else {
			turn();
		    }
		}
		
		public int[] getPosition() {
		    return new int[]{x, y};
		}
		
		private void turn() {
		    direction = (direction + 1) % 4;
		}
	} 

	public int[] solution(int[][] board, int k){
	Cleaner cleaner = new Cleaner();
	for(int i = 0; i < k; ++i) {
	    cleaner.move(board);
	}
	
		return cleaner.getPosition();
	}
	
	public static void main(String[] args){
		Solution T = new Solution();
		int[][] arr1 = {{0, 0, 0, 0, 0}, 
			{0, 1, 1, 0, 0}, 
			{0, 0, 0, 0, 0}, 
			{1, 0, 1, 0, 1}, 
			{0, 0, 0, 0, 0}};
		System.out.println(Arrays.toString(T.solution(arr1, 10)));
		int[][] arr2 = {{0, 0, 0, 1, 0, 1}, 
			{0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 1}, 
			{1, 1, 0, 0, 1, 0}, 
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0}};
		System.out.println(Arrays.toString(T.solution(arr2, 20)));
		int[][] arr3 = {{0, 0, 1, 0, 0}, 
			{0, 1, 0, 0, 0}, 
			{0, 0, 0, 0, 0}, 
			{1, 0, 0, 0, 1}, 
			{0, 0, 0, 0, 0}};
		System.out.println(Arrays.toString(T.solution(arr3, 25)));
		
	}
}
