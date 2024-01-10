import java.io.*;
import java.util.*;

public class Main {
    private static final int INF = 100;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        
        // 플로이드-와샬 -> 50^3 = 25*5000
        
        int[][] friends = new int[n+1][n+1];
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= n; ++j) {
                friends[i][j] = INF;
            }
        }

        while(true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());

            if(f1 == -1 && f2 == -1) break;
            
            friends[f1][f2] = 1;
            friends[f2][f1] = 1;
        }

        for(int k = 1; k <= n; ++k) {
            for(int i = 1; i <= n; ++i) {
                for(int j = 1; j <= n; ++j) {
                    friends[i][j] = Math.min(friends[i][j], friends[i][k] + friends[k][j]);
                    if(i == j) {
                        friends[i][j] = 0;
                    }
                }
            }
        }

        int[] members = new int[n+1];
        int score = INF;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; ++i) {
            int max = 0;
            for(int j = 1; j <= n; ++j) max = Math.max(max, friends[i][j]);
            members[i] = max;

            if(score > max) {
                score = max;
                count = 0;
                sb = new StringBuilder();
            } 

            if(score == max) {
                count++;
                sb.append(i).append(" ");
            }
        }

        System.out.println(score + " " + count);
        System.out.println(sb.toString());
	}
}
