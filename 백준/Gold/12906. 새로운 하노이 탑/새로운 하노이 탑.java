import java.util.*;
import java.io.*;

public class Main {
    static class Hanoi {
        int moveCount = 0;
        Stack<Character> stickA = new Stack<>();
        Stack<Character> stickB = new Stack<>();
        Stack<Character> stickC = new Stack<>();

        public boolean isEmpty(int index){
            if(index == 0) return stickA.isEmpty();
            if(index == 1) return stickB.isEmpty();
            return stickC.isEmpty();
        }

        public void stackClone(Stack<Character> stack1, Stack<Character> stack2, Stack<Character> stack3){
            stickA = stack1;
            stickB = stack2;
            stickC = stack3;
        }

        public void put(int index, Character stencil){
            if (index == 0) stickA.add(stencil);
            else if(index == 1) stickB.add(stencil);
            else stickC.add(stencil);
        }

        public Character stackPop(int index){
            if(index == 0) return stickA.pop();
            if(index == 1) return stickB.pop();
            return stickC.pop();
        }

        public String getState(){
            String state = "";
            state += SetStencil(stickA);
            state += SetStencil(stickB);
            state += SetStencil(stickC);
            return state;
        }

        public String SetStencil(Stack<Character> stack){
            StringBuilder state = new StringBuilder();
            for (Character character : stack) state.append(character);
            state.append(" ");
            return state.toString();
        }
    }
    
    static HashSet<String> visited = new HashSet<>();
    static Hanoi init = new Hanoi();
    static int numOfA, numOfB, numOfC, answer;
    static String goal;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(bf.readLine());
            
            int n = Integer.parseInt(st.nextToken());
            if(n == 0) continue;
            
            String stencil = st.nextToken();
            for(char ch : stencil.toCharArray()){
                if(ch == 'A') numOfA++;
                else if(ch =='B') numOfB++;
                else numOfC++;
                
                init.put(i, ch);
            }
        }

        goal = "A".repeat(numOfA) + " " + "B".repeat(numOfB) + " " + "C".repeat(numOfC) + " ";
        System.out.println(bfs());
    }
    
    // BFS
    static int bfs(){
        Queue<Hanoi> queue = new LinkedList<>();
        queue.add(init);
        visited.add(init.getState());
        
        while(!queue.isEmpty()){
            Hanoi cur = queue.poll();
            if(cur.getState().equals(goal)) return cur.moveCount;
            
            for(int i = 0; i < 3; i++) {
                if(cur.isEmpty(i)) continue;
                
                for(int j = 0; j < 3; j++) {
                    if(i == j) continue;
                    
                    char poped = cur.stackPop(i);
                    cur.put(j, poped);
                    String state = cur.getState();
                    if(!visited.contains(state)){
                        visited.add(state);
                        Hanoi tempHanoi = new Hanoi();
                        tempHanoi.stackClone((Stack<Character>) cur.stickA.clone(), (Stack<Character>) cur.stickB.clone(), (Stack<Character>) cur.stickC.clone());
                        tempHanoi.moveCount = cur.moveCount + 1;
                        queue.add(tempHanoi);
                    }
                    //원판 되돌리기
                    cur.put(i, poped);
                    cur.stackPop(j);
                }
            }
        }
        
        return -1;
    }
}