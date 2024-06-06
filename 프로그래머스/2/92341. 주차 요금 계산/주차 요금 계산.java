import java.util.*;
import java.time.*;

class Solution {
    public class Car {
        int number, parkingtime;
        LocalTime arrivedAt;
        
        public Car(int number) {
            this.number = number;
        }
        
        public void park(LocalTime arrivedAt) {
            this.arrivedAt = arrivedAt;
        }
        
        public void leave(LocalTime leaveAt) {
            parkingtime += (leaveAt.getHour() - arrivedAt.getHour()) * 60;
            parkingtime += leaveAt.getMinute() - arrivedAt.getMinute(); 
            arrivedAt = null;
        }
        
        public int getTotalFee(int[] fees) {
            if(arrivedAt != null) leave(LocalTime.of(23, 59));
            if(parkingtime <= fees[0]) return fees[1];
            
            int fee = fees[1];
            int over = (parkingtime - fees[0]) / fees[2];
            over += (parkingtime - fees[0]) % fees[2] > 0 ? 1 : 0;
            fee += over * fees[3];
            return fee;
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Car> map = new HashMap<>();
        
        for(String record : records) {
            String[] infos = record.split(" ");
            LocalTime time = LocalTime.parse(infos[0]);
            int number = Integer.parseInt(infos[1]);
            boolean isIn = infos[2].equals("IN") ? true : false;
            
            Car car = map.getOrDefault(number, new Car(number));
            map.put(number, car);
            if(isIn) car.park(time);
            else car.leave(time);
        }
        
        return map.values().stream()
            .sorted((a, b) -> a.number - b.number)
            .map(car -> car.getTotalFee(fees))
            .mapToInt(i -> i)
            .toArray();
    }
}