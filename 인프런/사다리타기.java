import java.util.*;
class Solution {
    // n: 학생 수 (세로 줄의 갯수)
    // ladder[i]: i번째 가로줄과 연결된 위치
    char[] alpha = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	public char[] solution(int n, int[][] ladder){
		char[] answer = new char[n];
        boolean[][] connections = new boolean[ladder.length][n+1];

        for(int i = 0; i < ladder.length; ++i) {
            for(int j : ladder[i]) connections[i][j] = true;
        }
		
        for(int i = 1; i <= n; ++i) {
            int position = i;

            for(int j = 0; j < ladder.length; ++j) {
                if(connections[j][position]) position += 1;
                else if(connections[j][position-1]) position -= 1;
            }
            
            answer[position - 1] = alpha[i - 1];
        }

		return answer;
	}

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(Arrays.toString(T.solution(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
		System.out.println(Arrays.toString(T.solution(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
		System.out.println(Arrays.toString(T.solution(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
		System.out.println(Arrays.toString(T.solution(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
	}
}
