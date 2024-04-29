import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < n; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Map<Integer, Integer> index = new HashMap<>(); ; // left ~ right 구간에서 가장 빠른(왼쪽) 위치 찾기
		Map<Integer, Integer> count = new HashMap<>();
		int answer = 1, left = 0, right = 0;
		while(right < n) {
			int num = arr[right];

			// left ~ right 사이에 num이 이미 k개 이상 있을 때
			if(count.containsKey(arr[right]) && count.get(arr[right]) >= k) {
				while(left < index.get(arr[right]) + 1) {
					count.put(arr[left], count.get(arr[left]) - 1);
					left++;
				}
			}

			// index를 업데이트 해야 되면 하기!
			if(!index.containsKey(num) || index.get(num) <= left) {
				index.put(num, right);
			}

			count.put(arr[right], count.getOrDefault(arr[right], 0) + 1);

			answer = Math.max(answer, right - left + 1);
			++right;
		}

		System.out.println(answer);
    }
}
