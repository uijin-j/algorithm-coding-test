import java.util.*;
import java.util.stream.*;

class Solution {
    public static class Music {
        int duration;
        int order;
        String name;
        
        public Music(int duration, int order, String name) {
            this.duration = duration;
            this.order = order;
            this.name = name;
        }
        
    }
    
    public String solution(String m, String[] musicinfos) {
        m = convert(m);
        int length = m.length();
        PriorityQueue<Music> pq = new PriorityQueue<>((a, b) -> 
                (b.duration - a.duration == 0) ? a.order - b.order : b.duration - a.duration);
        
        int order = 0;
        for(String musicinfo : musicinfos) {
            String[] info = musicinfo.split(",");
            int duration = calculateDuration(info[0], info[1]);
            String name = info[2];
            String music = convert(info[3]);
            int musicLength = music.length();
            int neededTime = duration; // 음악을 더 재생해야 되는 시간
            
            StringBuilder playedMusic = new StringBuilder();
            while(neededTime >= musicLength) { // 한 곡 전체를 재생할 수 있다면, 계속 재생
                playedMusic.append(music);
                neededTime -= musicLength;
            }
            
            playedMusic.append(music.substring(0, neededTime));
            
            if(playedMusic.toString().contains(m)) {
                pq.offer(new Music(duration, order, name));
            }
            
            order++;
        }
         
        return pq.isEmpty() ? "(None)" : pq.poll().name;
    }
    
    public String convert(String melody) {
        StringBuilder sb = new StringBuilder();
        int diff = 'A' - 'a';
        for(int i = 0; i < melody.length(); ++i) {
            if(i+1 < melody.length() && melody.charAt(i+1) == '#') {
                sb.append(String.valueOf((char) (melody.charAt(i) - diff)));
                ++i;
                continue;
            }
            
            sb.append(String.valueOf(melody.charAt(i)));
        }
        
        return sb.toString();
    }
    
    public int calculateDuration(String startAt, String endAt) {
        String[] startTime = startAt.split(":");
        String[] endTime = endAt.split(":");
        int hour = Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0]);
        int minute = Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]);
        
        if(minute < 0) {
            minute = 60 + minute;
            hour -= 1;
        }
        
        return hour * 60 + minute;
    }
}