import java.io.*;
import java.util.*;

public class Main {
    static int[] minTree, maxTree, nums;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    
	    nums = new int[n + 1];
	    for(int i = 1; i <= n; ++i) {
	        nums[i] = Integer.parseInt(bf.readLine());
	    }
	    
	    minTree = new int[n * 4];
	    maxTree = new int[n * 4];
	    initMin(1, 1, n);
	    initMax(1, 1, n);
	    
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < m; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        int to = Integer.parseInt(st.nextToken());
	        
	        // start ~ end 사이에 있는 값 중 최대, 최솟값
	        sb.append(findMin(1, 1, n, from, to))
	            .append(" ")
	            .append(findMax(1, 1, n, from, to))
	            .append("\n");
	    }
	    
	    System.out.println(sb);
	}
	
	// start ~ end 사이에서 최솟값을 minTree[node]에 저장
	public static int initMin(int node, int start, int end) {
	    if(start == end) {
	        return minTree[node] = nums[start];
	    }
	    
	    int half = (start + end) / 2;
	    int left = initMin(node * 2, start, half);
	    int right = initMin(node * 2 + 1, half + 1, end);
	    
	    return minTree[node] = Math.min(left, right);
	}
	
	// start ~ end 사이에서 최댓값을 maxTree[node]에 저장
	public static int initMax(int node, int start, int end) {
	    if(start == end) {
	        return maxTree[node] = nums[start];
	    }
	    
	    int half = (start + end) / 2;
	    int left = initMax(node * 2, start, half);
	    int right = initMax(node * 2 + 1, half + 1, end);
	    
	    return maxTree[node] = Math.max(left, right);
	}
	
	// start ~ end 사이에서 최솟값을 구하기
	public static int findMin(int node, int start, int end, int from, int to) {
	    if(from <= start && end <= to) {
	        return minTree[node];
	    }
	    
	    if(start > to || end < from) {
	        return 1_000_000_001;
	    }

	    int half = (start + end) / 2;
	    int left = findMin(node * 2, start, half, from, to);
	    int right = findMin(node * 2 + 1, half + 1, end, from, to);
	    
	    return Math.min(left, right);
	}
	
	// start ~ end 사이에서 최댓값을 구하기
	public static int findMax(int node, int start, int end, int from, int to) {
	    if(from <= start && end <= to) {
	        return maxTree[node];
	    }
	    
	    if(start > to || end < from) {
	        return 0;
	    }
	    
	    int half = (start + end) / 2;
	    int left = findMax(node * 2, start, half, from, to);
	    int right = findMax(node * 2 + 1, half + 1, end, from, to);

	    return Math.max(left, right);
	}
}