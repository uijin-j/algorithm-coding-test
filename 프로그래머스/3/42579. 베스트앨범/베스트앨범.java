import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> album = new ArrayList<>();
        Map<String, PriorityQueue<Integer>> map = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        
        for(int i = 0; i < genres.length; ++i) {
            PriorityQueue<Integer> pq = map.getOrDefault(genres[i], new PriorityQueue<>((a, b) -> plays[b] - plays[a]));
            pq.add(i);
            map.put(genres[i], pq);
            count.put(genres[i], count.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        PriorityQueue<String> genreRank = new PriorityQueue<>((a, b) -> count.get(b) - count.get(a));
        for(String genre : map.keySet()) {
            genreRank.add(genre);
        }
        
        while(!genreRank.isEmpty()) {
            String genre = genreRank.poll();
            PriorityQueue<Integer> songBest = map.getOrDefault(genre, new PriorityQueue<>());
            int cnt = 0;
            while(!songBest.isEmpty() && cnt < 2) {
                album.add(songBest.poll());
                ++cnt;
            }
        }
        
        return album.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}