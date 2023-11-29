import java.util.*;

public class Main {
    static int[] parent;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] dx = {-m, 1, m, -1};
        
        parent = new int[n * m];
        for(int i = 0; i < n*m; ++i) {
            parent[i] = i;
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('U', 0);
        map.put('R', 1);
        map.put('D', 2);
        map.put('L', 3);

        sc.nextLine();
        for(int i = 0; i < n; ++i) {
            String s = sc.nextLine();
            char[] line = s.toCharArray();
            for(int j = 0; j < m; j++) {
                int dir = map.get(line[j]);
                int now = i * m + j;
                int next = now + dx[dir];
                
                parent[now] = find(next);
            }
        }

        boolean[] check = new boolean[n*m];
        int count = 0;
        for(int i = 0; i < n*m; ++i) {
            int p = find(i);
            if(!check[p]) {
                count++;
                check[p] = true;
            }
        }

        System.out.println(count);
    }
    
    public static int find(int node) {
        if(parent[node] == node) return node;
        return find(parent[node]);
    }
}
