import java.io.*;
import java.util.*;

public class Main
{
    static int n;
    static long m;
    static long[] times;
    // 1 <= N(심사대 갯수) <= 100,000
    // 1 <= M(인원 수) <= 1,000,000,000
    // 1 <= T(시간) <= 1,000,000,000
	public static void main(String[] args) throws Exception {
		  // 최악의 시간 max(T) * M
		  // 어떤 시간 t가 주어졌을 때, 그 시간에 심사를 모두 받을 수 있는가?
		  BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		  StringTokenizer st = new StringTokenizer(bf.readLine());
		  n = Integer.parseInt(st.nextToken());
		  m = Long.parseLong(st.nextToken());
		  times = new long[n];
		  long max = Long.MIN_VALUE;
		  for(int i = 0; i < n; ++i) {
		      times[i] = Long.parseLong(bf.readLine());
		      max = Math.max(max, times[i]);
		  }
		  
		  Arrays.sort(times);
		  
		  long lt = 0;
		  long rt = max * m;
	      long answer = rt;
		  while(lt <= rt) {
		      long mid = (lt + rt) / 2;
		      if(check(mid)) { // mid초 안에 심사받는 것이 가능하면
		          answer = mid;
		          rt = mid - 1;
		      } else {
		          lt = mid + 1;
		      }
		  }
		  
		  System.out.println(answer);
	}
	
	public static boolean check(long target) {
	    long sum = 0;
	    for(long t : times) {
	        long count = target / t;
	        sum += count;
	        if(sum >= m) {
	            return true;
	        }
	    }
	    
	    return false;
	}
}
