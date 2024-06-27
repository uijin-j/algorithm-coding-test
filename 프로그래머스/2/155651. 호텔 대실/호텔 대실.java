import java.util.*;
import java.time.*;

/**
 * 풀이 1) PQ를 활용하는 대표적인 문제인, 회의실 배정 문제와 비슷하다고 생각!
 * 주의사항) 끝나는 시간에서 청소 시간 10분을 처리해 줘야 함! (끝나는 시간이 오후 11시 50분 이후이면 10분을 더했을 때, 다음 날이 되는 것에 주의!)
 *  - LocalTime은 java.time.* 를 import 해야함
 *  - LocalTime의 of(hour, minute), isAfter(localTime), isBefore(localTime), plusMinutes(minutes) 활용법 기억하기!
 */

class Solution {
    // 회의실 문제와 유사 PQ
    public class Room {
       LocalTime startAt, endAt;
        
        public Room(LocalTime start, LocalTime end) {
            this.startAt = start;
            this.endAt = end;
            
            if(this.endAt.isAfter(LocalTime.of(23, 49))) {
                this.endAt = LocalTime.of(23, 49);
            } else {
                this.endAt = this.endAt.plusMinutes(10);
            }
        }
    }
    
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));
        
        PriorityQueue<Room> rooms = new PriorityQueue<Room>((a, b) -> a.endAt.compareTo(b.endAt));
        for(String[] book : book_time) {
            String[] startAt = book[0].split(":");
            String[] endAt = book[1].split(":");
            
            LocalTime start = LocalTime.of(Integer.parseInt(startAt[0]), Integer.parseInt(startAt[1]));
            LocalTime end = LocalTime.of(Integer.parseInt(endAt[0]), Integer.parseInt(endAt[1]));
            
            if(rooms.isEmpty()) {
                rooms.offer(new Room(start, end));
                continue;
            }
            
            if(rooms.peek().endAt.isBefore(start) || rooms.peek().endAt.equals(start)) {
                rooms.poll();
            }
            
            rooms.offer(new Room(start, end)); 
        }
        
        return rooms.size();
    }
}