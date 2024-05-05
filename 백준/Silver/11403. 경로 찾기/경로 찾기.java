import java.io.*;
import java.util.*;

public class Main
{
	// 모든 정점 -> 모든 정점 => 플로이드-와샬
	static int MAX_VALUE = 1_000;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		int[][] dist = new int[n][n];

		StringTokenizer st;
		for(int i = 0; i < n; ++i) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < n; ++j) {
				dist[i][j] = (Integer.parseInt(st.nextToken()) == 1) ? 1 : MAX_VALUE;
			}
		}

		for(int k = 0; k < n; ++k) {
			for(int i = 0; i < n; ++i) {
				for(int j = 0; j < n; ++j) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}	

		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				if(dist[i][j] < MAX_VALUE) {
					System.out.print("1 ");
					continue;
				}
				System.out.print("0 ");
			}
			System.out.println();
		}
	}
}
