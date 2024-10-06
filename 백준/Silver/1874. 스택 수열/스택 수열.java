import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        Stack<Integer> stack = new Stack<>();
        List<String> opList = new ArrayList<>();
        
        int[] targets = new int[n];
        for(int i = 0; i < n; ++i) targets[i] = Integer.parseInt(br.readLine());
        
        int number = 1;
        int targetIdx = 0;
        while(number <= n || !stack.isEmpty()) {
            if (!stack.empty() && stack.peek() == targets[targetIdx]) { 
                stack.pop();
                opList.add("-");
                targetIdx++;
                continue;
            }
            
            if(number > n) break;
            
            stack.push(number++);
            opList.add("+");
        }
        
        if (stack.isEmpty()) {
            for (int i = 0; i < opList.size(); ++i) {
                System.out.println(opList.get(i));
            }
        } else {
            System.out.print("NO");
        }
    }
}