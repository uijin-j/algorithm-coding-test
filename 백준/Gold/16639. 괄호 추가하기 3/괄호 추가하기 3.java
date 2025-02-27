import java.io.*;
import java.util.*;

// 15:50 시작!
public class Main
{
    /**
     *  괄호를 추가해서 얻을 수 있는 최댓값
     */
    static int n;
    static int[][] min, max;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    min = new int[n][n];
	    max = new int[n][n];
	    for(int i = 0; i < n; ++i) {
	        Arrays.fill(min[i], Integer.MAX_VALUE);
	        Arrays.fill(max[i], Integer.MIN_VALUE);
	    }
	    
	    char[] exp = bf.readLine().toCharArray();
	    for(int i = 0; i < n; i += 2) {
	        min[i][i] = exp[i] - '0';
	        max[i][i] = exp[i] - '0';
	    }
	    
	    for(int j = 2; j < n; j += 2) { // i부터 떨어진 거리 (좁은 구간부터 확인해야 하기 때문)
	        for(int i = 0; i + j < n; i += 2) { // 범위의 시작
	            for(int k = 1; k < j; k += 2) { // 중간에 자를 연산자 위치
	                char op = exp[i + k];
	                int[] result = new int[4];
	                result[0] = cal(max[i][i+k-1], max[i+k+1][i+j], op);
	                result[1] = cal(max[i][i+k-1], min[i+k+1][i+j], op);
	                result[2] = cal(min[i][i+k-1], max[i+k+1][i+j], op);
	                result[3] = cal(min[i][i+k-1], min[i+k+1][i+j], op);
	                
	                Arrays.sort(result);
	                
	                max[i][i+j] = Math.max(max[i][i+j], result[3]);
	                min[i][i+j] = Math.min(min[i][i+j], result[0]);
	            }
	        }
	    }
	    
	    System.out.println(max[0][n-1]);
	}
	
	static int cal(int left, int right, char op) {
	    if(op == '+') return left + right;
	    if(op == '-') return left - right;
	    return left * right;
	}
}