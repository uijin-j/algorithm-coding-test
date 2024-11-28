import java.io.*;
import java.util.*;

// 21:30 시작! 
public class Main
{
    static int[] nums, ops, count;
    static int n, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    nums = new int[n];
	    
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) {
	        nums[i] =  Integer.parseInt(st.nextToken());
	    }
	    
	    ops = new int[4];
	    st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < 4; ++i) {
	        ops[i] =  Integer.parseInt(st.nextToken());
	    }
	    
	    count = new int[4];
	    dfs(1, nums[0]);
	   
	    System.out.println(max);
	    System.out.println(min);
	}
	
	public static void dfs(int idx, int result) {
	    if(idx == n) {
	        min = Math.min(min, result);
	        max = Math.max(max, result);
	        return;
	    }
	    
	    // + - * /
	    if(ops[0] > count[0]) {
	        count[0]++;
	        dfs(idx + 1, result + nums[idx]);
	        count[0]--;
	    }
	    
	    if(ops[1] > count[1]) {
	        count[1]++;
	        dfs(idx + 1, result - nums[idx]);
	        count[1]--;
	    }
	    
	    if(ops[2] > count[2]) {
	        count[2]++;
	        dfs(idx + 1, result * nums[idx]);
	        count[2]--;
	    }
	    
	    if(ops[3] > count[3]) {
	        count[3]++;
	        dfs(idx + 1, result / nums[idx]);
	        count[3]--;
	    }
	}
}
