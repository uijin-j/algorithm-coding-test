import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] kids = new int[n];
        int[] dp = new int[n]; // dp[i] = i까지의 LIS
        for(int i = 0; i < n; ++i) {
            kids[i] = Integer.parseInt(bf.readLine());
            dp[i] = 1;
        }
        
        for(int i = 1; i < n; ++i) {
            for(int j = 0; j < i; ++j) {
                if(kids[j] < kids[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        System.out.println(n - Arrays.stream(dp).max().getAsInt());
	}

}
