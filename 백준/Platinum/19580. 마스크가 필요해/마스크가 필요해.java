import java.io.*;
import java.util.*;

public class Main
{
    static class Customer {
        long min, max;

        public Customer(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 시민 수 (최대 500,000)
        int m = Integer.parseInt(st.nextToken()); // 상점 수 (최대 500,000)

        // 시민을 한명씩 돌면서 살 수 있는 마스크를 체크 O(nm) -> 시간초과!
        // 풀이 봄 -> 그리디(범위가 가장 좁은 사람부터 살 수 있도록)

        PriorityQueue<Customer> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.max, b.max) == 0 ? Long.compare(b.min, a.min) : Long.compare(a.max, b.max));
        
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            long min = Long.parseLong(st.nextToken());
            long max = Long.parseLong(st.nextToken());
            pq.add(new Customer(min, max));
        }

        TreeMap<Long, Integer> map = new TreeMap<>();
        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            long price = Long.parseLong(st.nextToken());
            int stock = Integer.parseInt(st.nextToken());
            if(map.containsKey(price)) {
                map.replace(price, map.get(price) + stock);
            } else {
                map.put(price, stock);
            }
        }

        int count = 0;
        while(!pq.isEmpty() && !map.isEmpty()) {
            Customer customer = pq.poll();
            Map.Entry<Long, Integer> entry = map.ceilingEntry(customer.min);

            if(entry == null) continue;

            if(entry.getKey() <= customer.max) {
                int stock = entry.getValue();
                stock--;
                count++;
                
                if(stock == 0) {
                    map.remove(entry.getKey());
                } else {
                    map.replace(entry.getKey(), stock);
                }
            }
        }

        System.out.println(count);
	}

}
