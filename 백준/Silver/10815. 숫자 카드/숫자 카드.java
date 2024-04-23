import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
		int[] cards = new int[n];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < n; ++i) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(cards);

		int m = Integer.parseInt(bf.readLine());
		int[] answer = new int[m];
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < m; ++i) {
			int target = Integer.parseInt(st.nextToken());
			if(Arrays.binarySearch(cards, target) < 0) {
				answer[i] = 0;
			} else {
				answer[i] = 1;
			}
		}
		

		for(int ans : answer) {
			System.out.print(ans + " ");
		}
    }
}
