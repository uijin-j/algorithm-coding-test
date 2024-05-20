import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        String regex = "(100+1+|01)+";
        Pattern pattern = Pattern.compile(regex);

        StringBuilder sb = new StringBuilder();
        while(t > 0) {
            String signal = bf.readLine();

            if(pattern.matcher(signal).matches()) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }

            sb.append("\n");

            --t;
        }

        System.out.println(sb);
	}

}
