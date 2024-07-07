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
            String[] startAt = info[0].split(":");
            String[] endAt = info[1].split(":");
            int hour = Integer.parseInt(endAt[0]) - Integer.parseInt(startAt[0]);
            int minute = Integer.parseInt(endAt[1]) - Integer.parseInt(startAt[1]);
            if(minute < 0) {
                minute = 60 + minute;
                hour -= 1;
            }
            int duration = hour * 60 + minute;
            String name = info[2];
            String music = convert(info[3]);
            int musicLength = music.length();
            int neededTime = duration;
            StringBuilder playedMusic = new StringBuilder();
            while(neededTime >= musicLength) {
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
        melody = melody.replaceAll("C#", "c");
        melody = melody.replaceAll("D#", "d");
        melody = melody.replaceAll("F#", "f");
        melody = melody.replaceAll("G#", "g");
        melody = melody.replaceAll("A#", "a");
        melody = melody.replaceAll("B#", "b");
        
        return melody;
    }
}