import java.io.*;
import java.util.*;

// 16:20 시작!
public class Main
{
    // 플로이드 와샬(모든 정점에서 모든 정점까지 최단거리)? O(v^3) ~= 시간 초과
    static int v;
    static List<Node>[] tree;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    v = Integer.parseInt(bf.readLine());
	    tree = new List[v+1];
	    
	    for(int i = 0; i <= v; ++i) {
	        tree[i] = new ArrayList<>();
	    }
	    
	    StringTokenizer st;
	    for(int i = 0; i < v; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        while(true) {
	            int to = Integer.parseInt(st.nextToken());
	            if(to == -1) break;
	            
	            int dist = Integer.parseInt(st.nextToken());
	            tree[from].add(new Node(to, dist));
	        }
	    }
	    
	    Node furFrom1 = getFurthest(1);
	    Node root = getFurthest(furFrom1.to);
	    System.out.println(getFurthest(root.to).dist);
	}
	
	public static class Node {
	    int to, dist;
	    
	    public Node(int to, int dist) {
	        this.to = to;
	        this.dist = dist;
	    }
	}
	
	public static Node getFurthest(int start) {
	    boolean[] check = new boolean[v+1];
	    int[] dist = new int[v+1];
	    Arrays.fill(dist, Integer.MAX_VALUE);
	    int maxDist = 0, maxNode = start;
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
	    pq.offer(new Node(start, 0));
	    while(!pq.isEmpty()) {
	        Node node = pq.poll();
	        
	        if(check[node.to]) continue;
	        check[node.to] = true;
	        dist[node.to] = node.dist;
	        if(maxDist < node.dist) {
	            maxDist = node.dist;
	            maxNode = node.to;
	        }
	        
	        for(Node next : tree[node.to]) {
	            if(dist[next.to] > node.dist + next.dist) {
	                dist[node.to] = node.dist + next.dist;
	                pq.offer(new Node(next.to, node.dist + next.dist));
	            }
	        }
	    }
	    
	    return new Node(maxNode, maxDist);
	}
}