import java.io.*;
import java.util.*;

// 13:52 시작! 14:10 끗(20분)
public class Main
{
    /**
     * 사라지는 발판 문제와 비슷한 문제
     */
    static int[] dp = new int[101];
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());

        Arrays.fill(dp, -1);
	    
	    if(play(n, m)) System.out.println("A");
	    else System.out.println("B");
	}
	
	// 상자에 n, m개의 돌이 들어 있을 때, 현재 플레이어가 이길 수 있는가?
	public static boolean play(int n, int m) {
	    if(n == 1 && m == 1) return false;
	    
	    int[] box = new int[]{n, m};
	    boolean win = false;
	    for(int i = 0; i < 2; ++i) { // i번 박스를 선택
	        if(box[i] < 2) continue;
	        if(dp[box[i]] == 0) continue;
            else if(dp[box[i]] == 1) return true;
	        for(int first = 1; first < box[i] / 2 + 1; ++first) {
	            if(!play(first, box[i] - first)) {
                    dp[box[i]] = 1;
	                return true;
	            }
	        }
	        
	        dp[box[i]] = 0;
	    }
	    
	    return false;
	}
}