import java.io.*;
import java.util.*;

// 21:50 시작!
public class Main {
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    int[] nge = new int[n];
	    
	    Arrays.fill(nge, -1);
	    
	    Deque<Node> stack = new ArrayDeque<>();
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) {
	        int num = Integer.parseInt(st.nextToken());
	        while(!stack.isEmpty() && stack.peek().num < num) {
	            Node node = stack.pop();
	            nge[node.idx] = num;
	        }
	        
	        stack.push(new Node(i, num));
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < n; ++i) {
	        sb.append(nge[i]).append(" ");
	    }
	    
	    System.out.println(sb);
	}
	
	public static class Node {
	    int idx, num;
	    
	    public Node(int idx, int num) {
	        this.idx = idx;
	        this.num = num;
	    }
	}
}