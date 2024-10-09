import java.util.Arrays;

class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        int playSeconds = toSeconds(play_time);
        int advSeconds = toSeconds(adv_time);
        
        int[] times = new int[360_000];
        for (String log : logs) {
            String[] splitLog = log.split("-");
            int startTime = toSeconds(splitLog[0]);
            int endTime = toSeconds(splitLog[1]);
            
            for (int i = startTime; i < endTime; i++) {
                times[i]++;
            }
        }

        int maxIdx = 0;
        long totalTime = 0;
        for (int i = 0; i < advSeconds; i++) {
            totalTime += times[i];
        }
        
        long maxTotalTime = totalTime;
        for (int i = advSeconds; i < playSeconds; i++) {
            totalTime += times[i] - times[i - advSeconds];
            
            if (totalTime > maxTotalTime) {
                maxTotalTime = totalTime;
                maxIdx = i - advSeconds + 1;
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