import java.util.*;
import java.io.*;

public class Main
{
    // 완전 탐색? nCc -> c의 최댓값은 n이고, n의 최댓값은 200,000 -> 시간 초과
	// 이분 탐색 nlogn -> 200,000log200,000
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] houses = new int[n];
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < n; ++i) {
		    houses[i] = Integer.parseInt(bf.readLine());
		}
		
		Arrays.sort(houses);
		
        int left = 1;
        int right = houses[n-1] - houses[0];
        int max = left;
		while(left <= right) {
		    int mid = left + (right - left) / 2;
		    
		    int count = 1;
		    int now = houses[0];
		    while(true) {
		        int idx = Arrays.binarySearch(houses, now + mid);

                if(idx >= 0) {
                    now = houses[idx];
                    count++;
                    continue;
                }
                
                if(Math.abs(idx) - 1 < n) {
                    now = houses[Math.abs(idx) - 1];
	                count++;   
                } else {
	                break;
	            }
		    }
		    
		    if(count >= m) {
	            left = mid + 1;
	            max = Math.max(max, mid);
	        } else {
	            right = mid - 1;
	        }
		}
		
		System.out.println(max);
	}
}
