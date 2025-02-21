import java.util.*;

class Solution {
    int n, half, max;
    int[] answer;
    boolean[] selected;
    public int[] solution(int[][] dice) {
        n = dice.length;
        half = n / 2;
        
        // 1. n개의 주사위 중 half개를 선택 O(nCn/2) ~= O(10C5) OK 
        // 2. 고른 주사위를 굴려서 나올 수 있는 점수 계산 O(2*6^n/2) ~= O(6^5) OK
        // 3. 이기는 경우를 계산  6^n = O(6^10) ~= 1억 NOT OK 
        answer = new int[half];
        selected = new boolean[n];
        select(0, 0, half, dice);
        
        for(int i = 0; i < half; ++i) {
            answer[i]++;
        }
        
        return answer;
    }
    
    public void select(int start, int level, int goal, int[][] dice) {
        if(level == goal) {
            List<Integer> firstPosible = new ArrayList<>();
            List<Integer> secondPosible = new ArrayList<>();
            
            int[] first = new int[half];
            int[] second = new int[half];
            int idx1 = 0, idx2 = 0;
            for(int i = 0; i < n; ++i) {
                if(selected[i]) first[idx1++] = i;
                else second[idx2++] = i;
            }
            
            dfs(0, half, first, 0, firstPosible, dice);
            dfs(0, half, second, 0, secondPosible, dice);
            
            secondPosible.sort((a, b) -> a - b);
            
            int count = 0;
            for(int score : firstPosible) {
                count += countLessThan(score, secondPosible);
            }
            
            if(count > max) {
                max = count;
                answer = first;
            }
            
            return;
        }
        
        for(int i = start; i < n; ++i) {
            selected[i] = true;
            select(i+1, level+1, goal, dice);
            selected[i] = false;
        }
    }
    
    public void dfs(int level, int goal, int[] arr, int sum, List<Integer> result, int[][] dice) {
        if(level == goal) {
            result.add(sum);
            return;
        }
        
        for(int i = 0; i < 6; ++i) {
            dfs(level+1, goal, arr, sum + dice[arr[level]][i], result, dice);
        }
    }
    
    public int countLessThan(int target, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        
        if(list.get(left) > target) return 0;
        if(list.get(right) < target) return list.size();
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(list.get(mid) >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return left; 
    }
}