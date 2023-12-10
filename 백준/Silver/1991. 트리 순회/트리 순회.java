import java.io.*;
import java.util.*;

public class Main {
    static Node[] nodes;
    static class Node {
        char node, left, right;

        public Node(char node, char left, char right) {
            this.node = node;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) throws Exception {
        // 전위, 중위, 후위 순회는 재귀 함수를 사용!

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        nodes = new Node[26];
        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            char node = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            nodes[node - 'A'] = new Node(node, left, right);
        }

        StringBuilder pre = new StringBuilder();
        StringBuilder in = new StringBuilder();
        StringBuilder post = new StringBuilder();

        preOrder(nodes[0], pre);
        inOrder(nodes[0], in);
        postOrder(nodes[0], post);

        System.out.println(pre.toString());
        System.out.println(in.toString());
        System.out.println(post.toString());
    }

    public static void preOrder(Node node, StringBuilder sb) {
        sb.append(node.node);

        if(node.left != '.') {
            preOrder(nodes[node.left - 'A'], sb);
        }

        if(node.right != '.') {
            preOrder(nodes[node.right - 'A'], sb);
        };
    }

    public static void inOrder(Node node, StringBuilder sb) {
        if(node.left != '.') {
            inOrder(nodes[node.left - 'A'], sb);
        }

        sb.append(node.node);

        if(node.right != '.') {
            inOrder(nodes[node.right - 'A'], sb);
        };
    }

    public static void postOrder(Node node, StringBuilder sb) {
        if(node.left != '.') {
            postOrder(nodes[node.left - 'A'], sb);
        }

        if(node.right != '.') {
            postOrder(nodes[node.right - 'A'], sb);
        };

        sb.append(node.node);
    }
}
