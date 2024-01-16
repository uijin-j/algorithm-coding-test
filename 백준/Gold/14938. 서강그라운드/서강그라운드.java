/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.io.*;
import java.util.*;

public class Main
{
    static int n, m, r;
    static List<List<Node>> roads;

    static class Node {
        int node, dist;

        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        int[] items = new int[n+1];
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; ++i) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        roads = new ArrayList<>();
        for(int i = 0; i <= n; ++i) {
            roads.add(new ArrayList<>());
        }

        for(int i = 0; i < r; ++i) {
            st = new StringTokenizer(bf.readLine());
            int area1 = Integer.parseInt(st.nextToken());
            int area2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads.get(area1).add(new Node(area2, dist));
            roads.get(area2).add(new Node(area1, dist));
        }

        int[][] dists = new int[n+1][n+1];
        for(int i = 1; i <= n; ++i) {
            Arrays.fill(dists[i], Integer.MAX_VALUE);
        }
        
        // 다익스트라 * n번
        for(int i = 1; i <= n; ++i) {
            dijkstra(i, dists[i]);
        }

        int max = 0;
        for(int i = 1; i <= n; ++i) { // i 지역에 떨어졌을 때
            int sum = 0;
            for(int j = 1; j <= n; ++j) {
                if(dists[i][j] <= m) {
                    sum += items[j];
                }
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);
	}

    static void dijkstra(int start, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(dist[now.node] < now.dist) continue;
            dist[now.node] = now.dist;

            for(Node next : roads.get(now.node)) {
                if(dist[next.node] > dist[now.node] + next.dist) {
                    dist[next.node] = dist[now.node] + next.dist;
                    pq.offer(new Node(next.node, dist[next.node]));
                }
            }
        }
    }
}
