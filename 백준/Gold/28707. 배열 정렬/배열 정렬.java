import java.io.*;
import java.util.*;

// 21:40 시작!
public class Main {
    static int n, m;
    static int[] arr;
    static int[][] method;
    static int MAX = Integer.MAX_VALUE / 2;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    arr = new int[n];
	    int[] toSort = new int[n];
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) {
	        arr[i] = Integer.parseInt(st.nextToken());
	        toSort[i] = arr[i];
	    }
	    
	    m = Integer.parseInt(bf.readLine());
	    method = new int[m][3];
	    for(int i = 0; i < m; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        method[i][0] = Integer.parseInt(st.nextToken()) - 1;
	        method[i][1] = Integer.parseInt(st.nextToken()) - 1;
	        method[i][2] = Integer.parseInt(st.nextToken());
	    }
	    
	    Arrays.sort(toSort);
	    String target = toString(toSort);
	    
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
	    pq.offer(new Node(arr, 0));
	    Map<String, Integer> dist = new HashMap<>();
	    while(!pq.isEmpty()) {
	        Node cur = pq.poll();

	        if(cur.cost > dist.getOrDefault(toString(cur.arr), MAX)) continue;
	        if(toString(cur.arr).equals(target)) {
                System.out.println(cur.cost);
                return;
            }
	        
	        for(int i = 0; i < m; ++i) {
	            int idx1 = method[i][0];
    	        int idx2 = method[i][1];
    	        int cost = method[i][2];
    	        
    	        int[] copy = new int[n];
    	        for(int j = 0; j < n; ++j) copy[j] = cur.arr[j];
    	        int temp = copy[idx1];
    	        copy[idx1] = copy[idx2];
    	        copy[idx2] = temp;
    	        String str = toString(copy);
    	        if(dist.getOrDefault(str, MAX) > cur.cost + cost) {
    	            dist.put(str, cur.cost + cost);
    	            pq.offer(new Node(copy, cur.cost + cost));
    	        }
	        }
	    }
	    
	    System.out.println(-1);
	}
	
	public static class Node {
	    int[] arr;
	    int cost;
	    
	    public Node(int[] arr, int cost) {
	        this.arr = arr;
	        this.cost = cost;
	    }
	}
	
	public static boolean checkAsc(int[] arr) {
	    int before = arr[0];
	    for(int i = 1; i < arr.length; ++i) {
	        if(arr[i] < before) return false;
	        before = arr[i];
	    }
	    
	    return true;
	}
	
	public static String toString(int[] arr) {
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < arr.length; ++i) {
	        sb.append(arr[i]).append(" ");
	    }
	    
	    return sb.toString();
	}
}