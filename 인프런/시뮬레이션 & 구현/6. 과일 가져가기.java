import java.util.*;

class Solution {
	public int solution(int[][] fruit){
        int n = fruit.length; // 학생 수
        boolean[] check = new boolean[n]; // 교환을 했다면, true

        for(int student = 0; student < n; ++student) {
            if(check[student]) continue;
            for(int i = 0; i < n; ++i) {
                if(check[i] || i == student) continue;
                if(exchange(student, i, fruit)) {
                    check[student] = true;
                    check[i] = true;
                    break;
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < n; ++i) {
            int min = Math.min(Math.min(fruit[i][0], fruit[i][1]), fruit[i][2]);
            answer += min;
        }
		
		return answer;
	}

    public static boolean exchange(int s1, int s2, int[][] fruit) {;
        int min1 = Math.min(Math.min(fruit[s1][0], fruit[s1][1]), fruit[s1][2]);
        int min2 = Math.min(Math.min(fruit[s2][0], fruit[s2][1]), fruit[s2][2]);

        for(int i = 0; i < 3; ++i) {
            fruit[s1][i] -= 1;
            fruit[s2][i] += 1;
            for(int j = 0; j < 3; ++j) {
                fruit[s2][j] -= 1;
                fruit[s1][j] += 1;

                int after1 = Math.min(Math.min(fruit[s1][0], fruit[s1][1]), fruit[s1][2]);
                int after2 = Math.min(Math.min(fruit[s2][0], fruit[s2][1]), fruit[s2][2]);

                if(min1 < after1 && min2 < after2) { // 두 명 모두에게 이득
                    return true;
                }

                fruit[s2][j] += 1;
                fruit[s1][j] -= 1;
            }
            fruit[s1][i] += 1;
            fruit[s2][i] -= 1;
        }

        return false;
    }

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution(new int[][]{{10, 20, 30}, {12, 15, 20}, {20, 12, 15}, {15, 20, 10}, {10, 15, 10}}));
		System.out.println(T.solution(new int[][]{{10, 9, 11}, {15, 20, 25}}));	
		System.out.println(T.solution(new int[][]{{0, 3, 27}, {20, 5, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}}));
		System.out.println(T.solution(new int[][]{{3, 7, 20}, {10, 15, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}, {12, 12, 6}, {10, 20, 0}, {5, 10, 15}}));
	}
}
