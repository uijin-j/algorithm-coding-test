import java.io.*;
import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for(int i = 0; i < n; ++i) {
            int num = Integer.parseInt(bf.readLine());
            if(num == 0) {
                if(pq.isEmpty()) System.out.println("0");
                else System.out.println(pq.poll());
            } else {
                pq.offer(num);
            }
        }
    }
}
