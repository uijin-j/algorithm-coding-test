import java.io.*;
import java.util.*;

// 16:43 시작!
public class Main {
    /**
     * 발판을 누를 수 있는 최소 힘
     * -> 같은 위치 (1) => 중간에서 다른 지점(2) => 지점에서 인접 지점(3) => 반대편 (4)
     * -> 완전탐색? 2^100_000
     * -> 그리디로 풀어도 될까?
     */
    static List<Integer> nums;
    static int[][][] dp;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    
	    nums = new ArrayList<>();
	    while(true) {
	        int num = Integer.parseInt(st.nextToken()); 
	        if(num == 0) break; // 종료
	        nums.add(num);
	    }
	    
	    int n = nums.size();
	    if(n == 0) {
	        System.out.println(0);
	        return;
	    }
	    
	    dp = new int[5][5][n];
	    
	    int last = nums.get(n - 1);
	    for(int x = 0; x < 5; ++x) {
            for(int y = 0; y < 5; ++y) {
                int left = calc(x, last);
                int right = calc(y, last);
                dp[x][y][n - 1] = Math.min(left, right);
            }
        }
        
	    for(int i = n - 2; i >= 0; --i) {
	        for(int x = 0; x < 5; ++x) {
	            for(int y = 0; y < 5; ++y) {
	                int num = nums.get(i);
	                int left = calc(x, num) + dp[num][y][i+1];
                    int right = calc(y, num) + dp[x][num][i+1];
	                dp[x][y][i] = Math.min(left, right);
	            }
	        }
	    }
	    
	    System.out.println(dp[0][0][0]);
	}
	
	public static int calc(int from, int to) {
	    if(from == to) return 1;
	    if(from == 0) return 2;
	    if(from % 2 == to % 2) return 4;
	    return 3;
	}
}