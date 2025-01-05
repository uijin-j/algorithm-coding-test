import java.io.*;
import java.util.*;

// 14:16 시작! 15:54 끗! (1시간 40분)
public class Main {
    /**
     * 넣고 빼는 쿼리(a, s)는 OK (스택 자료구조를 사용)
     * 문제는 과거로 돌아가는 쿼리(t) 각 쿼리별 상태를 알 수 있어야 함
     *  - 미리 저장? => 메모리 초과
     *  - 버전 관리 시스템 처럼 적절한 지점부터 쿼리를 다시 실행하면서 해당 시간의 상태를 찾기?
     */
    static String[][] querys;
    static int[] parents;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    Deque<Integer> stack = new ArrayDeque<>();
	    parents = new int[n + 1]; // parents[i]: i의 부모 (부모의 상태에서 자신까지 쿼리를 실행시키면 본인의 상태가 나옴!)
	    querys = new String[n + 1][]; // query[i]: i 번째 쿼리
	    StringBuilder sb = new StringBuilder();
	    for(int i = 1; i <= n; ++i) {
	        String[] query = bf.readLine().split(" ");
	        querys[i] = query;
	        
	        String cmd = query[0];
	        if(cmd.equals("s")) { // 삭제
	            stack.pollLast();
	            parents[i] = parents[i-1];
	            if(stack.isEmpty()) sb.append(-1).append("\n");
	            else sb.append(stack.peekLast()).append("\n");
	            
	            continue;
	        }
	        
	        int k = Integer.parseInt(query[1]);
	        if(cmd.equals("a")) { // 추가
	            stack.addLast(k);
	            parents[i] = parents[i-1];
	            sb.append(k).append("\n");
	            
	            continue;
	        }
	        
	        // 시간여행
	        parents[i] = i;
            stack = find(k - 1);
            if(stack.isEmpty()) sb.append(-1).append("\n");
            else sb.append(stack.peekLast()).append("\n");
	    }
	    
	    System.out.println(sb.toString());
	}
	
	public static Deque<Integer> find(int time) {
	    Deque<Integer> result = new ArrayDeque<>();
	    if(parents[time] == 0) {
	        for(int q = 1; q <= time; ++q) {
	            if(querys[q][0].equals("a")) result.addLast(Integer.parseInt(querys[q][1]));
	            else result.pollLast();
	        }
	        
	        return result;
	    }
	    
	    if(parents[time] == time) {
	        return find(Integer.parseInt(querys[time][1]) - 1);
	    }
	    
	    result = find(parents[time]);
	    for(int q = parents[time] + 1; q <= time; ++q) {
            if(querys[q][0].equals("a")) result.addLast(Integer.parseInt(querys[q][1]));
            else result.pollLast();
        }
	    
	    return result;
	}
}