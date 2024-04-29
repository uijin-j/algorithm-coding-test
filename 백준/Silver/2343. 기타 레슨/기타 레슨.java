import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken()); // 1 ~ 100,000
		int m = Integer.parseInt(st.nextToken()); // 1 ~ N(100,000)
		int[] lectures = new int[n];

		st = new StringTokenizer(bf.readLine());
		int sum = 0;
		for(int i = 0; i < n; ++i) {
			lectures[i] = Integer.parseInt(st.nextToken());
			sum += lectures[i];
		}

		int left = 1;
		int right = sum;
		int answer = Integer.MAX_VALUE;
		while(left <= right) {
			int mid = left + (right - left) / 2; // 블루레이의 크기를 mid로 해도 되는가?
			
			int count = 1;
			int total = 0;
			for(int lec : lectures) {
				if(lec > mid) {
					count = m + 1;
					break;
				}

				if(total + lec <= mid) {
					total += lec;
					continue;
				}

				count++;
				total = lec;
			}

			if(count <= m) {
				right = mid - 1;
				answer = Math.min(answer, mid);
			} else {
				left = mid + 1;
			}

		}

		System.out.println(answer);
    }
}
