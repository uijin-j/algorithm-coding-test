import java.io.*;
import java.util.*;

// 22:03 시작!
public class Main {
    // 기한이 가장 적게 남은 기프티콘부터 사용!
    // 기한을 연장하면 +30일
    // 내가 사용하려고 했던 날에 해당 이모티콘이 가장 유효기간이 짧아야 함!
    // 단순한 시뮬레이션으로는 시간 초과가 날 것 같다..
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    int[] expire = new int[n];

	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) {
	        expire[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    List<Gift> gifts = new ArrayList<>();
	    st = new StringTokenizer(bf.readLine());
	    for(int i = 0; i < n; ++i) {
	        int useAt = Integer.parseInt(st.nextToken());
	        gifts.add(new Gift(i, useAt, expire[i]));
	    }
	    
	    gifts.sort((a, b) -> a.useAt - b.useAt == 0 ? a.expire - b.expire : a.useAt - b.useAt);
	    
	    long count = 0;
	    int max = 0;
	    for(int i = 0; i < n; ++i) {
	        int today = gifts.get(i).useAt;
	        max = Math.max(max, today);
            
            int todayMax = -1;
	        while(i < n && gifts.get(i).useAt == today) {
	            if(max > gifts.get(i).expire) {
	                int extend = (max - gifts.get(i).expire + 29) / 30;
    	            gifts.get(i).expire += 30 * extend;
    	            count += extend;
	            }

	            todayMax = Math.max(todayMax, gifts.get(i).expire);
	            ++i;
	        }
	        
	        max = todayMax;
	        
	        if(i == n) break;
	        --i;
	    }
	    
	    System.out.println(count);
	}
	
	public static class Gift {
	    int num, useAt, expire;
	    
	    public Gift(int num, int useAt, int expire) {
	        this.num = num;
	        this.useAt = useAt;
	        this.expire = expire;
	    }
	}
}