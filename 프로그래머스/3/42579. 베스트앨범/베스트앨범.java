import java.util.*;

class Solution {
    // 알아야 하는 것
    // 1. 장르별로 플레이된 total
    // 2. 장르별로 노래의 순위
    public class Genre {
        String name;
        long total;
        
        public Genre(String name) {
            this.name = name;
            this.total = 0;
        }
    }
    
    public class Music implements Comparable<Music> {
        int plays;
        int id;
        
        public Music(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Music m) {
            return m.plays - this.plays == 0 ? this.id - m.id : m.plays - this.plays;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> genreMap = new HashMap<>();
        Map<String, PriorityQueue<Music>> playlists = new HashMap<>(); // 장르별 플레이리스트
        for(int i = 0; i < genres.length; ++i) {
            String name = genres[i];
            int play = plays[i];
            
            Genre genre = genreMap.getOrDefault(name, new Genre(name));
            genre.total += play;
            genreMap.put(name, genre);
            
            PriorityQueue<Music> pq = playlists.getOrDefault(name, new PriorityQueue<>());
            pq.offer(new Music(i, play));
            playlists.put(name, pq);
        }
        
        PriorityQueue<Genre> pq = new PriorityQueue<Genre>((a, b) -> a.total == b.total ? 0 :  (a.total > b.total ? -1 : 1));
        for(Genre genre : genreMap.values()) {
            pq.offer(genre);
        }
        
        List<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()) {
            Genre genre = pq.poll();
            PriorityQueue<Music> playlist = playlists.get(genre.name);
            for(int i = 0; i < 2; ++i) {
                if(playlist.isEmpty()) break;
                
                Music music = playlist.poll();
                list.add(music.id);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}