import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i = 1; i < n; i++) {
            int num = nums[i];

            if (list.get(list.size() - 1) < num) {
                list.add(num);
                continue;
            }
            
            int left = 0;
            int right = list.size() - 1;
            int idx = right;
            while(left <= right) {
                int mid = left + (right - left) / 2;
                if(list.get(mid) >= num) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            
            list.set(idx, num);
        }
        
        System.out.println(list.size());
    }
}