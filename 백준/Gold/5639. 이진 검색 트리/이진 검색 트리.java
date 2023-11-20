import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static class Node {
    int value;
    Node left, right;

    public Node(int value) {
      this.value = value;
    }

    void insert(int n) {
      if (n < this.value) {
          if (this.left == null) this.left = new Node(n);
          else this.left.insert(n);
      } else {
          if (this.right == null) this.right = new Node(n);
          else this.right.insert(n);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Node root = new Node(Integer.parseInt(br.readLine()));
    while (true) {
      String input = br.readLine();
      if (input == null || input.equals("")) break;
      root.insert(Integer.parseInt(input));
    }

    search(root);
  }

  static void search(Node node) {
    if(node == null) return;

    search(node.left);
    search(node.right);
    System.out.println(node.value);
  }
}
