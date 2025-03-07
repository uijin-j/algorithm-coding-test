import java.io.*;
import java.util.*;

// 2. 공주님의 정원
public class Main
{
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    int start = to365(3, 1);
	    int end = to365(11, 30); 
	    
	    // 1. 시작 날짜가 12월 1일 이후 or 종료날짜가 3월 1일 이전인 꽃은 필요가 없다.
	    PriorityQueue<Flower> pq = new PriorityQueue<>((a, b) -> a.from - b.from);
	    StringTokenizer st;
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int startMon = Integer.parseInt(st.nextToken());
	        int startDay = Integer.parseInt(st.nextToken());
	        int endMon = Integer.parseInt(st.nextToken());
	        int endDay = Integer.parseInt(st.nextToken());
	        
	        int from = to365(startMon, startDay);
	        int to = to365(endMon, endDay);
	        
	        if(from > end || to <= start) continue;
	        
	        pq.offer(new Flower(from, to));
	    }
	    
	    int cover = start;
	    int cnt = 0;
	    PriorityQueue<Flower> longer = new PriorityQueue<>((a, b) -> b.to - a.to);
	    while(!pq.isEmpty() || !longer.isEmpty()) {
	        while(!pq.isEmpty() && pq.peek().from <= cover) {
	            longer.offer(pq.poll());
	        }
	        
	        if(longer.isEmpty()) {
	            System.out.println(0);
	            return;
	        }
	        
	        Flower selected = longer.poll();
	        
	        if(cover >= selected.to) {
	            System.out.println(0);
	            return;
	        }
	        
	        cover = selected.to;
	        cnt++;
	        if(cover > end) {
	            System.out.println(cnt);
	            return;
	        }
	    }
	    
	    System.out.println(0);
	}
	
	static int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static int to365(int mon, int day) {
	    int total = 0;
	    for(int i = 1; i < mon; ++i) {
	        total += days[i];
	    }
	    
	    return total += day;
	}
	
	public static class Flower {
	    int from, to;
	    
	    public Flower(int from, int to) {
	        this.from = from;
	        this.to = to;
	    }
	}
}