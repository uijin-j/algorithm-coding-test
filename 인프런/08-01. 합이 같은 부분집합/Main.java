import java.util.*;

public class Main {
  static int n;
  static int[] nums;
  static int total;
  static String answer = "NO";
  static boolean flag = false;
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    nums = new int[n];
    for(int i = 0; i < n; ++i) {
      nums[i] = sc.nextInt();
      total += nums[i];
    }
    
    dfs(0, 0);
    
    System.out.println(answer);
  }
  
  public static void dfs(int l, int sum) {
    if(flag) return;
  	if(l == n) {
      if(total - sum == sum) {
        answer = "YES";
        flag = true;
      }
        return;
    }
    if(sum > total/2) return;
    
    dfs(l+1, sum + nums[l]);
    dfs(l+1, sum);
  }
}
