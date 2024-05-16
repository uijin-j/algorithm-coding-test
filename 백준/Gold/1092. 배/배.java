import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 1 <= n <= 50
        int[] cranes = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; ++i) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(bf.readLine()); // 1 <= m <= 10_000
        int[] boxes = new int[m];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < m; ++i) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cranes);
        Arrays.sort(boxes);

        if(cranes[n-1] < boxes[m-1]) {
            System.out.println(-1);
            return;
        }

        int left = m / n + ((m % n) > 0 ? 1 : 0);
        int right = m;
        int min = right;
        while(left <= right) {
            int mid = left + (right - left) / 2;

            // mid분으로 모든 상자를 옮길 수 있는가?
            boolean canLoad = true;
            int box = m - 1;
            for(int i = n - 1; i >= 0; --i) {
                for(int j = 0; j < mid; ++j) {
                    if(box < 0) break;
                    if(cranes[i] < boxes[box]) {
                        canLoad = false;
                        break;
                    }
                    --box;
                }
                if(!canLoad) break;
            }

            if(canLoad) {
                min = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        

        System.out.println(min);
	}

}
