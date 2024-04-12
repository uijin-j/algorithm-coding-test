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
        int[] visited = new int[100_001];
        
        if(n == k) {
            System.out.println(0);
            return;
        }
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(n, 1));
        visited[n] = 1;
        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            if(node.x != 100_000 && (visited[node.x + 1] == 0 || node.count + 1 < visited[node.x + 1])) {
                q.add(new Node(node.x + 1, node.count + 1));
                visited[node.x + 1] = node.count + 1;
                if(node.x + 1 == k) min = Math.min(min, node.count + 1);
            }
            
            if(node.x != 0 && (visited[node.x - 1] == 0 || node.count + 1 < visited[node.x - 1])) {
                q.add(new Node(node.x - 1, node.count + 1));
                visited[node.x - 1] = node.count + 1;
                if(node.x - 1 == k) min = Math.min(min, node.count + 1);
            }
            
            if(node.x * 2 <= 100_000 && (visited[node.x * 2] == 0 || node.count < visited[node.x * 2])) {
                q.add(new Node(node.x * 2, node.count));
                visited[node.x * 2] = node.count;
                if(node.x * 2 == k) min = Math.min(min, node.count);
            }
        }
        
        System.out.println(min - 1);
    }
}
