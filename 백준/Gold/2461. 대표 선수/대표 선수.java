import java.io.*;
import java.util.*;

// 1. 대표 선수
public class Main
{
    /**
     * 1. 모든 경우를 확인? O(m^n) 시간초과!
     * 2. 이분탐색? No
     */
    static int n, m;
    static int[][] powers;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    powers = new int[n][m];
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j = 0; j < m; ++j) {
	            powers[i][j] = Integer.parseInt(st.nextToken());
	        }
	        
	        Arrays.sort(powers[i]);
	    }
	    
	    int max = Integer.MIN_VALUE;
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.num - b.num);
	    for(int i = 0; i < n; ++i) {
	        int num = powers[i][0];
	        pq.offer(new Node(i, 0, num));
	        max = Math.max(max, num);
	    }
	    
	    int answer = Integer.MAX_VALUE;
	    while(true) {
	        Node minNode = pq.poll();
	        int ban = minNode.ban;
	        int idx = minNode.idx;
	        
	        answer = Math.min(answer, max - minNode.num);
	        
	        if(++idx == m) break;
	        
	        int num = powers[ban][idx];
	        max = Math.max(max, num);
	        pq.offer(new Node(ban, idx, num));
	    }
	    
	    System.out.println(answer);
	}
	
	static class Node {
	    int ban, idx, num;
	    
	    public Node(int ban, int idx, int num) {
	        this.ban = ban;
	        this.idx = idx;
	        this.num = num;
	    }
	}
}