import java.io.*;
import java.util.*;

// 19:59 시작!
public class Main
{
    /**
     * A에서 B로 가는 경로 중 다리의 최소 중량이 최대가 되는 경우?
     * 
     * sol 1. DFS로 모든 경로를 탐색? O(NM) ~= 1_000_000_000 애매하다..
	 * sol 2. 다익스트라? O(mlogn) 시간 초과ㅠㅜ
	 * sol 3. 이분탐색 + DFS
     */
    static boolean possible;
    static List<Node>[] bridges;
    static boolean[] visit;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    bridges = new ArrayList[n + 1];
	    for(int i = 1; i <= n; ++i) {
	        bridges[i] = new ArrayList<>();
	    }
	    
	    // 1. 입력 받기
	    int left = 1;
	    int right = 1;
	    for(int i = 0; i < m; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int p1 = Integer.parseInt(st.nextToken());
	        int p2 = Integer.parseInt(st.nextToken());
	        int limit = Integer.parseInt(st.nextToken());
	        
	        bridges[p1].add(new Node(p2, limit));
	        bridges[p2].add(new Node(p1, limit));
	        right = Math.max(right, limit);
	    }
	    
	    st = new StringTokenizer(bf.readLine());
	    int factory1 = Integer.parseInt(st.nextToken());
	    int factory2 = Integer.parseInt(st.nextToken());
	    int max = left;
	    while(left <= right) {
	        int mid = left + (right - left) / 2;
	        possible = false;
	        visit = new boolean[n + 1];
	        visit[factory1] = true;
	        dfs(factory1, factory2, mid);
	        
	        if(possible) {
	            max = mid;
	            left = mid + 1;
	        } else {
	            right = mid - 1;
	        }
	    }
	    
	    System.out.println(max);
	}
	
	public static void dfs(int island, int goal, int limit) {
	    if(island == goal) {
	        possible = true;
	        return;
	    }
	    
	    for(Node next : bridges[island]) {
	        if(next.limit >= limit && !visit[next.to]) {
	            visit[next.to] = true;
	            dfs(next.to, goal, limit);
	        }
	    }
	}
	
	public static class Node {
	    int to, limit;
	    
	    public Node(int to, int limit) {
	        this.to = to;
	        this.limit = limit;
	    }
	}
}