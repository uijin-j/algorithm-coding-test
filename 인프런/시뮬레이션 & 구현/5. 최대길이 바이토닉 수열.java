import java.util.*;

class Solution {
	public int solution(int[] nums){
		int answer = 0;
        	int n = nums.length;
        	List<Integer> peeks = new ArrayList<>();

	        for(int i = 1; i < n - 1; ++i) { // 후보 찾기
	            int num = nums[i];
	            if(num > nums[i-1] && num > nums[i+1]) {
	                peeks.add(i);
	            }
	        }

	        for(int peek: peeks) {
	            int left = peek - 1, right = peek + 1;
	            int num = nums[peek];
	            int lL = 0, rL = 0;
	            while(left >= 0) {
	                if(nums[left] >= num) break;
	                num = nums[left];
	                ++lL;
	                --left;
	            }
	        
	            num = nums[peek];
	            while(right < n) {
	                if(nums[right] >= num) break;
	                num = nums[right];
	                ++rL;
	                ++right;
	            }
	
	            answer = Math.max(answer, lL + rL + 1);
	         }


		return answer;	
	}

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution(new int[]{1, 3, 2, 5, 7, 4, 2, 5, 1}));
		System.out.println(T.solution(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
		System.out.println(T.solution(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
		System.out.println(T.solution(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
	}
}
