import java.io.*;
import java.util.*;

// 12:54 시작!
public class Main {
    /**
     * 
     */
    static List<Integer>[] tree;
    static int[] node;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int root = Integer.parseInt(st.nextToken());
	    
	    tree = new List[n+1];
	    for(int i = 0; i <= n; ++i) {
	        tree[i] = new ArrayList<>();
	    }
	    
	    node = new int[n+1];
	    Arrays.fill(node, -1);
	    
	    for(int i = 0; i < n - 1; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int u = Integer.parseInt(st.nextToken());
	        int v = Integer.parseInt(st.nextToken());
	        tree[u].add(v);
	        tree[v].add(u);
	    }
	    
	    int numOfleaf = Integer.parseInt(bf.readLine());
	    for(int i = 0; i < numOfleaf; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int k = Integer.parseInt(st.nextToken());
	        int t = Integer.parseInt(st.nextToken());
	        
	        node[k] = t;
	    }
	    
	    minmax(root, -1, 0);
	    
	    int q = Integer.parseInt(bf.readLine());
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < q; ++i) {
	        int num = Integer.parseInt(bf.readLine());
	        sb.append(node[num]).append("\n");
	    }
	    
	    System.out.println(sb);
	}
	
	public static int minmax(int cur, int parent, int level) {
	    if(node[cur] != -1) return node[cur];
	    
	    if(level % 2 == 0) { // max
	        int max = -1;
	        for(int child : tree[cur]) {
	            if(child == parent) continue;
	            max = Math.max(max, minmax(child, cur, level + 1));
	        }
	        
	        return node[cur] = max;
	    }
	    
	    // min
	    int min = 1_000_000_001;
	    for(int child : tree[cur]) {
	        if(child == parent) continue;
            min = Math.min(min, minmax(child, cur, level + 1));
        }
        
        return node[cur] = min;
	}
}