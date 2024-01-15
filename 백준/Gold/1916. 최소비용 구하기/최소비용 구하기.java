import java.io.*;
import java.util.*;

public class Main
{
    static class Node {
        int city, cost;

        Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }

	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        List<Node>[] buses = new ArrayList[n+1];

        for(int i = 1; i <= n; ++i) buses[i] = new ArrayList<>();

        for(int i = 0; i < m; ++i) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            buses[from].add(new Node(to, cost));
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int[] dist = new int[n+1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.city;
            int cost = node.cost;

            if(dist[now] < cost) continue;
            if(now == end) {
                System.out.println(dist[end]);
                return;
            }

            dist[now] = cost;

            for(Node next : buses[now]) {
                if(dist[next.city] > dist[now] + next.cost) {
                    dist[next.city] = dist[now] + next.cost;
                    pq.offer(new Node(next.city, dist[next.city]));
                }
            }
        }
	}
}
