import java.io.*;
import java.util.*;

/**
 * 21:15 START!
 */
public class Main
{
    /**
     * 완전 탐색? 2^30 ~= 1_073_741_824 (10억) ~= 시간초과 😢
     * 🤔 시간을 줄일 수 있는 방법?
     *  1. 문제를 n/2로 나눠서 각각의 모든 경우를 구한다. O(2^15) ~= 32_768
     *     - left, right 에 각각의 경우의 수를 저장한다.
     *  2. left의 값을 기준으로 더해서 c 범위 안에 들어갈 수 있는 right 경우를 모두 구한다.
     *     - 이분탐색을 활용한다 O(nlogn) ~= 15logn15
     */
    static int n, c;
    static int[] weight;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    
	    n = Integer.parseInt(st.nextToken());
	    c = Integer.parseInt(st.nextToken());
	    weight = new int[n];
	    
	    st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) {
	        weight[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    int middle = n / 2;
	    
	    List<Integer> left = new ArrayList<>();
	    List<Integer> right = new ArrayList<>();
	    
	    dfs(0, middle, 0, left);
	    dfs(middle, n, 0, right);
	    
	    right.sort((a, b) -> a - b);
	    
	    long count = 0;
	    for(int i = 0; i < left.size(); ++i) {
	        long cases = find(right, c - left.get(i));
	        count += cases;
	    }
	    
	    System.out.println(count);
	}
	
	public static void dfs(int level, int goal, int total, List<Integer> result) {
	    if(total > c) return;
	    if(level == goal) {
	        result.add(total);
	        return;
	    }
	    
	    // 선택 X
	    dfs(level + 1, goal, total, result);
	    
	    // 선택 O
	    dfs(level + 1, goal, weight[level] + total, result);
	}
	
	// target 보다 작거나 같은 수의 갯수
	public static int find(List<Integer> list, int target) {
	    int left = 0;
	    int right = list.size() - 1;
	    
	    int answer = -1;
	    while(left <= right) {
	        int mid = left + (right - left) / 2;
	        
	        if(list.get(mid) <= target) {
	            answer = mid;
	            left = mid + 1;
	        } else {
	            right = mid - 1;
	        }
	    }
	    
	    return answer + 1;
	}
}