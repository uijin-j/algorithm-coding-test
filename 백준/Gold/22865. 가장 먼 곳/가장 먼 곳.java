import java.io.*;
import java.util.*;

public class Main
{
    static class Node { 
        int to, dist;

        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

	public static void main(String[] args) throws IOException {
        // 플로이드-와샬: n^3 = 10_000_000_000_000_000 X
        // 다익스트라 3번: nlogn = 3 * 100_000 * log100_000
        
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] distA = new int[n+1];
        int[] distB = new int[n+1];
        int[] distC = new int[n+1];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(bf.readLine());
        List<List<Node>> roads = new ArrayList<>();
        for(int i = 0; i <= n; ++i) roads.add(new ArrayList<>());
        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads.get(from).add(new Node(to, dist));
            roads.get(to).add(new Node(from, dist));
        }

        dijkstra(a, distA, roads);
        dijkstra(b, distB, roads);
        dijkstra(c, distC, roads);

        int[] dist = new int[n+1];
        int max = 0;
        int answer = -1;
        for(int i = 1; i <= n; ++i) {
            dist[i] = Math.min(Math.min(distA[i], distB[i]), distC[i]);
            if(dist[i] > max) {
                max = dist[i];
                answer = i;
            }
        }

        System.out.println(answer);
	}

    public static void dijkstra(int start, int[] dist, List<List<Node>> roads) {
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.to;

            if(dist[now] < node.dist) continue;
            dist[now] = node.dist;

            for(Node next: roads.get(now)) {
                if(dist[next.to] > dist[now] + next.dist) {
                    dist[next.to] = dist[now] + next.dist;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
}
