import java.util.*;

public class Main {
    static int n, m, k, x;
    static HashMap<Integer, List<Integer>> hash = new HashMap<>();
    static boolean[] check;
    static int[] dis;

    static void bfs(int s, int l) {
        Queue<Integer> q = new LinkedList<>();
        dis[s] = 0;
        q.offer(s);
        check[s] = true;

        while(!q.isEmpty()) {
            int now = q.poll();
            for(int i : hash.getOrDefault(now, new ArrayList<>())) {
                if(!check[i]) {
                    dis[i] = dis[now] + 1;
                    q.offer(i);
                    check[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        x = sc.nextInt();
        check = new boolean[n+1];
        dis = new int[n+1];

        for(int i = 0; i < m; ++i) {
          int a = sc.nextInt();
          int b = sc.nextInt();
          hash.put(a, hash.getOrDefault(a, new ArrayList<>()));
          hash.get(a).add(b);
        }

        bfs(x, 0);

        boolean flag = true;
        for(int i = 1; i <= n; ++i) {
            if(dis[i] == k) {
                flag = false;
                System.out.println(i);
            }
        }
        if(flag) System.out.println(-1);
    }
}