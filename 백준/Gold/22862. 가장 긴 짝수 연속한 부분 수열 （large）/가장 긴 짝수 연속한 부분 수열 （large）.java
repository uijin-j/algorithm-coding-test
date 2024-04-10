import java.io.*;
import java.util.*;

public class Main
{
	/**
	 *  1 <= n <= 1_000_000
	 *  1 <= k <= 100_000
	 */
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		boolean[] isEven = new boolean[n];

		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < n; ++i) {
			int num = Integer.parseInt(st.nextToken());
			isEven[i] = num % 2 == 0;
		}

		// 풀이봄.. 투포인터! O(n)
		int left = 0, right = 0, count = 0;
		int max = 0;
		while(right < n) {
			if(!isEven[right]) { // right가 홀수면
				while(count >= k) {
					if(!isEven[left]) {
						--count;
					}

					++left;
				}

				++count; // 지우기
			}

			max = Math.max(max, (right - left + 1) - count);
			++right;
		}

		System.out.println(max);
    }
}
