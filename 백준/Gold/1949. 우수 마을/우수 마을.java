import java.io.*;
import java.util.*;

// 16:36 시작!
public class Main
{
    /**
     * 마을이 N(최대 10,000)개
     * 완전 탐색? (조건이 있기 때문에 엄청 많진 않을 것 같음..) => 시간 초과
     * 
     */
    static int n;
    static int[] count;
    static int[][] dp;
    static List<Integer>[] tree;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    count = new int[n + 1];
	    
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 1; i <= n; ++i) {
	       count[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    tree = new List[n + 1];
	    for(int i = 0; i <= n; ++i) {
	        tree[i] = new ArrayList<>();
	    }
	    
	    for(int i = 0; i < n - 1; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        int to = Integer.parseInt(st.nextToken());
	        tree[from].add(to);
	        tree[to].add(from);
	    }
	    
	    dp = new int[n + 1][2]; // dp[i][0]: i 마을이 우수 마을 X일 때, 하위 우수 마을들의 주민 수 합의 최대
	    findMax(1, 0);
	    
	    System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
	
	public static void findMax(int city, int parent) {
	    // dp[city][0] : city 마을이 우수 마을 X 일 때
	    // dp[city][1] : city 마을이 우수 마을 O 일 때
	    for(int child : tree[city]) {
	        if(child == parent) continue;
	        findMax(child, city);
	        dp[city][0] += Math.max(dp[child][0], dp[child][1]);
	        dp[city][1] += dp[child][0];
	    }
	    
	    dp[city][1] += count[city];
	}
}