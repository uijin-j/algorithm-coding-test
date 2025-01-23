import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static int n, m, t, s, g, h;
    static List<Node>[] graph;
    static int[] dist;
    static boolean[] check;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(bf.readLine());
	    
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st;
	    while(T > 0) {
	        st = new StringTokenizer(bf.readLine());
	        n = Integer.parseInt(st.nextToken()); // 교차로 갯수
	        m = Integer.parseInt(st.nextToken()); // 도로 갯수
	        t = Integer.parseInt(st.nextToken()); // 도착지 갯수
	        
	        st = new StringTokenizer(bf.readLine());
	        s = Integer.parseInt(st.nextToken()); // 출발지
	        g = Integer.parseInt(st.nextToken()); // g -> h 교차로는 무조건 지나감
	        h = Integer.parseInt(st.nextToken());
	        
	        graph = new List[n+1];
	        for(int i = 0; i <= n; ++i) {
	            graph[i] = new ArrayList<>();
	        }
	        
	        for(int i = 0; i < m; ++i) {
	            st = new StringTokenizer(bf.readLine());
	            int u = Integer.parseInt(st.nextToken());
	            int v = Integer.parseInt(st.nextToken());
	            int d = Integer.parseInt(st.nextToken());
	            
	            d *= 2;
	            
	            if((u == g && v == h) || (u == h && v == g)) {
	                d -= 1;
	            }
	            
	            graph[u].add(new Node(v, d));
	            graph[v].add(new Node(u, d));
	        }
	        
	        check = new boolean[n+1];
	        dist = new int[n+1];
	        Arrays.fill(dist, INF);

	        dijkstra(s);
	        
	        // s -> candi로 가는 최단 경로에 g -> h가 있으면 print
	        List<Integer> result = new ArrayList<>();
	        for(int i = 0; i < t; ++i) {
	            int candi = Integer.parseInt(bf.readLine());
	            if(dist[candi] != INF && dist[candi] % 2 == 1) {
	                 result.add(candi);
	            }
	        }
	        
	        result.sort((a, b) -> a - b);
	        
	        for(int i = 0; i < result.size(); ++i) {
	            sb.append(result.get(i)).append(" ");
	        }
	        
	        sb.append("\n");
	        --T;
	    }
	    
	    System.out.println(sb);
	}
	
	public static void dijkstra(int start) {
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
	    pq.offer(new Node(start, 0));
	    dist[start] = 0;
	    
	    while(!pq.isEmpty()) {
	        Node cur = pq.poll();
	        
	        if(check[cur.to]) continue;
	        check[cur.to] = true;
	        
	        for(Node next : graph[cur.to]) {
	            if(!check[next.to] && dist[next.to] > dist[cur.to] + next.dist) {
	                dist[next.to] = dist[cur.to] + next.dist;
	                pq.offer(new Node(next.to, dist[cur.to] + next.dist));
	            }
	        }
	    }
	}
	
	public static class Node {
	    int to, dist;
	    
	    public Node(int to, int dist) {
	        this.to = to;
	        this.dist = dist;
	    }
	}
}