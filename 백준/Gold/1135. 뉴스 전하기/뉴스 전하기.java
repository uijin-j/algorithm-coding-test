import java.io.*;
import java.util.*;

// 16:36 시작
public class Main
{
    	
	/**
	 *         0
	 *       1              2
	 *  3    4     5      6     7
	 * 8 9 10 11 12 13   14   15  16
	* 17         18 19   20     21 22 23
	 */ 
    static List<Integer>[] tree;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    tree = new List[n];
	    for(int i = 0; i < n; ++i) {
	        tree[i] = new ArrayList<>();
	    }
	    
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) {
	        int parent = Integer.parseInt(st.nextToken());
	        if(parent == -1) continue;
	        tree[parent].add(i);
	    }
	    
	    System.out.println(calcuate(0));
	}
	
	// node 를 루트로 하는 서브트리의 직원이 모두 뉴스를 듣기 위한 최소 시간
	public static int calcuate(int node) {
	    PriorityQueue<Integer> times = new PriorityQueue<>((a, b) -> b - a);
	    for(int child : tree[node]) {
	        times.offer(calcuate(child));
	    }
	    
	    int total = 0;
	    int order = 0;
	    while(!times.isEmpty()) {
	        int time = times.poll();
	        total = Math.max(total, time + order + 1);
	        order++;
	    }
	    
	    return total;
	}
}