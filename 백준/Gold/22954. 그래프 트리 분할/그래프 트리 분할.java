import java.io.*;
import java.util.*;

public class Main {
    /**
     * 그래프에서 원하는 만큼 간선을 삭제해, 서로 다른 크기의 트리 2개로 분할
     * 조건 1) 각 트리는 1개 이상의 정점을 가지고 있음
     * 조건 2) 두 트리가 동일한 정점 또는 간선을 공유 X
     * 참고) 트리의 크기 = 노드의 수
     * 즉, 그래프를 자르면 무조건 그래프 수가 증가! 
     * 따라서 그래프가 이미 3개면 Out !
     *        그래프가 2개면 자르지 않고, 같은 크기가 아니면 OK
     *        그래프가 1개면 마지막 노드 1개만 떼서 그래프로 만들기!
     */
    static int n, m; // 정점, 간선
    static List<Edge>[] graph;
    static boolean[] check; // 정점 방문 확인
    static List<Integer> edges, nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        // 정점이 2개 이하
        if (n <= 2) {
            System.out.println(-1);
            return;
        }
        
        graph = new List[n + 1];
        for(int i = 0; i <= n; ++i) {
	        graph[i] = new ArrayList<>();
	    }

        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            graph[u].add(new Edge(v, i));
	        graph[v].add(new Edge(u, i));
        }

        int count = 0; // 그래프 갯수
        check = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (check[i]) continue;

            // Case 3. 그래프가 3개
            if (count == 2) {
                System.out.println(-1);
                return;
            }
            
            edges = new ArrayList<>();
            nodes = new ArrayList<>();
            nodes.add(i);
            check[i] = true;
            dfs(i);
            
            count++;

            // Case 1. 그래프가 1개
            if (edges.size() == n - 1) {
                sb.append(n - 1).append(" ").append(1);
                sb.append("\n");
        
                for (int j = 0; j < nodes.size() - 1; j++) {
                    sb.append(nodes.get(j)).append(" ");
                }
        
                sb.append("\n");
        
                for (int j = 0; j < edges.size() - 1; j++) {
                    sb.append(edges.get(j)).append(" ");
                }
        
                sb.append("\n");
                sb.append(nodes.get(nodes.size() - 1));
                sb.append("\n");
                
                System.out.println(sb.toString());
                return;
            }

            // Case 2. 그래프가 2개 일수도
            if (count == 1) {
                if (2 * nodes.size() == n) {
                    System.out.println(-1);
                    return;
                }
                
                sb.append(nodes.size())
                    .append(" ")
                    .append(n - nodes.size())
                    .append("\n");
            }

            // 정점 방문 순서 출력
            for (int node : nodes) {
                sb.append(node).append(" ");
            }
            
            sb.append("\n");

            // 간선 방문 순서 출력
            for (int edge : edges) {
                sb.append(edge).append(" ");
            }
            
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
    
    private static class Edge {
        int to, num;

        public Edge(int to, int num) {
            this.to = to;
            this.num = num;
        }
    }

    private static void dfs(int node) {
        for (Edge next : graph[node]) {
            if (check[next.to]) continue;
            check[next.to] = true;
            nodes.add(next.to);
            edges.add(next.num);
            dfs(next.to);
        }
    }
}