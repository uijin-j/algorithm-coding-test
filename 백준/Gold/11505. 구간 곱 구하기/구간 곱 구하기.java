import java.io.*;
import java.util.*;

/**
 * 22:05 시작!
 */
public class Main
{
    /**
     * 누적합?
     */
    static int MOD = 1_000_000_007;
    static long[] nums, tree;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    StringBuilder answer = new StringBuilder();
	    
	    int n = Integer.parseInt(st.nextToken()); // 최대 100만
	    int m = Integer.parseInt(st.nextToken()); // 최대 1만
	    int k = Integer.parseInt(st.nextToken()); // 최대 1만
	    
	    nums = new long[n + 1];
	    for(int i = 1; i <= n; ++i) {
	        nums[i] = Integer.parseInt(bf.readLine());
	    }
	    
	    tree = new long[n * 4];
	    initTree(1, n, 1);
	    
	    for(int i = 0; i < m + k; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int cmd = Integer.parseInt(st.nextToken());
	        int arg1 = Integer.parseInt(st.nextToken());
	        int arg2 = Integer.parseInt(st.nextToken());
	        
	        if(cmd == 1) {
				update(1, n, 1, arg1, arg2);
	            continue;
	        }
	        
	        // cmd == 2
	        answer.append(multiply(1, n, 1, arg1, arg2) % MOD)
	            .append("\n");
	    }
	    
	    System.out.println(answer.toString());
	}
	
	static long initTree(int start, int end, int node) {
		if(start == end) return tree[node] = nums[start];
		
		int mid = (start + end) / 2;
		return tree[node] = (initTree(start, mid, node * 2) * initTree(mid + 1, end, node * 2 + 1)) % MOD;
	}
	
	static long update(int start, int end, int node, int idx, int value) {
		if(end < idx || idx < start) return tree[node];
		if(start == end) return tree[node] = value;
		
		int mid = (start + end) / 2;
		return tree[node] = (update(start, mid, node * 2, idx, value) * update(mid + 1, end, node * 2 + 1, idx, value)) % MOD;
	}
	
	static long multiply(int start, int end, int node, int left, int right) {
		if(end < left || right < start) return 1;
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		return (multiply(start, mid, node * 2, left, right)* multiply(mid + 1, end, node * 2 + 1, left, right)) % MOD;
	}
}