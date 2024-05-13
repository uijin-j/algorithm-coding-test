import java.io.*;
import java.util.*;

public class Main
{
    static Map<Integer, List<Integer>> map;
    static int[] cost;
    static int[] mins;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(t > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            cost = new int[n+1];
            mins = new int[n+1];
            map = new HashMap<>();
            for(int i = 1; i <=n; ++i) {
                map.put(i, new ArrayList<>());
            }

            st = new StringTokenizer(bf.readLine());
            for(int i = 1; i <= n; ++i) {
                cost[i] = Integer.parseInt(st.nextToken());
                mins[i] = Integer.MAX_VALUE;
            }

            for(int i = 0; i < k; ++i) {
                st = new StringTokenizer(bf.readLine());
                int pre = Integer.parseInt(st.nextToken());
                int task = Integer.parseInt(st.nextToken());
                map.get(task).add(pre);
            }

            int target = Integer.parseInt(bf.readLine());
            
            sb.append(findMinimumTime(target)).append("\n");

            --t;
        }
        
        System.out.println(sb);
	}

    // target 건설을 완료하는데 필요한 최소 시간
    public static int findMinimumTime(int target) {
        if(mins[target] != Integer.MAX_VALUE) {
            return mins[target];
        }
        
        if(map.get(target).isEmpty()) {
            return mins[target] = cost[target];
        }

        int time = 0;
        for(int pre : map.get(target)) {
            time = Math.max(time, findMinimumTime(pre));
        }

        return mins[target] = time + cost[target];
    }

}
