import java.io.*;
import java.util.*;

public class Main
{
    public static class Node {
        int x, count;
        
        public Node(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[100_001];
        
        if(n == k) {
            System.out.println(0);
            return;
        }
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(n, 0));
        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node node = q.poll();
            visited[node.x] = true;
            if(node.x == k) min = Math.min(min, node.count);
            
            if(node.x != 100_000 && !visited[node.x + 1]) q.add(new Node(node.x + 1, node.count + 1));
            if(node.x != 0 &&!visited[node.x - 1]) q.add(new Node(node.x - 1, node.count + 1));
            if(node.x * 2 <= 100_000 && !visited[node.x * 2]) q.add(new Node(node.x * 2, node.count));
        }
        
        System.out.println(min);
    }
}
