import java.io.*;
import java.util.*;

// 14:35 시작!
public class Main {
    /**
     * 1번 지역에서 N번 지역으로 가는 최단거리
     */
    static int n, m;
    static final long INF = 70_000_000_000L;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    
	    List<CrossWalk>[] graph = new List[n + 1];
	    for(int i = 0; i <= n; ++i) {
	        graph[i] = new ArrayList<>();
	    }
	    
	    for(int i = 0; i < m; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int from = Integer.parseInt(st.nextToken());
	        int to = Integer.parseInt(st.nextToken());
	        
	        graph[from].add(new CrossWalk(to, i));
	        graph[to].add(new CrossWalk(from, i));
	    }
	    
	    long[] dist = new long[n + 1];
	    boolean[] check = new boolean[n + 1];
	    Arrays.fill(dist, INF);
	    
	    PriorityQueue<CrossWalk> pq = new PriorityQueue<>((a, b) -> Long.compare(a.time, b.time));
	    pq.offer(new CrossWalk(1, 0));
	    while(!pq.isEmpty()) {
	        CrossWalk cross = pq.poll();
	        
	        if(dist[cross.to] < cross.time) continue;
	        if(cross.to == n) {
	            System.out.println(cross.time);
	            return;
	        }
	        
	        for(CrossWalk next : graph[cross.to]) {
	            if(dist[next.to] > calculate(next.time, cross.time) + 1) {
	                dist[next.to] = calculate(next.time, cross.time) + 1;
	                pq.offer(new CrossWalk(next.to, calculate(next.time, cross.time) + 1));   
	            }
	        }
	    }
	}
	
	public static long calculate(long first, long now) {
	    first += m * (now / m);
	    if(first < now) first += m;
	    return first;
	}
	
	
	public static class CrossWalk {
	    int to;
	    long time;
	    
	    public CrossWalk(int to, long time) {
	        this.to = to;
	        this.time = time;
	    }
	}
	
}