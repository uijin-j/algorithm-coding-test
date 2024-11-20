import java.io.*;
import java.util.*;

// 15:04 시작!
public class Main
{
    /**
     * 재귀를 사용해야 할 것 같은데..
     */
    static int[] pre, in;
    static int preIdx;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(bf.readLine());
	    while(t > 0) {
	        int n = Integer.parseInt(bf.readLine());
	        pre = new int[n];
	        in = new int[n];
	        StringTokenizer prest = new StringTokenizer(bf.readLine());
	        StringTokenizer inst = new StringTokenizer(bf.readLine());
	        for(int i = 0; i < n; ++i) {
	            pre[i] = Integer.parseInt(prest.nextToken());
	            in[i] = Integer.parseInt(inst.nextToken());
	        }
	        
	        preIdx = 0;
	        Node root = makeTree(0, n);

	        postOrder(root);
	        sb.append("\n");
	        
	        t--;
	    }
	    
	    System.out.print(sb);
	}
	
	public static Node makeTree(int s, int e) {
	    if(s == e) return null;
	    
	    int num = pre[preIdx++];
	    Node node = new Node(num);
	    for(int i = s; i < e; ++i) {
	        if(in[i] == num) {
	            node.left = makeTree(s, i);
	            node.right = makeTree(i+1, e);
	            break;
	        }
	    }
	    
	    return node;
	}
	
	public static void postOrder(Node node) {
	    // 왼-오-루트
	    if(node.left != null) postOrder(node.left);
	    if(node.right != null) postOrder(node.right);
	    sb.append(node.num).append(" ");
	}
	
	public static class Node {
	    int num;
	    Node left, right;
	    
	    public Node(int num) {
	        this.num = num;
	    }
	}
}