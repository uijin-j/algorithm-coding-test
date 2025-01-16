import java.io.*;
import java.util.*;

// 13:52 시작!
public class Main
{
    /**
     * 사라지는 발판 문제와 비슷한 문제
     */
    static boolean[] impossible = new boolean[101];
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    
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
	        if(impossible[box[i]]) continue;
	        for(int first = 1; first < box[i] / 2 + 1; ++first) {
	            if(!play(first, box[i] - first)) {
	                return true;
	            }
	        }
	        
	        impossible[box[i]] = true;
	    }
	    
	    return false;
	}
}