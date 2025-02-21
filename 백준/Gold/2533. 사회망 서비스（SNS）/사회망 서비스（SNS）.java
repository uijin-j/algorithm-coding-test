import java.io.*;
import java.util.*;

// 13:56 시작!
public class Main
{
    /**
     * 루트는 자신이 얼리 어답터 or 자식들이 모두 얼리어답터
     * 브랜치는 부모가 얼리 어답터 일 때, 자식들이 모두 얼리 어답터 or 자신이 얼리 어답터
     *          부모가 얼리 어답터가 아닐 때, 자신이 얼리 어답터
     * 리프노드는 부모가 얼리 어답터일 때, 자신은 얼리 X
     *             부모가 얼리 어답터가 아닐 때, 자신이 얼리 어답터
     */
    static int n;
    static List<List<Integer>> tree;
    static int[][] dp;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine()); // 최대 1,000,000
	    tree = new ArrayList<>();
	    for(int i = 0; i <= n; ++i) tree.add(new ArrayList<>());
	    dp = new int[n+1][2];
	    
	    StringTokenizer st;
	    for(int i = 0; i < n - 1; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        
	        int u = Integer.parseInt(st.nextToken());
	        int v = Integer.parseInt(st.nextToken());
	        
	        tree.get(u).add(v);
	        tree.get(v).add(u);
	    }
	    
	    int min = recur(1, 0, true);
	    System.out.println(min);
	}
	
	public static int recur(int num, int parent, boolean isParentEA) {
	    int second = isParentEA ? 1 : 0;
	    if(dp[num][second] != 0) return dp[num][second];
	    
	    // case 1. 본인이 얼리 어답터 O
	    int case1 = 1;
	    for(int child : tree.get(num)) {
	        if(child == parent) continue;
            case1 += recur(child, num, true);
        }
	    
	    // case 2. 본인이 얼리 어답터 X
	    if(isParentEA) {
	        int case2 = 0;
	        for(int child : tree.get(num)) {
	            if(child == parent) continue;
	            case2 += recur(child, num, false);
	        }
	        
	        return dp[num][second] = Math.min(case1, case2);
	    }
        
        return dp[num][second] = case1;
	}
}