import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		int[] nums = new int[n+1];
		int[] count = new int[n+1];
		for(int i = 1; i <= n; ++i) {
			nums[i] = Integer.parseInt(bf.readLine());
			count[nums[i]]++;
		}

		while(true) {
			boolean flag = true;
			for(int i = 1; i <= n; ++i) {
				if(nums[i] == -1 || count[i] > 0) continue;
				count[nums[i]]--;
				nums[i] = -1;
				flag = false;
			}

			if(flag) break;
		}

		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i = 1; i <= n; ++i) {
			if(nums[i] != -1) {
				cnt++;
				sb.append("\n").append(i);
			}
		}

		System.out.print(cnt);
		System.out.println(sb);
	}
}
