import java.util.*;

// 19:20 시작!
class Solution {
    /**
     * k가 10^12 이기 때문에 O(k)도 느림.. O(logK)로 풀어야 함!
     * 가장 간단한 방법? room_number를 돌면서 할당 O(n^2)
     * python map의 open addressing 방식이랑 비슷한듯
     * 1 2 3 4 5 6 7 8 9 10
     * _ _ _ _ _ _ _ _ _ _
     * 
     */
    Map<Long, Long> next;
    Set<Long> reserved;
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        next = new HashMap<>();
        reserved = new HashSet<>();
        
        long[] answer = new long[n];
        for(int i = 0; i < n; ++i) {
            long room = room_number[i];
            if(reserved.contains(room)) room = updateNext(room);
            reserved.add(room);
            // System.out.println("RESERVE " + room);
            answer[i] = room;
            updateNext(room);
            System.out.println();
        }
        
        return answer;
    }
    
    public long updateNext(long room) {
        if(!reserved.contains(room)) return room;
        long nextRoom = next.getOrDefault(room, room + 1);
        long nextEmpty = updateNext(nextRoom);
        next.put(room, nextEmpty);
        // System.out.println("PUT " + room + " " + nextEmpty);
        return nextEmpty;
    }
}