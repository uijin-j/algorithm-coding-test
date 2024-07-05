import java.util.*;

class Solution {
    /**
     * 최댓값부터 2까지 내려가면서 정답이 될 수 있는지 check! 찾으면 바로 return => 시간초과
     * 풀이 봄 => 최대 공약수를 각각 구한 뒤, 상대 배열을 나눌 수 있는지 check (근데, 모든 공약수를 구해야 하지 않나?..)
     */
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdOfA = arrayA[0]; 
        int gcdOfB = arrayB[0];
        
        for(int i = 1; i < arrayA.length; ++i) {
            gcdOfA = getGCD(gcdOfA, arrayA[i]);
            gcdOfB = getGCD(gcdOfB, arrayB[i]);
        }
        
        int answer = 0;
        boolean canDivide = false;
        for(int num : arrayB) {
            if(num % gcdOfA == 0) {
                canDivide = true;
                break;
            }
        }
        
        if(!canDivide) { 
            answer = gcdOfA;
        }
        
        canDivide = false;
        for(int num : arrayA) {
            if(num % gcdOfB == 0) {
                canDivide = true;
                break;
            }
        }
        
        if(!canDivide) { 
            answer = Math.max(answer, gcdOfB);
        }
        
        return answer;
    }
    
    public int getGCD(int a, int b) {
        if(b == 0) return a;
        return getGCD(b, a % b);
    }
}