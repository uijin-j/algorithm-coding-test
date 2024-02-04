import java.io.*;
import java.util.*;

public class Main
{
    public static char[] letters = {'A', 'C', 'G', 'T'};
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        String dna = bf.readLine();
        
        Map<Character, Integer> map = new HashMap<>();
        int count = 0, answer = 0;
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < 4; ++i) {
            map.put(letters[i], Integer.parseInt(st.nextToken()));
            if(map.get(letters[i]) > 0) count++;
        }

        for(int i = 0; i < p; ++i) {
            char c = dna.charAt(i);
            map.put(c, map.get(c) - 1);
            if(map.get(c) == 0) count--;
        }

        if(count == 0) answer++;

        for(int i = 0; i < s - p; ++i) {
            char left = dna.charAt(i);
            char right = dna.charAt(i + p);

            map.put(right, map.get(right) - 1);
            if(map.get(right) == 0) count--;
            
            map.put(left, map.get(left) + 1);
            if(map.get(left) == 1) count++;

            if(count == 0) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
