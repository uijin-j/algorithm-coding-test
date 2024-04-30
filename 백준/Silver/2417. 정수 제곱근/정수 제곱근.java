import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(bf.readLine());
		
		long left = 0;
		long right = (long) Math.sqrt((double) n) + 1;
		long answer = Long.MAX_VALUE;
		while(left <= right) {
			long mid = left + (right - left) / 2;

			if(Math.pow(mid, 2) >= n) {
				right = mid - 1;
				answer = Math.min(answer, mid);
			} else {
				left = mid + 1;
			}
		}


		System.out.println(answer);
    }
}
