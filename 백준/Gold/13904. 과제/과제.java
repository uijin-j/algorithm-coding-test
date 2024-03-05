/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main
{
    public static class Assignment {
        int dday, score;

        Assignment(int dday, int score) {
            this.dday = dday;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        PriorityQueue<Assignment> assignments = new PriorityQueue<>((a, b) -> b.dday - a.dday);
        PriorityQueue<Assignment> pq = new PriorityQueue<>((a, b) -> b.score - a.score);
        StringTokenizer st;
        int today = 0;
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(bf.readLine());
            int dday = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            today = Math.max(today, dday);
            assignments.add(new Assignment(dday, score));
        }
        
        int total = 0;
        while(today > 0) {
            while(!assignments.isEmpty() && today <= assignments.peek().dday) {
                pq.add(assignments.poll());
            }
            
            if(!pq.isEmpty()) {
                Assignment assignment = pq.poll();
                total += assignment.score;  
            }
            --today;
        }

        System.out.println(total);
    }
}
