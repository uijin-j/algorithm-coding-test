import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken()); // 벨트에 놓은 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int[] belt = new int[2*n];

		for(int i = 0; i < n; ++i) {
			int sushi = Integer.parseInt(bf.readLine());
			belt[i] = sushi;
			belt[n+i] = sushi;
		}

		int[] ate = new int[d+1];
		int answer = 1;
		ate[c] += 1; // 쿠폰으로 먹기
		for(int i = 0; i < k; ++i) { // 0번째 초밥부터 k개를 먹는다.
			int sushi = belt[i];
			if(ate[sushi] == 0) ++answer;
			ate[sushi] += 1;
		}

		int left = 0, ateSushi = answer; // n-2 -> (n-2) + k
		for(int right = k; right < n+k-1; ++right) {
			int subtract = belt[left];
			ate[subtract] -= 1;
			if(ate[subtract] == 0) ateSushi -= 1;

			int add = belt[right];
			if(ate[add] == 0) ateSushi += 1;
			ate[add] += 1;

			answer = Math.max(answer, ateSushi);
			++left;
		}

		System.out.println(answer);
    }
}
