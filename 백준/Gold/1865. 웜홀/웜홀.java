import java.io.*;
import java.util.*;

public class Main {
    static int t, n, m, w;
    static List<List<Edge>> edges;

    static class Edge {
        int to, time;
        
        public Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(bf.readLine());

        while(t > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            for(int i = 0; i <= n; ++i) {
                edges.add(new ArrayList<>());
            }
            
            for(int i = 0; i < m; ++i) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                edges.get(from).add(new Edge(to, time));
                edges.get(to).add(new Edge(from, time));
            }

            for(int i = 0; i < w; ++i) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken()) * -1;

                edges.get(from).add(new Edge(to, time));
            }

            if(hasMinusCycle()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

            --t;
        }

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

        for(int i = 1; i <= n; ++i) {
            boolean notUpdate = true;

            for(int j = 1; j <= n; ++j) {
                if(dist[j] == Integer.MAX_VALUE) continue;
                for(Edge edge: edges.get(j)) {
                    if(dist[edge.to] > dist[j] + edge.time) {
                        dist[edge.to] = dist[j] + edge.time;
                        notUpdate = false;

                        if (i == n) {
                            return true;
                        }
                    }
                }
            }

            if(notUpdate) return false;
        }

        return false;
    }
}
