import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // dfs -> 100,000 * 100,000 = 10,000,000,000 (시간 초과)
        // 양쪽에서 더했을 때 0에 가까울 확률 up!
        // 가장 양쪽을 더함 -> +라면 더 많이 빼야 함으로 오른쪽을 한칸 앞으로
        //              -> -라면 더 많이 더해야 함으로 왼쪽을 한칸 뒤로
        //              => 투포인터?

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] values = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; ++i) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = n - 1;
        int min = Integer.MAX_VALUE;
        int l = 0, r = n - 1;
        while(left < right) {
            int sum = values[left] + values[right];
            if(Math.abs(sum) < min) {
                min = Math.abs(sum);
                l = left;
                r = right;
            }

            if(sum < 0) {
                left++;
            } if(sum >= 0) {
                right--;
            }
        }

        System.out.println(values[l] + " " + values[r]);
    }
}
