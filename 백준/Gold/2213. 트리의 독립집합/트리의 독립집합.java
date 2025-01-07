import java.io.*;
import java.util.*;

// 16:23 시작!
public class Main
{
    static int[] w;
    static int[][] dp;
    static List<Integer>[] tree;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    w = new int[n + 1];
	    
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 1; i <= n; ++i) {
	        w[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    tree = new List[n + 1];
	    for(int i = 0; i <= n; ++i) {
	        tree[i] = new ArrayList<>();
	    }
	    
	    for(int i = 0; i < n - 1; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        int to = Integer.parseInt(st.nextToken());
	        
	        tree[from].add(to);
	        tree[to].add(from);
	    }
	    
	    dp = new int[n + 1][2];
	    find(1, 0);

        List<Integer> nodes = new ArrayList<>();
        if(dp[1][0] >= dp[1][1]) {
            System.out.println(dp[1][0]);
            trace(1, 0, 0, nodes);
        } else {
            System.out.println(dp[1][1]);
            trace(1, 0, 1, nodes);
        }
        
        nodes.sort((a, b) -> a - b);
        for(int node : nodes) {
            System.out.print(node + " ");
        }
	}
	
	public static void find(int node, int parent) {
	    for(int child : tree[node]) {
	        if(child == parent) continue;
	        
	        find(child, node);

            dp[node][0] += Math.max(dp[child][0], dp[child][1]);
            dp[node][1] += dp[child][0];
	    }
	    
	    dp[node][1] += w[node];
	}
	
	public static void trace(int node, int parent, int state, List<Integer> path) {
	    if(state == 1) {
			path.add(node);
			
			for(int child : tree[node]) {
			    if(child == parent) continue;
				trace(child, node, 0, path);
			}
			
			return;
		}
		
		for(int child : tree[node]) {
		    if(child == parent) continue;
			if(dp[child][1] > dp[child][0]) {
				trace(child, node, 1, path);
			} else {
				trace(child, node, 0, path);
			}
		}
	}
}