import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main
{
    static class Student {
        int num, votes, offeredAt;

        Student(int num, int votes, int offeredAt) {
            this.num = num;
            this.votes = votes;
            this.offeredAt = offeredAt;
        }
    }
    
    // 사진틀
    // 사진틀에서 추천수가 가장 적은 학생 -> 먼저 추천받은 학생
    // 추천받으면 추천수++
    // 삭제 시 추천 수 0

	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int k = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        List<Student> students = new ArrayList<>();
        for(int i = 0; i <= 100; ++i) {
            students.add(new Student(i, 0, 0));
        }

        int sequence = 0;
        int count = 0; // 후보자 수
        for(int i = 0; i < k; ++i) {
            int num = Integer.parseInt(st.nextToken());
            Student student = students.get(num);
            if(student.votes != 0) {
                student.votes += 1;
                continue;
            }
    
            if(count >= n) {
                Student removed = students.stream()
                    .filter(s -> s.votes > 0)
                    .sorted((s1, s2) -> 
                        s1.votes - s2.votes == 0 ? s1.offeredAt - s2.offeredAt : s1.votes - s2.votes)
                    .collect(Collectors.toList())
                    .get(0);

                removed.votes = 0;
                removed.offeredAt = 0;
                count--;
            }

            student.votes = 1;
            student.offeredAt = ++sequence;
            count++;
        }

        for(int i = 1; i <= 100; ++i) {
            Student student = students.get(i);
            if(student.votes != 0) System.out.print(i + " ");
        }
    }
}
