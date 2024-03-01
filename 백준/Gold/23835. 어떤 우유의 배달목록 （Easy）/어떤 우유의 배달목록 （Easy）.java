import java.io.*;
import java.util.*;

public class Main
{
    // 방문 횟수 count
    // 경로 구하기 -> dfs
    static int n;
    static List<List<Integer>> path;
    static int[] counts;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        counts = new int[n+1];
        path = new ArrayList<>();
        for(int i = 0; i <= n; ++i) path.add(new ArrayList<>());
        
        StringTokenizer st;
        for(int i = 0; i < n - 1; ++i) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            path.get(from).add(to);
            path.get(to).add(from);
        }

        int q = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; ++i) {
            st = new StringTokenizer(bf.readLine());
            int t = Integer.parseInt(st.nextToken());
            if(t == 1) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                visit = new boolean[n+1];
                visit[u] = true;
                resolveQ1(u, v, new ArrayList<>());
                continue;
            }

            int x = Integer.parseInt(st.nextToken());
            sb.append(counts[x]).append("\n");
        }

        System.out.println(sb);
    }

    public static void resolveQ1(int u, int v, List<Integer> route) {
        if(u == v) {
            int milk = 1;
            for(int room : route) counts[room] += milk++;
        }

        for(int next : path.get(u)) {
            if(visit[next]) continue;
            visit[next] = true;
            route.add(next);
            resolveQ1(next, v, route);
            route.remove(route.size() - 1);
            visit[next] = false;
        }
    }
}
