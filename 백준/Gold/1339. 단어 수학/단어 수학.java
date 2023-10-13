import java.util.*;

public class Main {
  public static void main(String[] args) {
    Map<Character, Integer> map = new HashMap<>();
    int N, answer = 0;
    Scanner sc = new Scanner(System.in);

    N = Integer.parseInt(sc.nextLine());
    for(int i = 0; i < N; ++i) {
      String s = sc.nextLine();
      for(int j = s.length() - 1, k = 0; j >= 0; --j, k++) {
        map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + (int) Math.pow(10, k));
      }
    }
    
    List<Integer> values = new ArrayList<>(map.values());
    Collections.sort(values, (a, b) -> b - a);

    int num = 9;
    for(int v : values) {
      answer += v * num--;
    }
  
    System.out.println(answer);
  }
}