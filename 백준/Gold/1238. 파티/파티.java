import java.util.*;

public class Main {
  static int[] go, back;
  static int n, m, x;

  static class Node {
    int to, time;

    public Node(int to, int time) {
      this.to = to;
      this.time = time;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt(); // 마을의 수(=학생의 수)
    m = sc.nextInt(); // 단방향 도로의 수
    x = sc.nextInt(); // 파티가 열리는 마을

    go = new int[n+1];
    back = new int[n+1];

    List<List<Node>> goList = new ArrayList<>();
    List<List<Node>> backList = new ArrayList<>();

    for(int i = 0; i <= n; ++i) {
      goList.add(new ArrayList<>());
      backList.add(new ArrayList<>());

      go[i] = Integer.MAX_VALUE;
      back[i] = Integer.MAX_VALUE;
    }

    for(int i = 0; i < m; ++i) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int time = sc.nextInt();

      goList.get(from).add(new Node(to, time));
      backList.get(to).add(new Node(from, time));
    }

    dijkstra(go, goList);
    dijkstra(back, backList);

    int max = 0;
    for(int i = 1; i <= n; ++i) {
      max = Math.max(max, go[i] + back[i]);
    }

    System.out.println(max);
  }

  static void dijkstra(int[] min, List<List<Node>> list) { // x부터 모든 마을까지 최단거리
    PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.time - b.time);

    min[x] = 0;
    q.offer(new Node(x, 0));

    while(!q.isEmpty()) {
      Node now = q.poll();

      if(now.time > min[now.to]) continue;

      for(Node next: list.get(now.to)) {
        if(now.time + next.time < min[next.to]) {
          min[next.to] = now.time + next.time;
          q.offer(new Node(next.to, min[next.to]));
        }
      }
    }
  }
}
