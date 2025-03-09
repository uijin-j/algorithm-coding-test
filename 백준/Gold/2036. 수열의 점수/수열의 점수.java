import java.io.*;
import java.util.*;

// 3. 수열의 점수
public class Main
{
    /**
     *  곱했을 때 이득
     *  => +,+ / -,-
     * => -는 최대한 곱해서 상쇄, 불가능하다면 가장 큰 음수만 더하기 (0으로 음수 상쇄 가능)
     * => +는 최대한 곱해서 크게 만들기 (1은 더하는게 나음)
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    PriorityQueue<Integer> minus = new PriorityQueue<>((a, b) -> a - b);
	    PriorityQueue<Integer> plus = new PriorityQueue<>((a, b) -> b - a);
	    int countOfZero = 0;
	    for(int i = 0; i < n; ++i) {
	        int num = Integer.parseInt(bf.readLine());
	        if(num > 0) plus.offer(num);
	        else if(num < 0) minus.offer(num);
	        else countOfZero++;
	    }
	    
	    long score = 0;
	    while(!minus.isEmpty()) {
	        if(minus.size() >= 2) {
	            score += minus.poll() * (long) minus.poll();
	            continue;
	        }
	        
	        int num = minus.poll();
	        if(countOfZero > 0) {
	            countOfZero--;
	            continue;
	        }
	        
	        score += num;
	    }
	    
	    while(!plus.isEmpty()) {
	        if(plus.size() >= 2) {
	            int num1 = plus.poll();
	            int num2 = plus.poll();
	            score += Math.max(num1 * (long) num2, num1 + num2);
	            continue;
	        }
	        
	        score += plus.poll();
	    }
	    
	    System.out.println(score);
	}
}