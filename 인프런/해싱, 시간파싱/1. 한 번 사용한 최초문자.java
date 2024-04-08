class Solution {
	public int solution(String s){
        int[] count = new int[26]; // 알파벳별로 몇번 사용됐는지 count

        for(char c : s.toCharArray()) {
            count[c - 'a'] += 1;
        }

        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(count[c - 'a'] == 1) {
                return i + 1;
            }
        }

		return -1;
	}

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution("statitsics"));
		System.out.println(T.solution("aabb"));
		System.out.println(T.solution("stringshowtime"));
		System.out.println(T.solution("abcdeabcdfg"));
	}
}
