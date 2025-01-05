import java.io.*;
import java.util.*;

// 14:16 시작!
public class Main {
    /**
     * 넣고 빼는 쿼리(a, s)는 OK (스택 자료구조를 사용)
     * 문제는 과거로 돌아가는 쿼리(t) 각 쿼리별 상태를 알 수 있어야 함
     *  - 미리 저장?
     *  - 쿼리를 다시 처음부터 돌면서 해당 시간을 찾기? O(n^2) = 6_400_000_000
     */
    static int[] parents;
    static String[][] querys;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    Deque<Integer> stack = new ArrayDeque<>();
	    parents = new int[n + 1]; // 자신의 부모를 확인
	    querys = new String[n + 1][]; // 쿼리를 저장
	    StringBuilder sb = new StringBuilder();
	    // 0 1 2 3 4 5 6 7 8 
	    // 0 0 0 0 0 5
	    for(int i = 1; i <= n; ++i) {
	        String[] query = bf.readLine().split(" ");
	        String cmd = query[0];
	        querys[i] = query;
	        
	        if(cmd.equals("a")) {
	            int k = Integer.parseInt(query[1]);
	            stack.addLast(k);
	            parents[i] = parents[i-1];
	            sb.append(k).append("\n");
	        } else if (cmd.equals("s")) {
	            stack.pollLast();
	            parents[i] = parents[i-1];
	            if(stack.isEmpty()) {
	                sb.append(-1).append("\n");
	            } else {
	                sb.append(stack.peekLast()).append("\n");
	            }
	        } else {
	           int k = Integer.parseInt(query[1]);
	           parents[i] = i;
	           stack = find(k - 1);
	           if(stack.isEmpty()) {
	               sb.append(-1).append("\n");
	           } else {
	               sb.append(stack.peekLast()).append("\n");
	           }
	        }
	    }
	    
	    System.out.println(sb.toString());
	}
	
	public static Deque<Integer> find(int time) {
	    Deque<Integer> result = new ArrayDeque<>();
	    if(parents[time] == 0) {
	        for(int q = 1; q <= time; ++q) {
	            if(querys[q][0].equals("a")) {
	                result.addLast(Integer.parseInt(querys[q][1]));
	            } else {
	                result.pollLast();
	            }
	        }
	        
	        return result;
	    }
	    
	    if(parents[time] == time) {
	        return find(Integer.parseInt(querys[time][1]) - 1);
	    }
	    
	    result = find(parents[time]);
	    for(int q = parents[time] + 1; q <= time; ++q) {
            if(querys[q][0].equals("a")) {
                result.addLast(Integer.parseInt(querys[q][1]));
            } else {
                result.pollLast();
            }
        }
	    
	    return result;
	}
}