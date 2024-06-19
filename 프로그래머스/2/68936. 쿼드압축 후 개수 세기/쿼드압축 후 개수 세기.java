class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        
        String result = compress(arr, 0, 0, arr.length); 
        for(char ch : result.toCharArray()) {
            if(ch == '0') answer[0]++;
            else answer[1]++;
        }
        
        return answer;
    }
    
    public String compress(int[][] arr, int x, int y, int n) {
        int num = arr[x][y];
        boolean canCompress = true;
        for(int i = x; i < x + n; ++i) {
            for(int j = y; j < y + n; ++j) {
                if(arr[i][j] != num) {
                    canCompress = false;
                    break;
                }
            }
            if(!canCompress) break;
        }
        
        if(canCompress) {
            return Integer.toString(num);
        }
        
        return compress(arr, x, y, n/2) + compress(arr, x, y + n/2, n/2) + compress(arr, x + n/2, y, n/2) + compress(arr, x + n/2, y + n/2, n/2);
    }
}