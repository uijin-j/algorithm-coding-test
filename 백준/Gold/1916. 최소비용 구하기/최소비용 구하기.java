import java.util.*;
import java.io.*;
 
public class Main {
    static int n, m;
    static ArrayList<ArrayList<Bus>> buses;
    static int[] dist;

    static class Bus implements Comparable<Bus> {
        int city;
        int cost;
    
        public Bus(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    
        @Override
        public int compareTo(Bus o) {
            return cost - o.cost;
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader sb = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(sb.readLine());
        m = Integer.parseInt(sb.readLine());
 
        buses = new ArrayList<>();
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
 
        for (int i = 0; i <= n; i++) {
            buses.add(new ArrayList<>());
        }
 
        StringTokenizer st;
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(sb.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
 
            buses.get(from).add(new Bus(to, cost));
        }

        st = new StringTokenizer(sb.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
 
        System.out.println(dijkstra(start, end));
    }
 
    public static int dijkstra(int start, int end) {
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        boolean[] check = new boolean[n + 1];
        pq.offer(new Bus(start, 0));
        dist[start] = 0;
 
        while (!pq.isEmpty()) {
            Bus now = pq.poll();
 
            if (!check[now.city]) {
                check[now.city] = true;
 
                for (Bus next : buses.get(now.city)) {
                    if (!check[next.city] && dist[next.city] > dist[now.city] + next.cost) {
                        dist[next.city] = dist[now.city] + next.cost;
                        pq.add(new Bus(next.city, dist[next.city]));
                    }
                }
            }
        }
        
        return dist[end];
    }
}