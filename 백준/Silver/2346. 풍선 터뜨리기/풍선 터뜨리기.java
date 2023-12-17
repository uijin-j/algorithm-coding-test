import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] nums = new int[n];
        Deque<Integer> dq = new ArrayDeque<>(); // -3 -1 2 1

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
            dq.offer(i);
        }

        while(!dq.isEmpty()) {
            int index = dq.poll();
            int num = nums[index];
            System.out.print((index + 1) + " ");

            if(dq.isEmpty()) break;

            if(num > 0) {
                for(int i = 0; i < num - 1; ++i) { // 3
                    dq.offer((dq.poll()));
                }
            } else {
                num *= -1;
                for(int i = 0; i < num; ++i) {
                    dq.offerFirst((dq.pollLast()));
                }
            }
        }

    }
}