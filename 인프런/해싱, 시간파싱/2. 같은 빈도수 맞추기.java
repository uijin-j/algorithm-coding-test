import java.util.*;
class Solution {
	public int[] solution(String s){
		int[] answer = new int[5];
        int[] count = new int[5]; // 빈도수 저장
        int max = 0;

        for(char c : s.toCharArray()) {
            count[c - 'a'] += 1;
            max = Math.max(max, count[c - 'a']);
        }

        for(int i = 0; i < 5; ++i) {
            answer[i] = max - count[i];
        }
		
		return answer;
	}

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(Arrays.toString(T.solution("aaabc")));
		System.out.println(Arrays.toString(T.solution("aabb")));
		System.out.println(Arrays.toString(T.solution("abcde")));
		System.out.println(Arrays.toString(T.solution("abcdeabc")));
		System.out.println(Arrays.toString(T.solution("abbccddee")));
	}
}
