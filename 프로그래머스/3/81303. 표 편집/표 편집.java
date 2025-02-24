import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        // C를 빠르게 처리하기 위해서는 연결리스트가 Good
        // Z를 빠르게 처리하기 위해서는 삭제 내역을 관리 Stack + 연결리스트가 Good
        // 연결 리스트를 쓰더라도 U/D에서 모든 이동 거리의 합이 1_000_000이하인 경우만 주어지므로 OK
        Node pre = new Node(null, null, 0);
        Node cursor = pre;
        for(int i = 1; i < n; ++i) {
            Node node = new Node(pre, null, i);
            pre.next = node;
            pre = node;
            
            if(i == k) cursor = node;
        }
        
        Deque<Node> stack = new ArrayDeque<>();
        for(String command : cmd) { // O(200_000)
            String[] info = command.split(" ");
            String type = info[0];
            
            if(type.equals("U")) {
                int x = Integer.parseInt(info[1]);
                for(int i = 0; i < x; ++i) cursor = cursor.pre;
            }
            
            if(type.equals("D")) {
                int x = Integer.parseInt(info[1]);
                for(int i = 0; i < x; ++i) cursor = cursor.next;
            }
            
            if(type.equals("C")) {
                stack.push(cursor);
                if(cursor.pre != null) cursor.pre.next = cursor.next;
                if(cursor.next != null) cursor.next.pre = cursor.pre;
                cursor = (cursor.next) == null ? cursor.pre : cursor.next;
            }
            
            if(type.equals("Z")) {
                Node poped = stack.pop();
                if(poped.pre != null) poped.pre.next = poped;
                if(poped.next != null) poped.next.pre = poped;
            }
        }
        
        boolean[] delete = new boolean[n];
        while(!stack.isEmpty()) {
            delete[stack.pop().num] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            sb.append(delete[i] ? "X" : "O");
        }
        
        return sb.toString();
    }
    
    public class Node {
        Node pre, next;
        int num;
        
        public Node(Node pre, Node next, int num) {
            this.pre = pre;
            this.next = next;
            this.num = num;
        }
    }
}