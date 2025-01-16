import java.io.*;
import java.util.*;

// 14:22 시작!
public class Main
{
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    long n = Long.parseLong(bf.readLine());
	    
	    // 4를 맞출 수 있음
	    // 상대가 1을 내면 내가 3을 낸다
	    // 상대가 3을 내면 내가 1을 낸다
	    // 입력값이 4로 나눴을 때 
	    // 나머지가 0 (상대가 3 또는 1을 남겨 줌 - 승리) 
	    // 나머지가 1 (상대가 4 또는 2을 남겨 줌 - 패배)
	    // 나머지가 2 (상태가 5 또는 3을 남겨 줌 - 승리) 
	    // 나머지가 3 (상태가 6 또는 4을 남겨 줌 - 패배) 

	    if(n % 4 == 0 || n % 4 == 2) System.out.println("CY");
	    else System.out.println("SK");
	}
}