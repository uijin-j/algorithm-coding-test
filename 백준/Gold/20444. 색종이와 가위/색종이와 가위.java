import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		long n = Long.parseLong(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		
		// 가로 갯수 n, 세로 갯수 m이면,
		// (n + 1) * (m + 1)개 사각형이 만들어짐!

		long lt = 0;
		long rt = n / 2;
		while(lt <= rt) {
		    long mid = lt + (rt - lt) / 2;
		    long count = (mid + 1) * (n - mid + 1);
		    
		    if(count < k) { // n/2에 가까워야 함
		        lt = mid + 1;
		    } else if(count > k) {
		        rt = mid - 1;
		    } else {
		        System.out.println("YES");
		        return;
		    }
		}
		
		System.out.println("NO");
	}
}
