import java.util.*;

/**
 * 풀이 1) bfs로 가장 빨리 0층에 도착할 수 있는 방법을 찾기 => 한 번 눌러서 이동할 수 있는 경우가 너무 많음!! (시간초과) => O(m(이동할 수 있는 경우의 수)^2)
 * 풀이 2) 결국 10의 배수로 옮겨야 하지 않을까?.. => 그리디! (젤 뒤에서 부터 0을 맞추기 위한 가장 빠른 경로 찾기) => O(storey.length)
 * 주의 사항) 5인 경우에는 앞자리 수를 이용해 결정하기! (앞자리가 5이상이면 올리기! ex. 455)
 */
class Solution {
    // 그리디
    public int solution(int storey) {        
        int answer = 0;
        
        while(storey > 0) {
            int num = storey % 10;
            storey /= 10;
            
            if(num > 5) {
                answer += 10 - num;
                storey++;
            } else if(num < 5) {
                answer += num;
            } else {
                int next = storey % 10;
                if(next >= 5) {
                    answer += 10 - num;
                    storey++;
                } else {
                    answer += num;
                }
            }
        }
        
        return answer;
    }
}