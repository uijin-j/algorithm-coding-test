// 16:20 START!
class Solution {
    /**
     * 그리디
     * 최대한 많이 챙겨서, 가장 먼 집부터 배달 O(2n) ~= O(n)
     * 돌아오면서, 가장 먼 집부터 수거
     */
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliveryIdx = n - 1;
        int pickupIdx = n - 1;
        while(deliveryIdx >= 0 || pickupIdx >= 0) { // 아직 배달/수거해야 되는 것이 남아 있으면!
            while(deliveryIdx >= 0 && deliveries[deliveryIdx] == 0) {
                deliveryIdx--;
            }
        
            while(pickupIdx >= 0 && pickups[pickupIdx] == 0) {
                pickupIdx--;
            }
              
            answer += (Math.max(deliveryIdx, pickupIdx) + 1) * 2;
            
            int boxes = cap;
            int emptyBoxes = 0;
            while(boxes > 0 && deliveryIdx >= 0) {
                if(deliveries[deliveryIdx] <= boxes) {
                    boxes -= deliveries[deliveryIdx--];
                } else {
                    deliveries[deliveryIdx] -= boxes;
                    boxes = 0;
                }
            }

            while(emptyBoxes < cap && pickupIdx >= 0) {
                if(emptyBoxes + pickups[pickupIdx] <= cap) {
                    emptyBoxes += pickups[pickupIdx--];
                } else {
                    pickups[pickupIdx] -= cap - emptyBoxes;
                    emptyBoxes = cap;
                }
            }   
        }
        
        return answer;
    }
}