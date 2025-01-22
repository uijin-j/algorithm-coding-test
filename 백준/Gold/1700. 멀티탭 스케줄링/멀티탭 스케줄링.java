import java.io.*;
import java.util.*;

public class Main
{
    // 비어있으면 그냥 꼽고, 제일 나중에 사용되는 애를 빼자! (이미 꼽혀 있으면 넘어가기)
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int k = Integer.parseInt(st.nextToken());
	    
	    int[] order = new int[k];
	    Queue<Integer>[] next = new Queue[k+1];
	    for(int i = 0; i <= k; ++i) {
	        next[i] = new LinkedList<>();
	    }
	    
	    st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < k; ++i) {
	        order[i] = Integer.parseInt(st.nextToken());
	        next[order[i]].offer(i);
	    }
	    
	    if(k <= n) { // 사용하는 전기 용품이 구멍 갯수보다 적으면
	        System.out.println(0);
	        return;
	    }
	    
	    Set<Integer> inUse = new HashSet<>();
	    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
	    int idx = 0;
	    int count = 0;
	    while(idx < k) {
	        int elec = order[idx++];
	        next[elec].poll();
	        int future = next[elec].isEmpty() ? Integer.MAX_VALUE : next[elec].peek();
	        
	        if(inUse.contains(elec)) {
	            Queue<int[]> temp = new LinkedList<>();
	            while(pq.peek()[0] != elec) {
	                temp.offer(pq.poll());
	            }
	            
	            int[] again = pq.poll();
	            again[1] = next[again[0]].isEmpty() ? Integer.MAX_VALUE : next[again[0]].peek();
	            pq.offer(again);
	            
	            while(!temp.isEmpty()) {
	                pq.offer(temp.poll());
	            }
	            
	            continue;
	        }
	        
	        if(pq.size() >= n) {
                int[] removed = pq.poll();
	            inUse.remove(removed[0]);
	            count++;
	        }
	        
	        pq.offer(new int[]{ elec, future });
	        inUse.add(elec);
	    }
	    
	    System.out.println(count);
	}
}