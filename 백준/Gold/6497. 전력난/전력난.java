import java.io.*;
import java.util.*;

/**
 * 14:17 start!
 */
public class Main
{
    /**
     * 최소 스패닝 트리?
     */
    static int[] parent;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    
	    while(true) {
	        st = new StringTokenizer(bf.readLine());
	        
    	    int m = Integer.parseInt(st.nextToken());
    	    int n = Integer.parseInt(st.nextToken());
    	    
    	    if(m == 0 && n == 0) break;
    	    
    	    PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
    	    int total = 0;
    	    for(int i = 0; i < n; ++i) {
    	        st = new StringTokenizer(bf.readLine());
    	        int h1 = Integer.parseInt(st.nextToken());
    	        int h2 = Integer.parseInt(st.nextToken());
    	        int dist = Integer.parseInt(st.nextToken());
    	        
    	        total += dist;
    	        
    	        pq.offer(new Edge(h1, h2, dist));
    	    }
    	    
    	    parent = new int[m];
    	    for(int i = 0; i < m; ++i) parent[i] = i;
    	    
    	    int cost = 0;
    	    while(!pq.isEmpty()) {
    	        Edge edge = pq.poll();
    	        
    	        if(find(edge.h1) == find(edge.h2)) continue;
    	        
    	        if(edge.h1 >= edge.h2) union(edge.h1, edge.h2);
    	        else union(edge.h2, edge.h1);
    	        
    	        cost += edge.dist;
    	    }
    	    
    	    sb.append(total - cost).append("\n");
	    }
	    
	    System.out.println(sb.toString());
	}
	
	public static class Edge {
	    int h1;
	    int h2;
	    int dist;
	    
	    public Edge(int h1, int h2, int dist) {
	        this.h1 = h1;
	        this.h2 = h2;
	        this.dist = dist;
	    }
	}
	
	public static void union(int h1, int h2) {
	    int p1 = find(h1);
	    int p2 = find(h2);
	    
	    if(p1 != p2) {
	        parent[p2] = p1;
	    }
	}
	
	public static int find(int h) {
	   if(parent[h] == h) return h;
	   return parent[h] = find(parent[h]);
	}
}