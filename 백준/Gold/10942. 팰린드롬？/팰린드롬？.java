import java.util.*;
import java.io.*;
  
public class Main {
  // isPalindrome을 구할 때, 이전 값을 이용할 순 없을까?
  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(bf.readLine());
    boolean[][] isPalindrome = new boolean[n][n]; // isPalindrome[i][j]: i번째 수부터 j번째 수까지 펠린드롬인가?
    int[] nums = new int[n];
    StringTokenizer st = new StringTokenizer(bf.readLine());
    for(int i = 0; i < n; ++i) {
        nums[i] = Integer.parseInt(st.nextToken());
        isPalindrome[i][i] = true;
    }
    
    for(int i = 0; i < n - 1; ++i) { // 길이가 2인 펠린드롬인지
        if(nums[i] == nums[i+1]) {
            isPalindrome[i][i+1] = true;   
        }
    }
    
    for(int i = 3; i <= n; ++i) { // i가 펠린드롬의 길이
        for(int j = 0; j <= n - i; ++j) {
            if(isPalindrome[j+1][j+i-2] && nums[j] == nums[j+i-1]) {
                isPalindrome[j][j+i-1] = true;
            }
        }
    }
    
    int m = Integer.parseInt(bf.readLine());
    int[] answers = new int[m];
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < m; ++i) {
        st = new StringTokenizer(bf.readLine());
        int from = Integer.parseInt(st.nextToken()) - 1;
        int to = Integer.parseInt(st.nextToken()) - 1;
        
        if(isPalindrome[from][to]) {
            sb.append("1\n");
        } else {
            sb.append("0\n");
        }
    }
    
    System.out.println(sb);
  }
}
