import java.util.*;

public class Main {
  static int[] parents;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // n: 도시의 수(max: 200)
    // m: 여행 계획 도시(max: 1000)
    int n = sc.nextInt();
    int m = sc.nextInt();
    parents = new int[n+1]; // 도시들의 집합을 표시
    for(int i = 1; i <= n; ++i) parents[i] = i;
    for(int i = 1; i <= n; ++i) {
      for(int j = 1; j <= n; ++j) {
        if(sc.nextInt() == 1) {
          union(i, j);
        }
      }
    }

    int[] plans = new int[m]; // 여행 계획
    for(int i = 0; i < m; ++i) {
      plans[i] = sc.nextInt();
    }

    // 여행 계획에 있는 도시들이 같은 집합에 있는지 확인하면 OK
    int root = find(plans[0]);
    for(int i = 1; i < m; ++i) {
      if(find(plans[i]) != root) {
        System.out.println("NO");
        return;
      }
    }

    System.out.println("YES");
  }

  static void union(int to, int from) {
    int toParent = find(to);
    int fromParent = find(from);

    // if(toParent == fromParent) return;
    parents[fromParent] = toParent;
  }

  static int find(int node) {
    if(parents[node] == node) return node;
    return find(parents[node]);
  }
}
