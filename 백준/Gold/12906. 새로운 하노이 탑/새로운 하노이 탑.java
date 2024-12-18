import java.util.*;
import java.io.*;

public class Main {
	//하노이 상태 관련 클래스
    static class hanoiInfo{
        int count = 0;		//원판 이동 횟수
        Stack<Character> A = new Stack<>();		//막대 A
        Stack<Character> B = new Stack<>();		//막대 B
        Stack<Character> C = new Stack<>();		//막대 C
        //해당 막대 비어있는지 확인
        public boolean emptyCheck(int index){
            if(index==0){
                return A.isEmpty();
            }else if(index==1){
                return B.isEmpty();
            }else{
                return C.isEmpty();
            }
        }
        //막대기 상태 복사하기
        public void stackClone(Stack<Character> stack1, Stack<Character> stack2, Stack<Character> stack3){
            A = stack1;
            B = stack2;
            C = stack3;
        }
        //막대기에 원판 저장하기
        public void stackAdd(int index, Character stencil){
            if (index==0)
                A.add(stencil);
            else if(index==1)
                B.add(stencil);
            else
                C.add(stencil);
        }
        //막대기에 원판 빼기
        public Character stackPop(int index){
            if(index==0)
                return A.pop();
            else if(index==1)
                return B.pop();
            else
                return C.pop();
        }
        //현재 막대기 상태 코드 구하기
        public String getState(){
            String state = "";
            state += SetStencil(A);
            state += SetStencil(B);
            state += SetStencil(C);
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
    static hanoiInfo basic = new hanoiInfo();	//초기 막대기 상태
    //초기 원판 A,B,C 개수, 최소 이동 횟수 저장 변수
    static int aCount, bCount, cCount, answer;
    static String answerCode;	//정답이 되는 상태 코드 저장 변수
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //초기 하노이 상태 저장
        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine()," ");
            int n = Integer.parseInt(st.nextToken());
            if(n==0)
                continue;
            String stencil = st.nextToken();
            for(int j=0;j<n;j++){
                char temp = stencil.charAt(j);
                if(temp=='A')	//원판 A일 때
                    aCount++;
                else if(temp =='B')	//원판 B일 때
                    bCount++;
                else if(temp == 'C')	//원판 C일 때
                    cCount++;
                basic.stackAdd(i, temp);	//막대기에 원판 저장하기
            }
        }
        visited.add(basic.getState());	//초기 상태 코드 HashSet 저장
        answerCode = getAnswerCode();	//정답이 되는 상태 코드 구하기
        answer = gameStart();	//하노이 게임 시작!
        bw.write(answer + "");	//최소 이동 횟수 BufferedWriter 저장
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //BFS탐색으로 하노이 게임을 진행하는 함수
    static int gameStart(){
        Queue<hanoiInfo> queue = new LinkedList<>();
        queue.add(basic);	//초기 상태 Queue 저장
        while(!queue.isEmpty()){
            hanoiInfo cur = queue.poll();
            if(cur.getState().equals(answerCode))	//게임 목표 도달!
                return cur.count;
            for(int i=0;i<3;i++){
                if(!cur.emptyCheck(i)){
                    for(int j=0;j<3;j++){
                        if(i!=j){
                            //원판 이동하기
                            char temp = cur.stackPop(i);
                            cur.stackAdd(j,temp);
                            String state = cur.getState();
                            if(!visited.contains(state)){	//방문하지 않았던 상태 코드일 때
                                visited.add(state);
                                hanoiInfo tempHanoi = new hanoiInfo();
                                tempHanoi.stackClone((Stack<Character>) cur.A.clone(), (Stack<Character>) cur.B.clone(), (Stack<Character>) cur.C.clone());
                                tempHanoi.count = cur.count + 1;
                                queue.add(tempHanoi);
                            }
                            //원판 되돌리기
                            cur.stackAdd(i, temp);
                            cur.stackPop(j);
                        }
                    }
                }
            }
        }
        return -1;
    }
    //정답이 되는 상태 코드 구하는 함수
    static String getAnswerCode(){
        String result =
                "A".repeat(aCount) + " " +
                "B".repeat(bCount) + " " +
                "C".repeat(cCount) + " ";
        return result;
    }
}