import java.io.*;
import java.util.*;

// 22:24 시작
public class Main
{
    /**
     * 최단거리 문제 => 다익스트라? O(nlogm) 
     * but 경로도 알아야 함..!
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    int m = Integer.parseInt(bf.readLine());
	    List<List<Node>> graph = new ArrayList<>();
	    for(int i = 0; i <= n; ++i) {
	        graph.add(new ArrayList<>());
	    }
	    
	    StringTokenizer st;
	    for(int i = 0; i < m; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        int to = Integer.parseInt(st.nextToken());
	        int cost = Integer.parseInt(st.nextToken());
	        graph.get(from).add(new Node(to, cost));
	    }
	    
	    st = new StringTokenizer(bf.readLine());
	    int start = Integer.parseInt(st.nextToken());
	    int end = Integer.parseInt(st.nextToken());
	    
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
	    int[] path = new int[1];
	    path[0] = start;
	    pq.offer(new Node(start, 0, 1, path));
	    int[] dist = new int[n+1];
	    Arrays.fill(dist, 100_000_001);
	    while(!pq.isEmpty()) {
	        Node cur = pq.poll();
	        
	        if(dist[cur.to] < cur.cost) continue;
	        dist[cur.to] = cur.cost;

	        if(cur.to == end) {
	            System.out.println(cur.cost);
	            System.out.println(cur.cnt);
	            for(int i = 0; i < cur.cnt; ++i) System.out.print(cur.path[i] + " ");
	            return;
	        }
	        
	        for(Node next : graph.get(cur.to)) {
	            if(dist[next.to] > next.cost + cur.cost) {
	                dist[next.to] = next.cost + cur.cost;
	                int[] nextPath = new int[cur.cnt + 1];
	                for(int i = 0; i < cur.cnt; ++i) nextPath[i] = cur.path[i];
	                nextPath[cur.cnt] = next.to;
	                pq.offer(new Node(next.to, next.cost + cur.cost, cur.cnt + 1, nextPath));
	            }
	        }
	    }
	}
	
	public static class Node {
	    int to, cost, cnt;
	    int[] path;
	    
	    public Node(int to, int cost) {
	        this.to = to;
	        this.cost = cost;
	    }
	    
	    public Node(int to, int cost, int cnt, int[] path) {
	        this.to = to;
	        this.cost = cost;
	        this.cnt = cnt;
	        this.path = path;
	    }
	}
}