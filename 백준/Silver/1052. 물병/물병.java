import java.io.*;
import java.util.*;

public class Main
{
    static PriorityQueue<Integer> pq = new PriorityQueue<>(); // 8 4 1
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        rec(0, n);

        int answer = 0;
        while(pq.size() > k) {
            int first = pq.poll();
            int second = pq.poll();
            
            answer += second - first;
            pq.offer(second * 2);
        }

        System.out.println(answer);
	}

    public static void rec(int count, int n) {
        if(n == 1) {
            pq.add((int) Math.pow(2, count));
            return;
        }

        int remainder = n % 2;
        int share = n / 2;

        if(remainder > 0) {
            pq.add((int) Math.pow(2, count));
        }

        rec(count + 1, share);
    }
}
