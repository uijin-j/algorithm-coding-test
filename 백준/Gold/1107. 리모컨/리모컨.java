import java.io.*;
import java.util.*;

public class Main
{
    static int n, m;
    static boolean[] broken = new boolean[10];
    
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        broken = new boolean[10];
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());
        
        if(m != 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int i = 0; i < m; ++i) {
                int num = Integer.parseInt(st.nextToken());
                broken[num] = true;
            }   
        }

        int min = Math.abs(n - 100);
        for(int i = 0; i <= 999999; ++i) {
            String number = String.valueOf(i);
            if(canPress(number)) {
                min = Math.min(min, Math.abs(n - i) + number.length());
            }
        }

        System.out.println(min);
    }
    
    static boolean canPress(String num) {
        for(char c: num.toCharArray()) {
            if(broken[c - '0']) return false;
        }
        
        return true;
    }
}
