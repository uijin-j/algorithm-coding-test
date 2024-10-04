import java.util.*;

// 16:05 START 17:30 
// 
class Solution {
    /**
     * 무식한 방법 O(n^2c) ~= 10_000_000_000 시간 초과
     *
     * 시간을 줄일 수 있는 방법? 연산을 할 때 모든 칸을 돌지 않으면 됨!
     * ShiftRow의 경우에는 직접 원소를 옮기는 것이 아니라, 어떤 열의 순서만 알면됨! (큐 사용하기)
     * Rotate는 바깥쪽 행/열을 큐로 구현하면 O(1)로 가능함
     * 
     *   1  2  3  4
     *   5  6  7  8
     *   9 10 11 12
     *  13 14 15 16
     */
    public int[][] solution(int[][] rc, String[] operations) {
        int n = rc.length;
        int m = rc[0].length;
        
        System.out.println(n + " " + m);

        Deque<Integer> firstCol = new ArrayDeque<>();
        Deque<Integer> lastCol = new ArrayDeque<>();
        Deque<Deque<Integer>> rows = new ArrayDeque<>();
        for(int row = 0; row < n; ++row) {
            firstCol.offerLast(rc[row][0]);
            lastCol.offerLast(rc[row][m-1]);
            
            Deque<Integer> deque = new ArrayDeque<>(); 
            for(int col = 1; col < m - 1; ++col) deque.offerLast(rc[row][col]);
            rows.offerLast(deque);
        }
        
        for(int i = 0; i < operations.length; ++i) {
            String operation = operations[i];
            
            if(operation.equals("ShiftRow")) {
                rows.offerFirst(rows.pollLast());
                firstCol.offerFirst(firstCol.pollLast());
                lastCol.offerFirst(lastCol.pollLast());
            }
            
            if(operation.equals("Rotate")) {
                if (m == 2) {
                    lastCol.addFirst(firstCol.pollFirst());
                    firstCol.addLast(lastCol.pollLast());
                    continue;
                }
                
                Deque<Integer> firstRow = rows.peekFirst();
                Deque<Integer> lastRow = rows.peekLast();
                
                firstRow.addFirst(firstCol.pollFirst());
                lastCol.addFirst(firstRow.pollLast());
                lastRow.addLast(lastCol.pollLast());
                firstCol.addLast(lastRow.pollFirst());
            }
        }
        
        int[][] answer = new int[n][m];
        for(int row = 0; row < n; ++row) {
            answer[row][0] = firstCol.pollFirst();
            answer[row][m-1] = lastCol.pollFirst();
            
            Deque<Integer> deque = rows.pollFirst();
            for(int col = 1; col < m - 1; ++col) {
                answer[row][col] = deque.pollFirst();
            }
        }

        return answer;
    }
}