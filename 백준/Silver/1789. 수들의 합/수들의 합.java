import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long s = Long.parseLong(bf.readLine());

		// 최대한 많은 자연수를 더해서 S를 만들어라. (같은 자연수를 여러 개 쓸 수 없다.)

		// S가 200이라면
		// 1 + 2 + 3 + ... + k => 서로 다른 k개의 자연수를 가지고 만들 수 있는 최솟값
		// 1 + 2 + .... + k = (k+1)/2*k

		long left = 1;
		long right = (long) Math.sqrt(4_294_967_295L * 2);
		long max = left;
		while(left <= right) {
			long mid = left + (right - left) / 2;

			if(mid * (mid+1) / 2 <= s) {
				left = mid + 1;
				max = Math.max(max, mid);
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(max);
    }
}
