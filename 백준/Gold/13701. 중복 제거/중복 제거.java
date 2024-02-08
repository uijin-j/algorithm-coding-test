import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        BitSet set = new BitSet(5000001);

        while(st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if(set.get(num)) continue;
            set.set(num);
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
