import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());

		StringBuilder sb = new StringBuilder();
		while(T > 0) {
			String w = bf.readLine();
			int k = Integer.parseInt(bf.readLine());

			Map<Character, List<Integer>> map = new HashMap<>();
			for(char c = 'a'; c <= 'z'; ++c) map.put(c, new ArrayList<>());
			int[] count = new int[26];
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < w.length(); ++i) {
				char c = w.charAt(i);
				count[c-'a'] += 1;
				map.get(c).add(i);
				if(count[c-'a'] == k) {
					int first = map.get(c).get(0);
					min = Math.min(i - first + 1, min);
					max = Math.max(i - first + 1, max);

					count[c-'a'] -= 1;
					map.get(c).remove(0);
				}
			}

			if(min == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
			} else {
				sb.append(min + " " + max).append("\n");
			}

			--T;
		}

		System.out.println(sb);
	}
}
