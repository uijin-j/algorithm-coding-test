import java.io.*;
import java.util.*;

// 19:59 시작!
public class Main
{
    /**
     * A에서 B로 가는 경로 중 다리의 최소 중량이 최대가 되는 경우?
     */
    static List<Node>[] bridges;
    static int n, m, factory1, factory2;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
        bridges = new ArrayList[n + 1];
	    for(int i = 1; i <= n; ++i) {
	        bridges[i] = new ArrayList<>();
	    }
	    
	    // 1. 입력 받기
	    for(int i = 0; i < m; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int p1 = Integer.parseInt(st.nextToken());
	        int p2 = Integer.parseInt(st.nextToken());
	        int limit = Integer.parseInt(st.nextToken());
	        
	        bridges[p1].add(new Node(p2, limit));
	        bridges[p2].add(new Node(p1, limit));
	    }
	    
	    st = new StringTokenizer(bf.readLine());
	    factory1 = Integer.parseInt(st.nextToken());
	    factory2 = Integer.parseInt(st.nextToken());
	    
	    // sol1. DFS로 모든 경로를 탐색? O(NM) ~= 1_000_000_000 애매하다..
	    // sol2. 다익스트라?
	    int[] maxLimit = new int[n + 1]; // maxLimit[i]: factory1에서 i까지 경로 중 가장 중량 제한이 큰 경우의 중량 제한
	    boolean[] check = new boolean[n + 1]; // check[i]: maxLimit[i]가 확정되었는지 확인
	    
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.limit - a.limit);
	    pq.offer(new Node(factory1, Integer.MAX_VALUE));
	    
	    while(!pq.isEmpty()) {
	        Node node = pq.poll();
	        
	        if(check[node.to]) continue;
	        maxLimit[node.to] = node.limit;
	        check[node.to] = true;

	        for(Node next : bridges[node.to]) {	            
	            if(maxLimit[next.to] < Math.min(node.limit, next.limit)) {
	                maxLimit[next.to] = Math.min(node.limit, next.limit);
	                pq.offer(new Node(next.to, Math.min(node.limit, next.limit)));
	            }
	        }
	    }
	    
	    System.out.println(maxLimit[factory2]);
	}
	
	public static class Node {
	    int to, limit;
	    
	    public Node(int to, int limit) {
	        this.to = to;
	        this.limit = limit;
	    }
	}
}