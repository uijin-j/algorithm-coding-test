import java.util.*;
import java.io.*;

public class Main
{
    static class Lecture {
        int start, end;
        
        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    
	    PriorityQueue<Lecture> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
	    
	    for(int i = 0; i < n; ++i) {
	        StringTokenizer st = new StringTokenizer(bf.readLine());
	        int start = Integer.parseInt(st.nextToken());
	        int end = Integer.parseInt(st.nextToken());
	        
	        pq.offer(new Lecture(start, end));
	    }
	    
	    PriorityQueue<Integer> rooms = new PriorityQueue<>();
	    rooms.offer(0);
	    while(!pq.isEmpty()) {
	        Lecture lec = pq.poll();
	        
	        int room = rooms.poll();
	        if(room > lec.start) {
	            rooms.offer(room);
	        }
	        
	        rooms.offer(lec.end);
	    }
	    
	    System.out.println(rooms.size());
	}
}
