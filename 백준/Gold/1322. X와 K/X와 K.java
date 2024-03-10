import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        long x = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        // X + Y = X | Y 을 만족하는 K번째로 작은 자연수 Y
        // O(n)으로 풀이 X
        // X = ...0000101 (5)
        // K = .......101 (5)
        // Y = _______0_0

        String binaryX = Long.toBinaryString(x);
        String binaryK = Long.toBinaryString(k);
        StringBuilder y = new StringBuilder();
        int idxK = binaryK.length() - 1;
        for(int idx = binaryX.length() - 1; idx >= 0; --idx) {
            int bit = binaryX.charAt(idx);
            if(bit == '1') {
                y.append("0");
                continue;
            }

            if(idxK < 0) continue; 
            char toReplace = binaryK.charAt(idxK--);
            y.append(toReplace);
        }

        while(idxK >= 0) {
            char toReplace = binaryK.charAt(idxK--);
            y.append(toReplace);
        }

        System.out.println(Long.parseLong(y.reverse().toString(), 2));
    }
}
