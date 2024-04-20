import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(bf.readLine());

		while(t > 0) {
		    long n = Long.parseLong(bf.readLine());
		    long left = 1;
		    long right = (long) Math.sqrt(2 * n);
		    long answer = 1;

		    while(left <= right) {
		        long mid = left + (right - left) / 2;
		        long count = mid * (mid + 1) / 2 ;
		        
		        if(count <= n) {
		            left = mid + 1;
		            answer = Math.max(answer, mid);
		        } else {
		            right = mid - 1;
		        }
		    }
		    
		    sb.append(answer).append("\n");
		    --t;
		}
		

		System.out.print(sb);
    }
}
