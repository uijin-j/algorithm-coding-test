import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    HashMap<Integer, List<Integer>> personList = new HashMap<>(); // personList.get(i)는 i번 파티에 참여자 명단
    for(int i = 0; i < m; ++i) personList.put(i, new ArrayList<>());
    HashMap<Integer, List<Integer>> partyList = new HashMap<>(); // partyList.get(i)는 i번 사람이 참여하는 파티 명단
    for(int i = 1; i <= n; ++i) partyList.put(i, new ArrayList<>());
    
    int cntOfKnow = sc.nextInt();
    int[] know = new int[cntOfKnow]; // 진실을 아는 사람들
    for(int i = 0; i< cntOfKnow; ++i) know[i] = sc.nextInt();
    
    for(int i = 0; i < m; ++i) {
        int cntOfPerson = sc.nextInt(); // 이 파티에 참여하는 사람 수
        List<Integer> list = personList.get(i);
        for(int j = 0; j < cntOfPerson; ++j) {
            int person = sc.nextInt();
            list.add(person);  // 파티 명단에 참가자 추가
            partyList.get(person).add(i); // 참가자의 파티 리스트에 파티 추가
        }
    }
    
    Queue<Integer> q = new LinkedList<>();
    boolean[] check = new boolean[m]; // check[i]는 i번 파티에서 진실을 말했는지
    for(int i = 0; i < cntOfKnow; ++i) {
        int person = know[i];
        for(int party: partyList.get(person)) { // 진실을 아는 사람들이 가는 파티를 큐에 넣어줌
            if(!check[party]) {
                check[party] = true;
                q.offer(party);   
            }
        }
    }
    
    while(!q.isEmpty()) {
        int party = q.poll();
        
        for(int person: personList.get(party)) { // 진실을 말한 파티의 참가자 조회
           for(int p: partyList.get(person)) { // 그 참가자들이 가는 파티를 조회
                if(!check[p]) { 
                    check[p] = true;
                    q.offer(p);   
                }
            }
        }
    }
    
    int answer = 0;
    for(int i = 0; i < m; ++i) {
        if(!check[i]) answer++;
    }
    
    System.out.println(answer);
  }
}
