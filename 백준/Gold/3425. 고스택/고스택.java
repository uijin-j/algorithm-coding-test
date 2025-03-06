import java.io.*;
import java.util.*;

// 16:24 시작!
public class Main
{
    /**
     * 구현
     */
    static final long MAX = 1_000_000_000L;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

	    String input = bf.readLine();
	    StringBuilder sb = new StringBuilder();
	    while(!input.equals("QUIT")) {
	        GoStack stack = new GoStack();
	        
	        while(!input.equals("END")) {
	            stack.cmds.add(input);
	            input = bf.readLine();
	        }
	        
	        int n = Integer.parseInt(bf.readLine());
	        for(int i = 0; i < n; ++i) {
	            String result = stack.go(Integer.parseInt(bf.readLine()));
	            sb.append(result);
	            sb.append("\n");
	        }
	        
	        sb.append("\n");
	        
	        bf.readLine();
	        input = bf.readLine();
	    }
	    
	    System.out.println(sb.toString());
	}
	
	public static class GoStack {
	    List<String> cmds;
	    
	    public GoStack() {
	        cmds = new ArrayList<>();
	    }
	    
	    public String go(int num) {
	        Stack<Integer> stack = new Stack();
	        stack.push(num);
	        
	        for(String command : cmds) {
	            String[] info = command.split(" ");
	            String cmd = info[0];
	            
	            if(cmd.equals("NUM")) {
	                int x = Integer.parseInt(info[1]);
	                stack.push(x);
	            }
	            
	            if(cmd.equals("POP")) {
	                if(stack.isEmpty()) return "ERROR";
	                stack.pop();
	            }
	            
	            if(cmd.equals("INV")) {
	                if(stack.isEmpty()) return "ERROR";
	                
	                int first = stack.pop();
	                stack.push(first * -1);
	            }
	            
	            if(cmd.equals("DUP")) {
	                if(stack.isEmpty()) return "ERROR";
	                stack.push(stack.peek());
	            }
	            
	            if(cmd.equals("SWP")) {
	                if(stack.size() < 2) return "ERROR";
	                
	                int first = stack.pop();
	                int second = stack.pop();
	                
	                stack.push(first);
	                stack.push(second);
	            }
	            
	            if(cmd.equals("ADD")) {
	                if(stack.size() < 2) return "ERROR";
	                
	                int first = stack.pop();
	                int second = stack.pop();
	                
	                if(Math.abs(first + second) > MAX) return "ERROR";
	                 
	                stack.push(first + second);
	            }
	            
	            if(cmd.equals("SUB")) {
	                if(stack.size() < 2) return "ERROR";
	                
	                int first = stack.pop();
	                int second = stack.pop();
	                
	                 if(Math.abs(second - first) > MAX) return "ERROR";
	                
	                stack.push(second - first);
	            }
	            
	            if(cmd.equals("MUL")) {
	                if(stack.size() < 2) return "ERROR";
	                
	                int first = stack.pop();
	                int second = stack.pop();
	                
	                if(Math.abs(first * (long) second) > MAX) return "ERROR";
	                
	                stack.push(first * second);
	            }
	            
	            if(cmd.equals("DIV")) {
	                if(stack.size() < 2) return "ERROR";
	                
	                int first = stack.pop();
	                int second = stack.pop();
	                
	                boolean isMinus = false;
	                if(first < 0) isMinus = !isMinus;
	                if(second < 0) isMinus = !isMinus;
	                
	                if(first == 0) return "ERROR";
	                
	                int result = Math.abs(second) / Math.abs(first);
	                if(isMinus) result *= -1;
	                stack.push(result);
	            }
	            
	            if(cmd.equals("MOD")) {
	                if(stack.size() < 2) return "ERROR";
	                
	                int first = stack.pop();
	                int second = stack.pop();
	                boolean isMinus = second < 0;
	                
	                if(first == 0) return "ERROR";
	                
	                int result = Math.abs(second) % Math.abs(first);
	                if(isMinus) result *= -1;
	                stack.push(result);
	            }
	        }
	        
	        if(stack.size() != 1) return "ERROR";
	        return String.valueOf(stack.peek());
	    }
	}
}