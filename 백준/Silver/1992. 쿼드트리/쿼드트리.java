import java.io.*;
import java.util.*;

public class Main
{
	static int[][] video;
	static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		video = new int[n][n];
		for(int i = 0; i < n; ++i) {
			String s = bf.readLine();
			for(int j = 0; j < n; ++j) {
				video[i][j] = s.charAt(j) - '0';
			}
		}

		System.out.println(rec(0, 0, n));
	}

	public static String rec(int x, int y, int len) {
		int color = video[x][y];
		boolean flag = false;
		for(int i = x; i < x + len; ++i) {
			for(int j = y; j < y + len; ++j) {
				if(video[i][j] != color) { 
					flag = true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		if(flag) { // 4등분
			sb.append("(");
			sb.append(rec(x, y, len / 2));
			sb.append(rec(x, y + len / 2, len / 2));
			sb.append(rec(x + len / 2, y, len / 2));
			sb.append(rec(x + len / 2, y + len / 2, len / 2));
			sb.append(")");
		} else {
			sb.append(color);
		}

		return sb.toString();
	}

}
