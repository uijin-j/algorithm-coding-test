import java.util.Arrays;

class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        int playSeconds = toSeconds(play_time);
        int advSeconds = toSeconds(adv_time);
        
        int[] watch = new int[playSeconds + 1]; // watch[i]: i ~ i+1초 사이에 영상 시청 수
        for (String log : logs) {
            String[] splited = log.split("-");
            int start = toSeconds(splited[0]);
            int end = toSeconds(splited[1]);
            
            for (int i = start; i < end; i++) {
                watch[i]++;
            }
        }

        long total = 0;
        for (int i = 0; i < advSeconds; i++) {
            total += watch[i];
        }
        
        int maxIdx = 0;
        long maxTotal = total;
        for (int end = advSeconds; end < playSeconds; ++end) {
            int start = end - advSeconds + 1;
            total += watch[end];
            total -= watch[start - 1];
            
            if (total > maxTotal) {
                maxTotal = total;
                maxIdx = start;
            }
        }

        return toHHMMSS(maxIdx);
    }

    public int toSeconds(String hhmmss) {
        String[] info = hhmmss.split(":");
        int total = 0;
        
        total += Integer.parseInt(info[0]) * 60 * 60;
        total += Integer.parseInt(info[1]) * 60;
        total += Integer.parseInt(info[2]);
        
        return total;
    }

   public String toHHMMSS(int seconds) {
        int h = seconds / (60 * 60);
        seconds = Math.max(0, seconds - h * (60 * 60));
        int m = seconds / 60;
         seconds = Math.max(0, seconds - m * (60));
        int s = seconds;
        
        String hh = (h > 9) ? String.valueOf(h) : "0" + String.valueOf(h);
        String mm = (m > 9) ? String.valueOf(m) : "0" + String.valueOf(m);
        String ss = (s > 9) ? String.valueOf(s) : "0" + String.valueOf(s);
        
        return hh + ":" + mm + ":" + ss;
    }
}