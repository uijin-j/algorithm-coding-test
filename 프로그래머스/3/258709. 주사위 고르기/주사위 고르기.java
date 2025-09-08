import java.util.*;

/**
 * 22:30 시작
 * n개의 주사위 중 n/2개를 선택  O(nCn/2) = O(10C5) ~= O(280)
 * 주사위를 굴려서 나오는 경우의 수 6^10
 * 완탐으로 풀면 O(280*6^10) --> 시간 초과
 * 
 * 일단 주사위를 n/2개 뽑고, 해당 주사위를 굴려 나올 수 있는 경우의 수들을 계산 O(280*6^5) ~= O(28_000_000) OK
 * 280개의 경우를 확인하면서, 이길 확률을 계산 O(280 * 100_000 * 100) ~= O(2_800_000_000) 아슬아슬 (사실 140번만 해도되긴 함)
 */
class Solution {
    int n;
    boolean[] check;
    List<int[]> cases = new ArrayList<>();
    List<List<Integer>> results = new ArrayList<>();
    List<List<Integer>> ops = new ArrayList<>();
    public int[] solution(int[][] dice) {
        n = dice.length;
        check = new boolean[n];
        
        choice(0, 0, dice);
        
        for(List<Integer> op : ops) {
            op.sort((a, b) -> a - b);
        }
        
        int[] answer = new int[n/2];
        int max = 0;
        for(int i = 0; i < results.size(); ++i) {
            List<Integer> result = results.get(i);
            List<Integer> op = ops.get(i);
            
            int total = 0;
            for(int num : result) total += findLessThan(num, op);
            
            if(total > max) {
                max = total;
                answer = cases.get(i);
            }
        }
        
        for(int i = 0; i < n/2; ++i) answer[i] += 1;
        return answer;
    }
    
    public void choice(int level, int start, int[][] dice) {
        if(level == n/2) {
            // 주사위 선택 완료
            int[] selected = new int[n/2];
            int[] unselected = new int[n/2];
            int idx1 = 0, idx2 = 0;
            for(int i = 0; i < n; ++i) {
                if(check[i]) selected[idx1++] = i;
                else unselected[idx2++] = i;
            }
            
            List<Integer> cases1 = new ArrayList<>();
            List<Integer> cases2 = new ArrayList<>();
            dfs(0, 0, dice, selected, cases1);
            dfs(0, 0, dice, unselected, cases2);
            
            cases.add(selected);
            results.add(cases1);
            ops.add(cases2);
            return;
        }
        
        for(int i = start; i < n; ++i) {
            check[i] = true;
            choice(level+1, i+1, dice);
            check[i] = false;
        }
    }
    
    public void dfs(int level, int sum, int[][] dice, int[] arr, List<Integer> list) {
        if(level == arr.length) {
            list.add(sum);
            return;
        }
        
        for(int i = 0; i < 6; ++i) {
            dfs(level+1, sum + dice[arr[level]][i], dice, arr, list);
        } 
    }
    
    public int findLessThan(int target, List<Integer> list) {
        int left = 0;
        int right = list.size();
        while(left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
}