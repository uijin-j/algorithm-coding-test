import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 0에 가장 가까운 두 수의 합 구하기

        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] values = new int[n];
        for(int i = 0; i < n; ++i) 
            values[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(values);
        int left = 0; 
        int right = n - 1;

        int candi = Integer.MAX_VALUE;
        int answerL = left, answerR = right;
        while(left < right) {
            int sum = values[left] + values[right];
            if(Math.abs(sum) < candi) {
                candi = Math.abs(sum);
                answerL = left;
                answerR = right;
            }

            if(sum == 0) break;

            if(sum > 0) --right;
            else ++left;
        }

        System.out.println(values[answerL] + " " + values[answerR]);
    }
}
