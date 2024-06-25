import java.util.*;
import java.time.*;

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