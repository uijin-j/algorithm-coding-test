import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	
	final static long MOD = 1_000_000_007;
	public static long[][] origin = {{1, 1} , {1, 0}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[][] A = {{1, 1} , {1, 0}};
		
		
		long N = Long.parseLong(br.readLine());
		
		System.out.println(pow(A, N - 1)[0][0]);
	}
	
	public static long[][] pow(long[][] A, long exp) {
		if(exp == 1 || exp == 0) {
			return A;
		}
		
		long[][] ret = pow(A, exp / 2);
		
		ret = multiply(ret, ret);

		if(exp % 2 == 1L) {
			ret = multiply(ret, origin);
		}
		
		return ret;
	}
	
	
	// 행렬곱
	public static long[][] multiply(long[][] o1, long[][] o2) {
		long[][] ret = new long[2][2];
		
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 2; i++) {							
				for (int j = 0; j < 2; j++) {
					ret[i][j] += o1[i][k] * o2[k][j];
					ret[i][j] %= MOD;
				}
			}
		}
		
		return ret;
	}
 
}
