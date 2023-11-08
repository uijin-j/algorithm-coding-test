import java.util.*;

public class Main {
  static int v, e;
  static List<Node> nodeList = new ArrayList<>();
  static int[] parents;

  static class Node {
    int from;
    int to;
    int weight;

    public Node(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    v = sc.nextInt();
    e = sc.nextInt();
    parents = new int[v + 1];
    for(int i = 1; i <= v; ++i) {
      parents[i] = i; // i번째 노드는 i집합에 속함
    }

    for(int i = 0; i < e; ++i) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int weight = sc.nextInt();

      nodeList.add(new Node(from, to, weight));
    }

    nodeList.sort((a, b) -> a.weight - b.weight);

    int answer = 0;
    for(Node node: nodeList) {
      if(makeUnion(node)) {
        answer += node.weight;
      }
    }

    System.out.println(answer);
  }

  public static boolean makeUnion(Node node) {
    int fromRoot = findRoot(node.from);
    int toRoot = findRoot(node.to);

    if(fromRoot != toRoot) {
      parents[toRoot] = fromRoot;
      return true;
    }

    return false;
  }

  public static int findRoot(int v) {
    if(parents[v] == v) return v;
    return findRoot(parents[v]);
  }
}