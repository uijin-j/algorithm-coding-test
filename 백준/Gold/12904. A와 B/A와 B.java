import java.io.*;

public class Main {
    static String s1, s2;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s1 = bf.readLine();
        s2 = bf.readLine();

        // 모든 경우? 2^(s1과 s2의 길이 차이 = 최대 999) -> 너무 많은데?
        // s1 -> s2 말고, s2 -> s1을 해보자!
        StringBuilder sb = new StringBuilder(s2);
        while(true) {
            if(sb.length() == s1.length()) {
                if(sb.toString().equals(s1)) {
                    System.out.println(1);
                } else System.out.println(0);

                return;
            }

            int lastIndex = sb.length() - 1;
            char last = sb.charAt(lastIndex);
            sb.deleteCharAt(lastIndex);
            if(last == 'B') {
                sb.reverse();
            }
        }
    }
}
