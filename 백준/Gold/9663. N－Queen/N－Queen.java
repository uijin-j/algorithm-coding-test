import java.util.*;

public class Main {
    static int n, answer;
    static int[] selected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        selected = new int[n];

        dfs(0);

        System.out.println(answer);
    }

    public static void dfs(int depth) {
        if(depth == n) {
            answer++;
            return;
        }

        for(int i = 0; i < n; ++i) { // depth행에 i 열을 선택!
            selected[depth] = i;

            if(canSelect(depth)) {
                dfs(depth + 1);
            }
        }
    }

    public static boolean canSelect(int depth) {
        for(int i = 0; i < depth; ++i) {
            if(selected[i] == selected[depth]) {
                return false;
            }

            if(Math.abs(i - depth) == Math.abs(selected[i] - selected[depth])) {
                return false;
            }
        }
        
        return true;
    }
}
