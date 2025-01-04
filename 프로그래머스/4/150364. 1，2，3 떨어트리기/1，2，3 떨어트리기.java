import java.util.*;

class Solution {
    int n;
    int[] count;
    Map<Integer, Node> map = new HashMap<>();
    List<Integer> path = new ArrayList<>();
    boolean impossible = false;
    public int[] solution(int[][] edges, int[] target) {
        n = edges.length + 1;
        List<Integer>[] tree = new List[n+1];
        for(int i = 0; i <= n; ++i) {
            tree[i] = new ArrayList<>();
        }
        
        for(int[] edge: edges) {
            tree[edge[0]].add(edge[1]);
        }
        
        for(int i = 1; i <= n; ++i) {
            tree[i].sort((a, b) -> a - b);
            map.put(i, new Node(i, tree[i]));
        }
        
        count = new int[n+1];
        Node root = map.get(1);
        while(!finish(target)) {
            root.go();
        }
        
        if(impossible) return new int[]{-1};
        
        int[] answer = new int[path.size()];
        for(int i = 0; i < path.size(); ++i) {
            int num = path.get(i);
            int need = Math.max(target[num-1] - (count[num] - 1) * 3, 1);
            answer[i] = need;
            target[num-1] -= need;
            count[num]--;
        }
        
        return answer;
    }
    
    class Node {
        int num;
        List<Integer> child;
        int idx;
        
        public Node(int num, List<Integer> child) {
            this.num = num;
            this.child = child;
            idx = 0;
        }
        
        public void go() {
            if(child.size() == 0) { // 리프노드
                count[num]++;
                path.add(num);
                return;
            }
            
            map.get(child.get(idx)).go();
            idx = (idx + 1) % child.size();
        }
    }
    
    public boolean finish(int[] target) {
        for(int i = 1; i <= n; ++i) {
            if(target[i-1] == 0) continue;
            
            if(count[i] > target[i-1]) {
                impossible = true;
                return true;
            }
            
            if((target[i-1] + 2) / 3 > count[i]) {
                return false;
            }
        }
        
        return true;
    }
}