import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<String>[] math = new ArrayList[12]; // math[i]: i를 만들 수 있는 수식 List
        for(int i = 0; i <= 11; ++i) {
            math[i] = new ArrayList<>();
        }

        math[1].add("1");
        math[2].add("1+1");
        math[2].add("2");
        math[3].add("1+1+1");
        math[3].add("1+2");
        math[3].add("2+1");
        math[3].add("3");

        for(int i = 4; i <= n; ++i) {
            for(int j = 1; j <= 3; ++j) {
                for(String express: math[i-j]) {
                    math[i].add(j + "+" + express);
                }
            }
        }

        if(math[n].size() < k) {
            System.out.println("-1");
        } else {
            System.out.println(math[n].get(k - 1));
        }
    }
}
