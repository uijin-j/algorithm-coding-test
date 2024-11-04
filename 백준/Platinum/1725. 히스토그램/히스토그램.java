import java.io.*;
import java.util.*;

/**
 * 14:30 시작!  
 */
public class Main
{
    /**
     * 예전에 풀었던 문제랑 비슷! => 해당 문제는 그리디
     * n은 최대 100_000
     * 높이는 최대 1_000_000_000
     * 
     * 어렵다.. 투포인터를 생각해 봤는데..
     * 알고리즘 분류를 보니, 세그먼트 트리!
     */
    static int n;
    static int[] heights, tree;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    heights = new int[n + 1];
	    
	    // 1. 입력 받기
	    for(int i = 1; i <= n; ++i) {
	        heights[i] = Integer.parseInt(bf.readLine());
	    }
	    
	    //         1
	    //     1      3
	    //  1   4   3  3
	    // 2 1 4 5 3 3 
	    
	    //         1
	    //     1      4
	    //  1   2   4  6
	    // 0 1 2 3 4 5 
	    
	    // 2. 세그먼트 트리
	    tree = new int[n * 4];
	    initTree(1, n, 1);
	    
	    System.out.println(findMaxArea(1, n));
	}
	
	public static int initTree(int start, int end, int node) {
	    if(start == end) return tree[node] = start;
	    
	    int mid = (start + end) / 2;
	    int leftIdx = initTree(start, mid, node * 2);
	    int rightIdx = initTree(mid + 1, end, node * 2 + 1);
	    
	    return tree[node] = (heights[leftIdx] > heights[rightIdx]) ? rightIdx : leftIdx;
	}
	
	// start ~ end 범위 사이의 최대 넓이
	public static int findMaxArea(int start, int end) {
	    int idxOfMin = getIdxOfMin(1, n, 1, start, end);
	    int result = heights[idxOfMin] * (end - start + 1);
	    
	    if(start < idxOfMin) result = Math.max(result, findMaxArea(start, idxOfMin - 1));
	    if(idxOfMin < end) result = Math.max(result, findMaxArea(idxOfMin + 1, end));
	    
	    return result;
	}
	
	// left와 right 사이에 가장 작은 높이의 인덱스
	public static int getIdxOfMin(int start, int end, int node, int left, int right) {
	   if(left > end || right < start) return -1;
	   if(left <= start && right >= end) return tree[node];
	   
	   int mid = (start + end) / 2;
	   int leftIdx = getIdxOfMin(start, mid, node * 2, left, right);
	   int rightIdx = getIdxOfMin(mid + 1, end, node * 2 + 1, left, right);
	   
	   if(leftIdx == -1) return rightIdx;
	   if(rightIdx == -1) return leftIdx;
	   
	   return heights[leftIdx] > heights[rightIdx] ? rightIdx : leftIdx;
	}
}