import java.util.*;

// 22:47 시작!
class Solution {
    /**
     * 1. 트리를 구성...
     *  - 루트 노드는 y값이 가장 크다. (y값을 내림차순 정렬한 뒤 탐색하면 루트부터 내려올 수 있음)
     *  - 자신보다 x값이 작으면 왼쪽 서브트리, x값이 크면 오른쪽 서브트리 (범위를 지정해서 재귀로 탐색하면 될듯?!)  
     * 2. 전/후위 순회 O(10_000)
     */
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;

        // 1. 트리를 구성
        List<Node> descY = new ArrayList<>();
        List<Node> ascX = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            Node node = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
            descY.add(node);
            ascX.add(node);
        }

        descY.sort((a, b) -> b.y - a.y == 0 ? a.x - b.x : b.y - a.y);
        ascX.sort((a, b) -> a.x - b.x);

        Node root = descY.get(0);
        descY.remove(0);
        buildTree(root, descY);

        // 2. 전/후위 순회
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        root.preorder(pre);
        root.postorder(post);
        
        int[][] answer = new int[2][n];
        for(int i = 0; i < n; ++i) {
            if(i < pre.size()) answer[0][i] = pre.get(i);
            if(i < post.size()) answer[1][i] = post.get(i);
        }

        return answer;
    }
    
    public class Node {
        int num, x, y;
        Node left, right;
        
        public Node(int num, int x, int y) {
            this.num =num;
            this.x = x;
            this.y = y;
        }
        
        public void preorder(List<Integer> path) {
            path.add(num);
            if(left != null) left.preorder(path);
            if(right != null) right.preorder(path);
        }
        
        public void postorder(List<Integer> path) {
            if(left != null) left.postorder(path);
            if(right != null) right.postorder(path);
            path.add(num);
        }
    }
    
    public void buildTree(Node root, List<Node> descY) {
        if (descY.size() == 0) return;
        
        List<Node> left = new ArrayList<>();
        List<Node> right = new ArrayList<>();
        int idx = 0;
        while(idx < descY.size()) {
            Node node = descY.get(idx++);
            if(node.x < root.x) left.add(node);
            else right.add(node);
        }
        
        Node child = descY.get(0);
        if (child.x < root.x) {
            root.left = child;
            left.remove(0);
            buildTree(root.left, left);
            
            if (descY.size() >= 2 && descY.get(1).y == child.y) {
                root.right = descY.get(1);
                right.remove(0);
                buildTree(root.right, right);
            }

            return;
        }
        
        root.right = child;
        right.remove(0);
        buildTree(root.right, right);
    }
}