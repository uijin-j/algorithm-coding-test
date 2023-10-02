import java.util.*;

public class Main {
    static int v, e, k;
    static HashMap<Integer, List<int[]>> hash = new HashMap<>();
    static boolean[] check;
    static int[] dis;

    static void bfs(int s) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dis[s] = 0;
        q.offer(new int[]{s, 0});

        while(!q.isEmpty()) {
            int[] now = q.poll();

            if(check[now[0]]) continue;
            for(int[] data : hash.getOrDefault(now[0], new ArrayList<>())) {
                int v = data[0];
                int w = data[1];

                dis[v] = Math.min(dis[v], now[1] + w);
                q.offer(new int[]{v, dis[v]});
            }

            check[now[0]] = true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        v = sc.nextInt();
        e = sc.nextInt();
        k = sc.nextInt();
        check = new boolean[v+1];
        dis = new int[v+1];
        Arrays.fill(dis, Integer.MAX_VALUE);

        for(int i = 0; i < e; ++i) {
          int from = sc.nextInt();
          int to = sc.nextInt();
          int w = sc.nextInt();
          hash.put(from, hash.getOrDefault(from, new ArrayList<>()));
          hash.get(from).add(new int[]{to, w});
        }

        bfs(k);

        for(int i = 1; i <= v; ++i) {
            if(dis[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dis[i]);
        }
    }
}