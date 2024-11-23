import java.io.*;
import java.util.*;

// 20:05 시작!
public class Main
{
    /**
     * 스택?
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    String nataion = bf.readLine();
	    
	    Deque<Character> stack = new ArrayDeque<>();
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < nataion.length(); ++i) {
	        char ch = nataion.charAt(i);
	        
	        if(ch >= 'A' && ch <= 'Z') { // 숫자라면 그대로 문자열에 담아준다.
	            sb.append(String.valueOf(ch));
	            continue;
	        }
	        
	        if(ch == '(') {
	            stack.push(ch);
	            continue;
	        }
	        
	        if(ch == ')') {
                while(stack.peek() != '(') { // '('이 나올때까지 문자열에 담아준다.
                    sb.append(stack.pop());
                }
                stack.pop(); // '('연산자를 꺼내준다.
                continue;
            }
            
            // + - / * 연산자 일경우
            while(!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(ch)) {
                sb.append(stack.pop());
            }

            stack.push(ch);
	    }
	    
	    while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        System.out.println(sb);
	}
	
	public static int getPriority(char op) {
        if(op == '*' || op == '/') return 2;
        if(op == '+' || op == '-') return 1;
        return 0; // '('는 꺼내져서는 안되기 때문에 제일 낮은 값을 반환
    }
}