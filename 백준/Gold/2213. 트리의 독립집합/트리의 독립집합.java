import java.io.*;
import java.util.*;

// 트리의 독립 집합 (15:37 ~ )
public class Main
{
    /**
     * 트리의 최대 독립 집합을 구하기 
     * 
     * 1(10) - 2(30) - 3(40) - 4(10) -5(20)
     *         |
     *         6(20) - 7(70)
     * 
     * 1. 먼저 독립 집합을 구하자.
     *  1) 아무 노드나 선택 (1)
     *  2) 1번 노드를 선택 or 1번 노드를 선택 X
     *   2-1) 1번 노드를 선택, 1번과 인접한 노드는 선택 X
     *   2-2) 1번 노드를 선택 X, 1번과 인접한 노드는 상관 X
     *  3) 1번 노드와 인접한 노드를 탐색 (1번 노드 선택 여부 또는 해당 노드는 상태를 넘겨주기)
     *   3-1) 자신이 선택 X이면, 자신과 인접한 노드는 상관 X
     *   3-2) 자신이 선택 O이면, 자신과 인접한 노드는 선택 X
     *  4) 2-3을 반복
     * 
     * 2. 독립 집합의 크기를 계산하고, 최댓값을 취하자!
     * 
     * 즉, 재귀
     */
    static int n;
    static int[] value;
    static List<Integer>[] tree;
    static int[][] dp;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    value = new int[n+1];
	    
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 1; i <= n; ++i) {
	        value[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    tree = new List[n+1];
	    for(int i = 0; i <= n; ++i) tree[i] = new ArrayList<>();
	    
	    for(int i = 0; i < n-1; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int u = Integer.parseInt(st.nextToken());
	        int v = Integer.parseInt(st.nextToken());
	        
	        tree[u].add(v);
	        tree[v].add(u);
	    }
	    
	    dp = new int[n+1][2];

	    int result1 = findMax(1, 0, true);
	    int result2 = findMax(1, 0, false);
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append(Math.max(result1, result2)).append("\n");
	    
	    List<Integer> list = new ArrayList<>();

	    if(result1 > result2) trace(1, 0, true, list);
	    else trace(1, 0, false, list);
	    
	    list.sort((a, b) -> a - b);
        for(int node : list) {
            sb.append(node).append(" ");
        }
        
        System.out.println(sb.toString());
	}
	
	// node를 포함한 하위 트리의 최대 독립 집합
	public static int findMax(int node, int parent, boolean selected) {
	    int sum = 0;
	    if(selected) {
	        if(dp[node][1] > 0) return dp[node][1];
	        for(int child : tree[node]) {
	            if(child == parent) continue;
	            sum += findMax(child, node, false);
	        }
	        
	        return dp[node][1] = sum + value[node];
	    }
	    
	    // node를 선택 X
	    if(dp[node][0] > 0) return dp[node][0];
	    for(int child : tree[node]) {
            if(child == parent) continue;
            sum += Math.max(findMax(child, node, true), findMax(child, node, false));
        }
        
        return dp[node][0] = sum;
	}
	
	public static void trace(int node, int parent, boolean selected, List<Integer> list) {
	    if(selected) {
	        list.add(node);
	        
	        for(int child : tree[node]) {
	            if(child == parent) continue;
	            trace(child, node, false, list);
	        }
	        
	        return;
	    }

	    for(int child : tree[node]) {
            if(child == parent) continue;
            if(dp[child][0] > dp[child][1]) {
                trace(child, node, false, list);
            } else {
                trace(child, node, true, list);
            }
	    }
	}
}