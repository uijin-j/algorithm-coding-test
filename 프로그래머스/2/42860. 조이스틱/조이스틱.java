// class Solution {
//     static int length;
//     public int solution(String name) {
//         int answer = Math.min(name.charAt(0) - 'A', 'Z' - name.charAt(0) + 1);
//         length = name.length();
//         boolean[] check = new boolean[length];
//         check[0] = true;
        
//         System.out.println("0번째: " + answer);
        
//         int now = 0;
//         int count = 0;
//         dfs(0);
//         while(true) { // 왼쪽이 있으면 무조건 왼쪽 최소 거리 부터
//             System.out.println("count: " + count);
//             boolean flag = true;

//             for(int i = 1; i < length; ++i) {
//                 // 오른쪽
//                 int next = nextR(now, i);
//                 if(!check[next] && name.charAt(next) != 'A') {
//                     System.out.print("오른쪽으로 " + i + "번 움직여서 " + next + "번째: ");
//                     answer += i;
//                     now = next;
//                     flag = false;
//                     check[next] = true;
//                     break;   
//                 }
                
//                 //왼쪽
//                 next = nextL(now, i);
//                 if(!check[next] && name.charAt(next) != 'A') {
//                     System.out.print("왼쪽으로 " + i + "번 움직여서 " + next + "번째: ");
//                     answer += i;
//                     now = next;
//                     flag = false;
//                     check[next] = true;
//                     break;   
//                 }
//             }
            
//             if(flag) break;
            
//             answer += Math.min(name.charAt(now) - 'A', 'Z' - name.charAt(now) + 1);
//             System.out.println(Math.min(name.charAt(now) - 'A', 'Z' - name.charAt(now)));
            
//             count++;
//             if(count == 100) break;
//         }
        
//         return answer;
//     }
    
//     void dfs(int now) {
//         if()
//     }
    
//     int nextR(int now, int step) { // 오른쪽으로 step칸 이동
//         if(now + step > length - 1) return now + step - length;
//         return now + step;
//     }
    
//     int nextL(int now, int step) { // 왼쪽으로 step칸 이동
//         if(now - step < 0) return length + (now - step);
//         return now - step;
//     }
// }

class Solution {
    static int length, step = Integer.MAX_VALUE;
    static boolean[] check;
    
    public int solution(String name) {
        length = name.length();
        check = new boolean[length];
        
        int answer = 0;
        for(int i = 0; i < length; ++i) {
            if(name.charAt(i) == 'A') check[i] = true;
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
        }
        
        check[0] = true;
        dfs(0, 0, name);
  
        return answer + step;
    }
    
    void dfs(int count, int now, String name) {
        if(count >= step) return;
        if(isAllCheck()) {
            step = Math.min(step, count);
            return;
        }
        
        for(int i = 1; i < length; ++i) {
            // 오른쪽
            int next = nextR(now, i);
            if(!check[next] && name.charAt(next) != 'A') {
                check[next] = true;
                dfs(count + i, next, name); 
                check[next] = false;
                break;
            }
        }
        
        for(int i = 1; i < length; ++i) {
            // 왼쪽
            int next = nextL(now, i);
            if(!check[next] && name.charAt(next) != 'A') {
                check[next] = true;
                dfs(count + i, next, name); 
                check[next] = false;
                break;
            }
        }
    }
    
    boolean isAllCheck() {
        for(boolean ch: check) {
            if(!ch) return false; 
        }
        
        return true;
    }
    
    int nextR(int now, int step) { // 오른쪽으로 step칸 이동
        if(now + step > length - 1) return now + step - length;
        return now + step;
    }
    
    int nextL(int now, int step) { // 왼쪽으로 step칸 이동
        if(now - step < 0) return length + (now - step);
        return now - step;
    }
}