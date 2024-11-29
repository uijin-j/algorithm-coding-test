import java.io.*;
import java.util.*;

// 22:00 시작!
public class Main
{
    /**
     * 우선순위 큐? 실제 시간이 가는 것을 t로 나타내기
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    int[][] problems = new int[n][2];
	    StringTokenizer st;
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        problems[i][0] = Integer.parseInt(st.nextToken());
	        problems[i][1] = Integer.parseInt(st.nextToken());
	    }
	    
	    Arrays.sort(problems, (a, b) -> b[0] - a[0]); // 데드라인순으로 내림차순

	    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
	    int idx = 0;
	    int t = problems[0][0];
	    int total = 0;
	    while(t > 0) {
	        while(idx < n && problems[idx][0] >= t) {
	            pq.offer(problems[idx++]);
	        }
	        
	        if(!pq.isEmpty()) {
	            int[] solved = pq.poll();
	            total += solved[1];   
	        }
	        
	        t--;
	    }
	    
	    System.out.println(total);
	}
}
