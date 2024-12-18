import java.util.*;
import java.io.*;

public class Main {
    static class Hanoi {
        int count = 0;		//원판 이동 횟수
        Stack<Character> stickA = new Stack<>();		//막대 A
        Stack<Character> stickB = new Stack<>();		//막대 B
        Stack<Character> stickC = new Stack<>();		//막대 C
        //해당 막대 비어있는지 확인
        public boolean emptyCheck(int index){
            if(index == 0){
                return stickA.isEmpty();
            } else if(index == 1){
                return stickB.isEmpty();
            } else{
                return stickC.isEmpty();
            }
        }
        //막대기 상태 복사하기
        public void stackClone(Stack<Character> stack1, Stack<Character> stack2, Stack<Character> stack3){
            stickA = stack1;
            stickB = stack2;
            stickC = stack3;
        }
        //막대기에 원판 저장하기
        public void stackAdd(int index, Character stencil){
            if (index==0)
                stickA.add(stencil);
            else if(index==1)
                stickB.add(stencil);
            else
                stickC.add(stencil);
        }
        //막대기에 원판 빼기
        public Character stackPop(int index){
            if(index==0)
                return stickA.pop();
            else if(index==1)
                return stickB.pop();
            else
                return stickC.pop();
        }
        //현재 막대기 상태 코드 구하기
        public String getState(){
            String state = "";
            state += SetStencil(stickA);
            state += SetStencil(stickB);
            state += SetStencil(stickC);
            return state;
        }
        //막대기 원판에 대한 정보 얻기
        public String SetStencil(Stack<Character> stack){
            StringBuilder state = new StringBuilder();
            for (Character character : stack)
                state.append(character);
            state.append(" ");
            return state.toString();
        }
    }
    static HashSet<String> visited = new HashSet<>();
    static Hanoi basic = new Hanoi();
    static int numOfA, numOfB, numOfC, answer;
    static String goal;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0;i<3;i++){
            st = new StringTokenizer(bf.readLine()," ");
            int n = Integer.parseInt(st.nextToken());
            if(n == 0) continue;
            String stencil = st.nextToken();
            for(char ch : stencil.toCharArray()){
                if(ch == 'A') numOfA++;
                else if(ch =='B') numOfB++;
                else numOfC++;
                basic.stackAdd(i, ch);
            }
        }

        goal = getAnswerCode();
        System.out.println(gameStart());
    }
    
    // BFS
    static int gameStart(){
        Queue<Hanoi> queue = new LinkedList<>();
        queue.add(basic);
        visited.add(basic.getState());
        while(!queue.isEmpty()){
            Hanoi cur = queue.poll();
            if(cur.getState().equals(goal)) return cur.count;
            
            for(int i = 0; i < 3; i++){
                if(!cur.emptyCheck(i)){
                    for(int j = 0; j < 3; j++){
                        if(i != j){
                            //원판 이동하기
                            char poped = cur.stackPop(i);
                            cur.stackAdd(j,poped);
                            String state = cur.getState();
                            if(!visited.contains(state)){
                                visited.add(state);
                                Hanoi tempHanoi = new Hanoi();
                                tempHanoi.stackClone((Stack<Character>) cur.stickA.clone(), (Stack<Character>) cur.stickB.clone(), (Stack<Character>) cur.stickC.clone());
                                tempHanoi.count = cur.count + 1;
                                queue.add(tempHanoi);
                            }
                            //원판 되돌리기
                            cur.stackAdd(i, poped);
                            cur.stackPop(j);
                        }
                    }
                }
            }
        }
        return -1;
    }

    static String getAnswerCode(){
        String result =
                "A".repeat(numOfA) + " " +
                "B".repeat(numOfB) + " " +
                "C".repeat(numOfC) + " ";
        return result;
    }
}