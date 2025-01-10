import java.io.*;
import java.util.*;

public class Main {
    static int n, m, w;
    static List<Edge> edges;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
    
        StringBuilder sb = new StringBuilder();
        while(t > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            for(int i = 0; i < m; ++i) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                edges.add(new Edge(from, to, time));
                edges.add(new Edge(to, from, time));
            }

            for(int i = 0; i < w; ++i) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken()) * -1;

                edges.add(new Edge(from, to, time));
            }

            if(hasMinusCycle()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");

            --t;
        }
        
        System.out.println(sb.toString());
    }

    private static boolean hasMinusCycle() {
        for(int i = 1; i <= n; ++i) { // i를 출발 지점으로 하는 사이클 탐색
            if(bellmanFord(i)) return true;
        }

        return false;
    }

    private static boolean bellmanFord(int start) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for(int i = 0; i < n - 1; ++i) {
            boolean notUpdate = true;
            for(int j = 0; j < edges.size(); ++j) {
                Edge edge = edges.get(j);
                
                if(dist[edge.from] == Integer.MAX_VALUE) continue;
                if(dist[edge.to] > dist[edge.from] + edge.dist) {
                    dist[edge.to] = dist[edge.from] + edge.dist;
                    notUpdate = false;
                }
            }

            if(notUpdate) return false;
        }
        
        for(int j = 0; j < edges.size(); ++j) {
            Edge edge = edges.get(j);
            
            if(dist[edge.from] == Integer.MAX_VALUE) continue;
            if(dist[edge.to] > dist[edge.from] + edge.dist) {
                return true;
            }
        }

        return false;
    }
    
    static class Edge {
        int from, to, dist;
        
        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }
}
