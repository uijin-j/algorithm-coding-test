import java.util.*;

// 21:54 시작!
class Solution {
    /**
     * 슬라이딩 윈도우?
     * 재생 시간의 최대 => 99 * 60 * 60 + 59 * 60 + 59 => 약 360_000
     * 뒤에서 부터 슬라이딩 윈도우 진행
     * 1) 처음에는 뒤에서부터 adv_time까지 시청 시간(total)을 구함
     * 2) 1초를 넘기고 그때부터 신규 사용자(+) / 빠지는 사용자(-)
     *  - 각 시간별로 들어오는 사용자와 나가는 사용자를 알아야 함
     */
    Map<Integer, Integer> in, out;
    int[] users;
    int user = 0;
    public String solution(String play_time, String adv_time, String[] logs) {
        in = new HashMap<>();
        out = new HashMap<>();
        
        for(String log : logs) {
            int start = toSeconds(log.split("-")[0]);
            int end = toSeconds(log.split("-")[1]);
            
            in.put(start, in.getOrDefault(start, 0) + 1);
            out.put(end, out.getOrDefault(end, 0) + 1);
        }
        
        int playSeconds = toSeconds(play_time);
        int advSecounds = toSeconds(adv_time);
        users = new int[playSeconds + 1];
        
        long sum = sumFirst(advSecounds);
        long max = sum;
        int minStart = 0;
        for(int e = advSecounds, s = 1; e < playSeconds; ++e, ++s) {
            user += in.getOrDefault(e, 0);
            user -= out.getOrDefault(e, 0);
            
            users[e] = user;
            sum += user;
            sum -= users[s-1];
            
            if(sum > max) {
                max = sum;
                minStart = s;
            }
        }
        
        return toString(minStart);
    }
    
    public int sumFirst(int running) { // 0 ~ running 까지 시청자 수
        int sum = 0;
        for(int t = 0; t < running; ++t) {
            user += in.getOrDefault(t, 0);
            user -= out.getOrDefault(t, 0);
            
            users[t] = user;
            sum += user;
        }
        
        return sum;
    }
    
    public int toSeconds(String hhmmss) {
        int total = 0;
        String[] time = hhmmss.split(":");
        
        total += Integer.parseInt(time[0]) * 3600;
        total += Integer.parseInt(time[1]) * 60;
        total += Integer.parseInt(time[2]);
        
        return total;
    }
    
    public String toString(int seconds) {
        int h = seconds / 3600;
        seconds -= h * 3600;
        int m = seconds / 60;
        seconds -= m * 60;
        int s = seconds;
        
        StringBuilder sb = new StringBuilder();
        if(h < 10) sb.append("0").append(h);
        else sb.append(h);
        
        sb.append(":");
        
        if(m < 10) sb.append("0").append(m);
        else sb.append(m);
        
        sb.append(":");
        
        if(s < 10) sb.append("0").append(s);
        else sb.append(s);
        
        return sb.toString();
    }
}