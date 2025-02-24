class Solution {
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        
        for(int i = 0; i < n; ++i) {
            String binary = Long.toBinaryString(numbers[i]);
            
            // 1. 앞에 생략된 0 채우기
            int len = binary.length();
            int target = findTarget(len);

            binary = "0".repeat(target - len) + binary;
            
            // 2. 이진 트리로 수를 표현할 수 있는지 확인
            if(possible(binary, 0, binary.length() - 1)) {
                answer[i] = 1;
            }
        }
        
        return answer;
    }
    
    public int findTarget(int n) {
        int num = 1;
        int count = 1;
        while(num < n) {
            num += Math.pow(2, count++);
        }
        
        return num;
    }
    
    public boolean possible(String binary, int start, int end) {
        if(start == end) return true;
        
        int mid = (start + end) / 2;
        if(binary.charAt(mid) == '0') {
            int leftRoot = (start + mid - 1) / 2;
            int rightRoot = (mid + 1 + end) / 2;

            if(binary.charAt(leftRoot) != '0' || binary.charAt(rightRoot) != '0') return false;
        }    
        
        boolean left = possible(binary, start, mid - 1);
        boolean right = possible(binary, mid + 1, end);

        if(left && right) return true;
        return false;
    }
}