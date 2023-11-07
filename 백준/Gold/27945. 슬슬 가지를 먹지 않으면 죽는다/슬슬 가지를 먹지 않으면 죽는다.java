import java.util.*;

public class Main {
  static int n, m;
  static List<Node> nodeList = new ArrayList<>();
  static int[] parents;

  static class Node {
    int from;
    int to;
    int open;

    public Node(int from, int to, int open) {
      this.from = from;
      this.to = to;
      this.open = open;
    }
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();
    parents = new int[n+1];

    for(int i = 0; i < m; ++i) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int open = sc.nextInt();

      nodeList.add(new Node(from, to, open));
    }
    
    for(int i = 1; i <= n; ++i) {
        parents[i] = i;
    }

    nodeList.sort((a, b) -> a.open - b.open); // open일을 기준으로 오름차순 정렬

    int today = 1;
    for(Node node: nodeList) {
      if(node.open == today && canUnion(node)) {
        today++;

        if(today == n) break;
      } else break;
    }

    System.out.println(today); 
  }

  public static boolean canUnion(Node node) {
    int fromRoot = findSet(node.from);
		int toRoot = findSet(node.to);
		
		if(fromRoot == toRoot) return false;
		else parents[toRoot] = fromRoot;
		return true;
  }

  private static int findSet(int v) {
		if(parents[v]==v) return v;
		else return parents[v] = findSet(parents[v]);
	}
}