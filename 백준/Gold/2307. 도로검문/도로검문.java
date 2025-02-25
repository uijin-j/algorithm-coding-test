import java.io.*;
import java.util.*;

// 14:27 시작!
public class Main
{
    /**
     * Q. 지연시킬 수 있는 최대 지연 시간
     * 1. 검문이 없을 때, 최단 시간을 구하기 위해 다익스트라를 이용! O(5_000log1_000)
     * 2. 모든 도로를 하나씩 제거해 보면서, 지연 시간을 측정 O(5_000*5_000log1_000) OK
     */
    static int n, m;
    static int INF = 10_000 * 5_000;
    static List<List<Node>> graph;
    static String[] bestPath;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    
	    graph = new ArrayList<>();
	    for(int i = 0; i <= n; ++i) {
	        graph.add(new ArrayList<>());
	    }
	    
	    for(int i = 0; i < m; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        int to = Integer.parseInt(st.nextToken());
	        int cost = Integer.parseInt(st.nextToken());
	        
	        graph.get(from).add(new Node(to, cost));
	        graph.get(to).add(new Node(from, cost));
	    }
	    
	    int original = findBest();
	    int[] paths = new int[bestPath.length];
	    for(int i = 0; i < paths.length; ++i) paths[i] = Integer.parseInt(bestPath[i]);
	    
	    int from = 1;
	    int max = original;
	    for(int i = 1; i < paths.length; ++i) {
	        int to = paths[i];
	        max = Math.max(max, findBest(from, to));
	        
	        if(max == INF) {
	            System.out.println(-1);
	            return;
	        }
	        
	        from = to;
	    }
	    
	    System.out.println(max - original);
	}
	
	public static class Node {
	    int to, cost;
	    String path;
	    
	    public Node(int to, int cost) {
	        this.to = to;
	        this.cost = cost;
	    }
	    
	    public Node(int to, int cost, String path) {
	        this.to = to;
	        this.cost = cost;
	        this.path = path;
	    }
	}
	
	public static int findBest() {
	    int[] min = new int[n+1];
	    Arrays.fill(min, INF);
	    
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
	    pq.offer(new Node(1, 0, "1 "));
	    while(!pq.isEmpty()) {
	        Node cur = pq.poll();
	        
	        if(min[cur.to] < cur.cost) continue;
	        min[cur.to] = cur.cost;
	        
	        if(cur.to == n) {
	            bestPath = cur.path.substring(0, cur.path.length() - 1).split(" ");
	            return cur.cost;
	        }
	        
	        for(Node next : graph.get(cur.to)) {
	            if(min[next.to] > cur.cost + next.cost) {
	                min[next.to] = cur.cost + next.cost;
	                pq.offer(new Node(next.to, cur.cost + next.cost, cur.path + next.to + " "));
	            }
	        }
	    }
	    
	    return INF;
	}
	
	public static int findBest(int blockedFrom, int blockedTo) {
	    int[] min = new int[n+1];
	    Arrays.fill(min, INF);
	    
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
	    pq.offer(new Node(1, 0));
	    while(!pq.isEmpty()) {
	        Node cur = pq.poll();
	        
	        if(min[cur.to] < cur.cost) continue;
	        min[cur.to] = cur.cost;
	        
	        if(cur.to == n) return cur.cost;
	        
	        for(Node next : graph.get(cur.to)) {
	            if(cur.to == blockedFrom && next.to == blockedTo) continue;
	            if(cur.to == blockedTo && next.to == blockedFrom) continue;
	            if(min[next.to] > cur.cost + next.cost) {
	                min[next.to] = cur.cost + next.cost;
	                pq.offer(new Node(next.to, cur.cost + next.cost));
	            }
	        }
	    }
	    
	    return INF;
	}
}