import java.io.*;
import java.util.*;

public class Main
{
    // O(n^2) ~= 25_000_000
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    String string = bf.readLine();
	    int n = string.length();
	    int answer = 0;
        for(int i = 0; i < n; ++i) {
            String sub = string.substring(i); // i부터 끝까지 부분 문자열
            int[] pi = new int[sub.length()]; // pi[i]: 0 ~ i까지의 부분 문자열 중 '접두사==접미사'가 되는 가장 긴 접두사의 길이
            int max = 0;
            int start = 0;
            for(int end = 1; end < sub.length(); ++end) {
                while(start > 0 && sub.charAt(start) != sub.charAt(end)) {
                    start = pi[start - 1];
                }
                
                if(sub.charAt(start) == sub.charAt(end)) { // 정답이 될지도?
                    pi[end] = ++start;
                    max = Math.max(max, pi[end]);
                }
            }
            
            answer = Math.max(answer, max);
        }
        
        System.out.println(answer);
	}
}