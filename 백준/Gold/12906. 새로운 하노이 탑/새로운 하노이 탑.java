import java.util.*;
import java.io.*;

public class Main {
    static class Hanoi {
        int moveCount = 0;
        Stack<Character>[] sticks;

        public Hanoi() {
            this.sticks = new Stack[3];
            for(int i = 0; i < 3; ++i) sticks[i] = new Stack();
        }
        
        public boolean isEmpty(int index){
            return sticks[index].isEmpty();
        }

        public void stackClone(Stack<Character> stack1, Stack<Character> stack2, Stack<Character> stack3){
            sticks[0] = stack1;
            sticks[1] = stack2;
            sticks[2] = stack3;
        }

        public void put(int index, Character stencil){
            sticks[index].add(stencil);
        }

        public void move(int from, int to){
            sticks[to].push(sticks[from].pop());
        }

        public String toString(){
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < 3; ++i) {
                for (char ch : sticks[i]) {
                    result.append(ch);
                }
                
                result.append(" ");
            }
            
            return result.toString();
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
        visited.add(init.toString());
        
        while(!queue.isEmpty()) {
            Hanoi hanoi = queue.poll();
            if(hanoi.toString().equals(goal)) return hanoi.moveCount;
            
            for(int i = 0; i < 3; i++) {
                if(hanoi.isEmpty(i)) continue;
                
                for(int j = 0; j < 3; j++) {
                    if(i == j) continue;
                    
                    hanoi.move(i, j);
                    
                    String state = hanoi.toString();
                    if(!visited.contains(state)){
                        visited.add(state);
                        Hanoi tempHanoi = new Hanoi();
                        tempHanoi.stackClone((Stack<Character>) hanoi.sticks[0].clone(), (Stack<Character>) hanoi.sticks[1].clone(), (Stack<Character>) hanoi.sticks[2].clone());
                        tempHanoi.moveCount = hanoi.moveCount + 1;
                        queue.add(tempHanoi);
                    }

                    hanoi.move(j, i);
                }
            }
        }
        
        return -1;
    }
}