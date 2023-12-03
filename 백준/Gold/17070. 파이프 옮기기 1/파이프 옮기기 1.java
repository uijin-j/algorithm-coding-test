import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {
    static int n, answer;
    static int[][] house;

    public static enum PipeType {
        HORIZONTAL((point) -> new int[]{point[0], point[1] + 1}),
        VERTICAL((point) -> new int[]{point[0] + 1, point[1]}), 
        DIAGONAL((point) -> new int[]{point[0] + 1, point[1] + 1});

        private Function<int[], int[]> put;

        PipeType(Function<int[], int[]> put) {
            this.put = put;
        }

        int[] put(int x, int y) {
            return put.apply(new int[]{x, y});
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        house = new int[n+1][n+1];

        for(int i = 1; i <= n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; ++j) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 2, PipeType.HORIZONTAL);

        System.out.println(answer);
    }

    public static void dfs(int x, int y, PipeType type) {
        if(x == n && y == n) {
            answer++;
            return;
        }

        for(PipeType next: getPossibleNext(type)) {
            int[] point = next.put(x, y);
            int nx = point[0];
            int ny = point[1];

            if(nx > 0 && nx <= n && ny > 0 && ny <= n && house[nx][ny] != 1) {
                if(next == PipeType.DIAGONAL) {
                    if(house[nx-1][ny] == 1 || house[nx][ny-1] == 1) {
                        continue;
                    }
                }

                dfs(nx, ny, next);
            }
        }
    }

    public static List<PipeType> getPossibleNext(PipeType prev) {
        if(prev == PipeType.HORIZONTAL) {
            return List.of(PipeType.HORIZONTAL, PipeType.DIAGONAL);
        }
        if(prev == PipeType.VERTICAL) {
            return List.of(PipeType.VERTICAL, PipeType.DIAGONAL);
        }
        return List.of(PipeType.HORIZONTAL, PipeType.VERTICAL, PipeType.DIAGONAL);
    }
}
