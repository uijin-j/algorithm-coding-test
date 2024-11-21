import java.io.*;
import java.util.*;

// 21:10 시작!
public class Main
{
    /**
     * 구간 내 최대/최소 구하기
     * 매 입력마다 선형적으로 구하면, 최악의 경우 O(NM) ~= 10_000_000_000
     * 세그먼트 트리?
     * 75 30 100 38 50 51 52 20 81 5
     */
     
    static int[] nums, maxTree, minTree;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    nums = new int[n + 1];
	    for(int i = 1; i <= n; ++i) {
	        nums[i] = Integer.parseInt(bf.readLine());
	    }
	    
	    maxTree = new int[n * 4];
	    minTree = new int[n * 4];
	    
	    initMax(1, n, 1);
	    initMin(1, n, 1);
	    
	    StringBuilder sb = new StringBuilder();
	    for(int i = 1; i <= m; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        int to = Integer.parseInt(st.nextToken());
	        
	        sb.append(findMin(1, n, 1, from, to))
	            .append(" ")
	            .append(findMax(1, n, 1, from, to))
	            .append("\n");
	    }
	    
	    System.out.println(sb);
	}
	
	static int initMax(int start, int end, int node) {
	    if(start == end) return maxTree[node] = nums[start];
		int mid = (start + end) / 2;
		return maxTree[node] = Math.max(initMax(start, mid, node * 2), initMax(mid + 1, end, node * 2 + 1));
	}
	
	static int initMin(int start, int end, int node) {
	    if(start == end) return minTree[node] = nums[start];
		int mid = (start + end) / 2;
		return minTree[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, node * 2 + 1));
	}
	
	static int findMax(int start, int end, int node, int left, int right) {
	    if(end < left || right < start) return Integer.MIN_VALUE;
		if(left <= start && end <= right) return maxTree[node];
		
		int mid = (start + end) / 2;
		return Math.max(findMax(start, mid, node * 2, left, right), findMax(mid + 1, end, node * 2 + 1, left, right));
	}
	
	static int findMin(int start, int end, int node, int left, int right) {
	    if(end < left || right < start) return Integer.MAX_VALUE;
		if(left <= start && end <= right) return minTree[node];
		
		int mid = (start + end) / 2;
		return Math.min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right));
	}
}
