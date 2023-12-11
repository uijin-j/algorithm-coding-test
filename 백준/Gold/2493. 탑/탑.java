import java.io.*;
import java.util.*;

public class Main {
    static class Top {
        int num, height;

        public Top(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        
        Stack<Top> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; ++i) {
            int height = Integer.parseInt(st.nextToken());

            boolean flag = true;
            while(!stack.isEmpty()) {
                Top peeked = stack.peek();
                if(peeked.height > height) {
                    System.out.print(peeked.num + " ");
                    stack.push(new Top(i + 1, height));
                    flag = false;
                    break;
                } else {
                    stack.pop();
                }
            }

            if(flag) {
                System.out.print("0 ");
                stack.push(new Top(i + 1, height));
            }
        }
    }
}
