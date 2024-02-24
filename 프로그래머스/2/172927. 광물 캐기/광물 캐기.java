class Solution {
    public int[][] tired;
    public int min = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        // picks[0]: 다이아 곡갱이 수
        // picks[1]: 철 곡갱이 수
        // picks[2]: 돌 곡갱이 수
        
        // minerals을 5개씩 다이아/철/돌 곡갱이로 캤을 때 피로도를 구하자(최대 10번)
        // 가지고 있는 곡갱이를 dfs..
        int countOfMinerals = minerals.length;
        int countOfSet = (countOfMinerals / 5) + ((countOfMinerals % 5 == 0) ? 0 : 1);
        tired = new int[countOfSet][3];
        int index = 0;
        for(int i = 0; i < countOfSet; ++i) {
            for(int j = 0; j < 5; ++j) {
                if(countOfMinerals == index) break;
                
                String mineral = minerals[index];
                
                switch(mineral) {
                    case "diamond": {
                        tired[i][0] += 1;
                        tired[i][1] += 5;
                        tired[i][2] += 25;
                        break;
                    }
                    case "iron": {
                        tired[i][0] += 1;
                        tired[i][1] += 1;
                        tired[i][2] += 5;
                        break;
                    }
                    case "stone": {
                        tired[i][0] += 1;
                        tired[i][1] += 1;
                        tired[i][2] += 1;
                        break;
                    }
                }
                
                index++;
            }
        }
        
        dfs(0, 0, picks, countOfSet);
        
        return min;
    }
    
    public void dfs(int level, int tired_score, int[] picks, int countOfSet) {
        if(level == countOfSet) {
            min = Math.min(min, tired_score);
            return;
        }
        
        boolean hasPick = false;
        for(int i = 0; i < 3; ++i) {
            if(picks[i] <= 0) continue;
            hasPick = true;
            
            picks[i] -= 1;
            dfs(level + 1, tired_score + tired[level][i], picks, countOfSet);
            picks[i] += 1;
        }
        
        if(!hasPick) min = Math.min(min, tired_score);
    }
}