import java.io.*;
import java.util.*;

public class Main
{
    static class Node {
        int com, sec;

        Node(int com, int sec) {
            this.com = com;
            this.sec = sec;
        }
    }

	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        // c부터 모든 컴퓨터까지의 최단 거리들 중의 최댓값
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<List<Node>> list = new ArrayList<>();
            for(int i = 0; i <= n; ++i) list.add(new ArrayList<>());

            for(int i = 0; i < d; ++i) {
                st = new StringTokenizer(bf.readLine());
                int to = Integer.parseInt(st.nextToken()); // 의존하는 컴퓨터
                int from = Integer.parseInt(st.nextToken()); // 의존당하는 컴퓨터
                int sec = Integer.parseInt(st.nextToken());

                list.get(from).add(new Node(to, sec));
            }

            int[] dist = new int[n+1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            dijkstra(c, list, dist);

            int max = 0, count = 1;
            for(int i = 1; i <= n; ++i) {
                if(i == c || dist[i] == Integer.MAX_VALUE) continue;
                max = Math.max(max, dist[i]);
                count++;
            }

            System.out.println(count + " " + max);

        }
    }

    public static void dijkstra(int start, List<List<Node>> list, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.sec - b.sec);
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[node.com] < node.sec) continue;
            dist[node.com] = node.sec;

            for(Node next : list.get(node.com)) {
                if(dist[next.com] > node.sec + next.sec) {
                    dist[next.com] = node.sec + next.sec;
                    pq.offer(new Node(next.com, dist[next.com]));
                }
            }
        }

    }
}
