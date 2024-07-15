class Solution {
    public int[] solution(int n) {
        int total = (n + 1) * n / 2;
        int initalN = n;
        int[] answer = new int[total];
        int[][] triangle = new int[n+1][n+1];
        
        int[] point = new int[]{ 1, 1 };
        int number = 1;
        while(n > 0) {
            fill(point, number, n, triangle);   
            
            point[0] += 2;
            point[1] = point[0] / 2 + 1;
            number += n * 3 - 3;
            n = n - 3;
        }
        
        int idx = 0;
        for(int i = 1; i <= initalN; ++i) {
            for(int j = 1; j <= i; ++j) {
                answer[idx++] = triangle[i][j];
            }
        }
        
        return answer;
    }
    
    public void fill(int[] point, int number, int n, int[][] triangle) {
        int x = point[0];
        int y = point[1];
        
        for(int i = 0; i < n; ++i) {
            triangle[x++][y] = number++;
        }
        
        x--;
        
        for(int i = 0 ; i < n -1; ++i) {
            triangle[x][++y] = number++;
        }
        
        for(int i = 0; i < n - 2; ++i) {
            triangle[--x][--y] = number++;
        }
    }
    
    
}