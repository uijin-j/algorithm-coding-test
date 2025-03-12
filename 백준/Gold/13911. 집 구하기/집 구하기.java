import java.io.*;
import java.util.*;

// 집 구하기
public class Main
{
    /**
     * 맥세권 or 스세권을 먼저 확인해야 함
     * -> 모든 정점에서 모든 정점까지 최단거리? O(n^3) => 시간초과
     * -> 모든 맥도날드에서 집까지의 최단거리를 구한다. O(ElogV)
     * -> 모든 스타벅스에서 집까지의 최단거리를 구한다. O(ElogV)
     * -> 즉, 다익스트라 2번으로 해결 가능
     */
    static int v, e;
    static List<Node>[] graph;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    v = Integer.parseInt(st.nextToken());
	    e = Integer.parseInt(st.nextToken());
	    
	    graph = new List[v+1];
	    for(int i = 0; i <= v; ++i) graph[i] = new ArrayList<>();
	    for(int i = 0; i < e; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        int to = Integer.parseInt(st.nextToken());
	        int w = Integer.parseInt(st.nextToken());
	        
	        graph[from].add(new Node(to, w));
	        graph[to].add(new Node(from, w));
	    }
	    
	    // 맥날
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
	    st = new StringTokenizer(bf.readLine());
	    int m = Integer.parseInt(st.nextToken());
	    int x = Integer.parseInt(st.nextToken());
	    
	    st = new StringTokenizer(bf.readLine());
	    boolean[] isMac = new boolean[v+1];
	    for(int i = 0; i < m; ++i) {
	        int num = Integer.parseInt(st.nextToken());
	        isMac[num] = true;
	        pq.offer(new Node(num, 0));
	    }
	    
	    int[] mDist = new int[v+1]; // mDist[i]: i번째 집에서 맥날까지의 최단거리
	     Arrays.fill(mDist, x+1);
	    findMinDist(pq, mDist);
	    
	    // 스벅
	    pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
	    st = new StringTokenizer(bf.readLine());
	    int s = Integer.parseInt(st.nextToken());
	    int y = Integer.parseInt(st.nextToken());
	    
	    st = new StringTokenizer(bf.readLine());
	    boolean[] isStar = new boolean[v+1];
	    for(int i = 0; i < s; ++i) {
	        int num = Integer.parseInt(st.nextToken());
	        isStar[num] = true;
	        pq.offer(new Node(num, 0));
	    }
	    
	    int[] sDist = new int[v+1]; // sDist[i]: i번째 집에서 스벅까지의 최단거리
	    Arrays.fill(sDist, y+1);
	    findMinDist(pq, sDist);
	    
	    int answer = Integer.MAX_VALUE;
	    for(int i = 1; i <= v; ++i) {
	        if(isMac[i] || isStar[i]) continue;
	        if(mDist[i] > x || sDist[i] > y) continue;
	        answer = Math.min(answer, mDist[i] + sDist[i]);
	    }
	    
	    answer = (answer == Integer.MAX_VALUE) ? -1 : answer;
	    System.out.println(answer);
	    
	}
	
	public static void findMinDist(PriorityQueue<Node> pq, int[] min) {
	    while(!pq.isEmpty()) {
	        Node cur = pq.poll();
	        
	        if(cur.cost > min[cur.to]) continue;
	        min[cur.to] = cur.cost;
	       
	        for(Node next : graph[cur.to]) {
	            if(min[next.to] > cur.cost + next.cost) {
	                min[next.to] = cur.cost + next.cost;
	                pq.offer(new Node(next.to, cur.cost + next.cost));
	            }
	        }
	    }
	}
	
	public static class Node {
	    int to, cost;
	   
	    public Node(int to, int cost) {
	        this.to = to;
	        this.cost = cost;
	    }
	}
}