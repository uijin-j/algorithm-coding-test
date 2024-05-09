import java.io.*;
import java.util.*;

public class Main
{
	static List<List<Integer>> graph;
	static int[] nums;
	static int numOfNodes;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(bf.readLine());
		numOfNodes = (int) Math.pow(2, k) - 1;
		nums = new int[numOfNodes];

		graph = new ArrayList<>();
		for(int level = 0; level <= k; ++level) {
			graph.add(new ArrayList<>());
		}

		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < numOfNodes; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		fuc(0, numOfNodes - 1, 1);
		
		for(int level = 1; level <= k; ++level) {
			for(int node : graph.get(level)) {
				System.out.print(node + " ");
			}
			System.out.println();
		}
	}

	public static void fuc(int left, int right, int level) {
		if(left > right) {
			return;
		}

		int mid = findMid(left, right);
		graph.get(level).add(nums[mid]);

		fuc(left, mid - 1, level + 1); // 0 2 2
		fuc(mid + 1, right, level + 1); // 4 6 2
	}

	public static int findMid(int left, int right) {
		return (right + left) / 2;
	}
}
