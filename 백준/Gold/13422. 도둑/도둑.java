import java.io.*;
import java.util.*;

public class Main
{
	static int n, m, k;
	static int[] money;

	public static void main(String[] args) throws IOException {
	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder answer = new StringBuilder();
	int t = Integer.parseInt(bf.readLine());
	
	while(t > 0) {
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    k = Integer.parseInt(st.nextToken());
	    
	    money = new int[n];
	    st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) money[i] = Integer.parseInt(st.nextToken());
	
	    int sum = 0, count = 0;
	    for(int i = 0; i < m; ++i) sum += money[i];
	    if(sum < k) ++count;
	
	    if(m == n) {
		answer.append(count).append("\n");
		--t;
		continue;
	    }
	
	    for(int i = m; i < n + m - 1; ++i) {
		sum += (money[i % n] - money[i - m]);
		if(sum < k) count++;
	    }
	
	    answer.append(count).append("\n");
	    --t;
	}
	
	System.out.print(answer);
	}
}
