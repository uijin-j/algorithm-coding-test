import java.io.*;
import java.util.*;

public class Main
{
	static String formula;
	static int numOfOperator;
	static boolean[] selected;
	static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		numOfOperator = (n - 1) / 2;
		selected = new boolean[numOfOperator];
		formula = bf.readLine();

		int answer = Integer.MIN_VALUE;
		for(int i = 0; i <= (numOfOperator + 1) / 2; ++i) {
			// i개의 괄호를 선택
			max = Integer.MIN_VALUE;
			select(0, 0, i);
			answer = Math.max(answer, max);
		}

		System.out.println(answer);
	}

	// goal개의 괄호로 묶을 연산자를 선택
	static void select(int start, int count, int goal) {
		if(count == goal) { // 선택 완료
			Deque<Integer> nums = new ArrayDeque<>();
			Deque<Character> operator = new ArrayDeque<>();
			// 괄호 처리
			for(int i = 0; i < formula.length(); ++i) {
				if(i % 2 == 0) {
					nums.offer(formula.charAt(i) - '0');
					continue;
				}

				if(!selected[i/2]) {
					operator.offer(formula.charAt(i));
					continue;
				}

				if(selected[i/2]) {
					nums.offer(calculate(nums.pollLast(), formula.charAt(i+1) - '0', formula.charAt(i)));
					++i;
					continue;
				}
			}

			// 괄호 후 계산
			int sum = nums.pollFirst();
			for(char op : operator) {
				sum = calculate(sum, nums.poll(), op);
			}

			max = Math.max(max, sum);
			return;
		}

		for(int i = start; i < numOfOperator; ++i) {
			selected[i] = true;
			select(i + 2, count + 1, goal);
			selected[i] = false;
		}
	}

	static int calculate(int op1, int op2, char operator) {
		if(operator == '+') {
			return op1 + op2;
		}

		if(operator == '-') {
			return op1 - op2;
		}
		
		return op1 * op2;
	}

}
