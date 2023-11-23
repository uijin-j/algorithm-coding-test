import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String string = sc.nextLine();
    String bomb = sc.nextLine();

    int len = bomb.length();
    char last = bomb.charAt(len - 1);

    // replace() 사용 후 while ?
    // 스택?

    Stack<Character> stack = new Stack<>();
    for(char c: string.toCharArray()) {
      if(c == last) {
        int size = stack.size();
        if(size < len - 1) {
          stack.add(c);
          continue;
        }

        boolean flag = true;
        for(int i = len - 2, j = 1; i >= 0; --i, ++j) {
          if(bomb.charAt(i) != stack.elementAt(size - j)) {
            flag = false;
            break;
          }
        }

        if(flag) {
          for(int i = 0; i < len - 1; ++i) stack.pop();
          continue;
        }
      }

      stack.add(c);
    }

    if(stack.isEmpty()) {
      System.out.println("FRULA");
      return;
    }

    StringBuilder sb = new StringBuilder();
    while(!stack.isEmpty()) {
      sb.append(stack.pop().toString());
    }

    System.out.println(sb.reverse().toString());
  }
}
