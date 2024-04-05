import java.util.*;
class Solution {
    int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	public int solution(int[] keypad, String password){
        int[][] distance = new int[10][10]; // distance[i][j]: i에서 j를 누르는 데 필요한 최소 시간
        for(int i = 0; i < 10; ++i) {
            Arrays.fill(distance[i], 2);
            distance[i][i] = 0;
        }
        for(int x = 0; x < 3; ++x) {
            for(int y = 0; y < 3; ++y) {
                int num = keypad[x * 3 + y];
                for(int direction = 0; direction < 8; ++direction) {
                    int nx = x + dx[direction];
                    int ny = y + dy[direction];

                    if(nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                        distance[num][keypad[nx * 3 + ny]] = 1;
                    }
                }
            }
        }

        int answer = 0;
        for(int i = 1; i < password.length(); ++i) {
            int from = password.charAt(i-1) - '0';
            int to = password.charAt(i) - '0';
            answer += distance[from][to];
        }
		
		return answer;
	}

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution(new int[]{2, 5, 3, 7, 1, 6, 4, 9, 8}, "7596218"));	
		System.out.println(T.solution(new int[]{1, 5, 7, 3, 2, 8, 9, 4, 6}, "63855526592"));
		System.out.println(T.solution(new int[]{2, 9, 3, 7, 8, 6, 4, 5, 1}, "323254677"));
		System.out.println(T.solution(new int[]{1, 6, 7, 3, 8, 9, 4, 5, 2}, "3337772122"));
	}
}
