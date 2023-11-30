import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.nextLine();
        int len = number.length();
        int[] dp = new int[len + 1]; // dp[i]는 첫번째 숫자부터 i개 숫자의 합

        dp[0] = 0;
        for(int i = 1; i <= len; ++i) {
            dp[i] = dp[i-1] + number.charAt(i-1) - '0';
        }
        
        for(int size = len; size > 0; --size) { // size는 크리 문자열의 크기
            if(size % 2 == 1) continue;
            int half = size / 2;
            for(int i = 1; i <= len - size + 1; ++i) { 
                // i번째 문자부터 size만큼을 크리 문자열 후보로! 
                int first = dp[i + half - 1] - dp[i - 1];
                int second = dp[i + size - 1] - dp[i + half - 1];
                if(first == second) {
                    System.out.println(size);
                    return;
                }
            }
        }
    }
}
