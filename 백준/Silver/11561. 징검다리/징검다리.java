import java.io.*;

public class Main
{
	// 1부터 k까지 합 = (k + 1) / 2 * k
	// 즉, k번 밟기 위해서는 최소 (k + 1) / 2 * k 가 n이하여야 함!
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(bf.readLine());

		while(t > 0) {
		    long n = Long.parseLong(bf.readLine());
		    long left = 1;
		    long right = Integer.MAX_VALUE;
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
