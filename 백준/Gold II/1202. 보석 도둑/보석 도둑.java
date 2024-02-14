import java.io.*;
import java.util.*;

public class Main
{
    public static class Jewelry {
        int weight, value;

        Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
    
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        // 용량이 작은 가방부터 가방에 넣을 수 있는 최대 가치 보석을 넣는다!
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Jewelry[] jewelries = new Jewelry[n];
        int[] bags = new int[k];

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewelries[i] = new Jewelry(weight, value);
        }

        for(int i = 0; i < k; ++i) {
            bags[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(jewelries, (j1, j2) -> j1.weight - j2.weight);  // 보석을 용량이 작은 순으로
        Arrays.sort(bags); // 가방을 용량이 작은 순으로

        PriorityQueue<Jewelry> pq = new PriorityQueue<>(
            (a, b) -> b.value - a.value); // 보석을 가치가 높은 순으로

        long sum = 0;
        int index = 0; // pq에 들어가야 할 보석 인덱스
        for(int i = 0; i < k; ++i) {
            int bag = bags[i];

            while(index < n) {
                if(jewelries[index].weight <= bag) {
                    pq.offer(jewelries[index++]);
                } else break;
            }

            if(!pq.isEmpty()) sum += (long) pq.poll().value;
        }

        System.out.println(sum);
    }
}
