import java.io.*;
import java.util.*;

public class Main
{
    static int[][][] path;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    String s1 = bf.readLine();
	    String s2 = bf.readLine();
	    int len1 = s1.length();
	    int len2 = s2.length();
	    
	    int[][] dp = new int[len1 + 1][len2 + 1]; // dp[i][j]는 s1.substring(0, i)와 s2.substring(0, j)일 때 LCS
	    path = new int[len1 + 1][len2 + 1][3];
	    for(int i = 1; i <= len1; ++i) {
	        for(int j = 1; j <= len2; ++j) {
	            if(s1.charAt(i-1) == s2.charAt(j-1)) {
	                dp[i][j] = dp[i-1][j-1] + 1;
	                path[i][j] = new int[]{ i-1, j-1, s1.charAt(i-1) };
	                continue;
	            }

                if(dp[i-1][j] >= dp[i][j-1]) {
                    dp[i][j] = dp[i-1][j];
                    path[i][j] = new int[]{ i-1, j, -1 };
                } else {
                    dp[i][j] = dp[i][j-1];
                    path[i][j] = new int[]{ i, j-1, -1 };
                }
	        }
	    }
	    
	    System.out.println(dp[len1][len2]);
	    System.out.println(find(len1, len2));
	}
	
	public static String find(int l1, int l2) {
	    if(l1 <= 0 || l2 <= 0) return "";
	    
	    int[] parent = path[l1][l2];
	    if(parent[2] == -1) {
	        return find(parent[0], parent[1]);
	    }
	    
	    return find(parent[0], parent[1]) + String.valueOf((char) parent[2]);
	}
}