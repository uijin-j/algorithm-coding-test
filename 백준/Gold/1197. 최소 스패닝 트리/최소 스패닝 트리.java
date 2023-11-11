import java.util.*;

public class Main {
  static List<Edge>[] graph; // graph[i]는 i번째 노드와 연결된 간선들
  static boolean[] visited;
 
  static class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int v = sc.nextInt();
    int e = sc.nextInt();
    visited = new boolean[v+1];
    graph = new ArrayList[v + 1];
    for (int i = 0; i <= v; i++) graph[i] = new ArrayList<>();

    for(int i = 0; i < e; ++i) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int cost = sc.nextInt();

      graph[from].add(new Edge(to, cost));
      graph[to].add(new Edge(from, cost));
    }

    // prim
    PriorityQueue<Edge> q = new PriorityQueue<>((a, b) -> a.cost - b.cost);
    q.offer(new Edge(1, 0));

    int answer = 0;
    while (!q.isEmpty()) {
      Edge now = q.poll();

      if(visited[now.to]) continue;

      visited[now.to] = true;
      answer += now.cost;

      for(Edge edge : graph[now.to]) {
        if(!visited[edge.to]) {
          q.offer(edge);
        }
      }
    }

    System.out.println(answer);
  }
}
