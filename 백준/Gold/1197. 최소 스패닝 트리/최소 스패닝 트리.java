import java.io.*;
import java.util.*;

public class Main
{
    public static class Edge {
        int to, cost;
        
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        List<Edge>[] graph = new List[v+1];
        for(int i = 1; i <= v; ++i) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < e; ++i) {
            st = new StringTokenizer(bf.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            graph[v1].add(new Edge(v2, cost));
            graph[v2].add(new Edge(v1, cost));
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Edge(1, 0));
        boolean[] visited = new boolean[v+1];
        int answer = 0;
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            
            if(visited[now.to]) {
                continue;
            }
            
            visited[now.to] = true;
            answer += now.cost;
            
            for(Edge next : graph[now.to]) {
                if(visited[next.to]) continue;
                pq.offer(next);
            }
        }
    	  
    	  
        System.out.println(answer);
    }
}
