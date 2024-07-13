class Solution {
    int[] dx = { -1, 0, 1, 0, -1, 1, 1, -1};
    int[] dy = { 0, 1, 0, -1, 1, 1, -1, -1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        int idx = 0;
        for(String[] place : places) {
            boolean isValid = true;
            
            for(int x = 0; x < 5; ++x) {
                for(int y = 0; y < 5; ++y) {
                    char position = place[x].charAt(y);
                    
                    if(position == 'P') {
                        boolean[] check = new boolean[4];
                        
                        for(int i = 0; i < 4; ++i) {
                            int nx = x + dx[i];
                            int ny = y + dy[i];
                            
                            if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                                if(place[nx].charAt(ny) == 'P') {
                                    isValid = false;
                                    break;
                                }
                                
                                if(place[nx].charAt(ny) == 'X') check[i] = true;
                            }
                        }
                        
                        for(int i = 0; i < 4; ++i) {
                            int nx = x + dx[i] * 2;
                            int ny = y + dy[i] * 2;
                            
                            if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && place[nx].charAt(ny) == 'P' && !check[i]) {
                                isValid = false;
                                break;
                            }
                        }
                        
                        for(int i = 4; i < 8; ++i) {
                            int nx = x + dx[i];
                            int ny = y + dy[i];
                            
                            int before = i-4;
                            int after = (i-3 == 4) ? 0 : i-3;
                            if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && place[nx].charAt(ny) == 'P' && (!check[before] || !check[after])) {
                                isValid = false;
                                break;
                            }
                        }
                        
                        if(!isValid) break;
                    }
                }
                
                if(!isValid) break;
            }
            
            if(isValid) answer[idx] = 1;
            idx++;
        }
        
        return answer;
    }
}