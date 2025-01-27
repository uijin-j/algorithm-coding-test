import java.io.*;
import java.util.*;

// 20:33 시작!
public class Main {
    /**
     * 두 노드의 가장 가까운 공통 조상 구하기 (DFS를 하면 O(n)으로 풀  수 있을 것 같다!)
     */
    static int n, answer;
    static List<Integer>[] tree;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(bf.readLine());
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    while(t > 0) {
	        answer = -1;
	        n = Integer.parseInt(bf.readLine()); // n은 최대 10_000
	        tree = new ArrayList[n+1];
	        for(int i = 0; i <= n; ++i) tree[i] = new ArrayList<>();
	        
	        boolean[] check = new boolean[n+1];
	        for(int i = 0; i < n - 1; ++i) {
	            st = new StringTokenizer(bf.readLine());
	            int from = Integer.parseInt(st.nextToken());
	            int to = Integer.parseInt(st.nextToken());
	            check[to] = true;
	            tree[from].add(to);
	        }
	        
	        int root = -1;
	        for(int i = 1; i <= n; ++i) {
	            if(!check[i]) {
	                root = i;
	                break;
	            }
	        }

	        st = new StringTokenizer(bf.readLine());
	        int firstNode = Integer.parseInt(st.nextToken());
	        int secondNode = Integer.parseInt(st.nextToken());
	        dfs(root, firstNode, secondNode);
	        
	        sb.append(answer).append("\n");
	        t--;
	    }
	    
	    System.out.println(sb);
	}
	
	public static boolean[] dfs(int cur, int firstNode, int secondNode) {
	    boolean[] result = new boolean[2];
	    
	    if(cur == firstNode) result[0] = true;
	    if(cur == secondNode) result[1] = true;
	    
	    for(int child : tree[cur]) {
	        boolean[] childs = dfs(child, firstNode, secondNode);
	        if(childs[0]) result[0] = true;
	        if(childs[1]) result[1] = true;
	    }

	    if(result[0] && result[1] && answer == -1) {
	        answer = cur;
	    }
	    
	    return result;
	}
}