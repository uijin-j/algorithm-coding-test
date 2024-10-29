import java.io.*;

/**
 *  15:30 시작!
 */
public class Main
{
    /**
     *  1 2 3 4 5
     *  2 4 6 8 10
     *  3 6 9 12 15
     *  4 8 12 16 20
     *  5 10 15 20 25
     * 
     * O(N^2) ~= 10_000_000_000 시간 초과! 애초에 다 채우는 것을 할 수 없음!
     * 부분적으로 정렬이 되어있다는 것이 핵심! (정렬? 이분탐색?)
     * Sol. 1 ~ n^2을 이분 탐색해서 정답(x)을 구하자. 
     *      K번째 수란, x 보다 작거나 같은 수가 최소 K개 있다는 의미이다.
     */
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    int k = Integer.parseInt(bf.readLine());
	    
	    long left = 1;
	    long right = k; // right를 n^2으로 잡지 않아도 됨. B[k] = x일 때, x는 k보다 작거나 같다.
	    while(left <= right) {
	        long mid = left + (right - left) / 2;
	        
	        long countLessOrSame = 0; // mid보다 작거나 같은 수의 갯수
	        int countOfSame = 0;
	        for(int row = 1; row <= n; ++row) {
	            countLessOrSame += Math.min(mid / row, n);
	            if(mid % row == 0 && mid / row <= n) countOfSame++;
	        }
	        
	        if(countLessOrSame < k) {
	            left = mid + 1;
	        } else if(countLessOrSame - countOfSame >= k) {
	            right = mid - 1;
	        } else {
	            System.out.println(mid);
	            break;
	        }
	    }
	}
}