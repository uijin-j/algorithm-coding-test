import java.io.*;
import java.util.*;

public class Main
{
	// 모든 경우? 
	// 1 ~ m개의 그룹으로 나눴을 때, nCm-1 * n => 시간 초과
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] nums = new int[n];

		st = new StringTokenizer(bf.readLine());
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < n; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, nums[i]);
		}

		int left = 0;
		int right = max;
		int answer = right;
		while(left <= right) {
			int mid = left + (right - left) / 2; // mid 이하의 차이로 구간을 만들기
			
			int minV = nums[0], maxV = nums[0], count = 1;
			for(int i = 1; i < n; ++i) {
				minV = Math.min(minV, nums[i]);
				maxV = Math.max(maxV, nums[i]);
				if(maxV - minV > mid) {
					++count;
					minV = nums[i];
					maxV = nums[i];
				}
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
