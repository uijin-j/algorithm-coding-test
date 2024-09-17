import java.util.*;

// 17:25 START! 
// 유니온파인드?
class Solution {
    int[] parents;
    String[] values;
    public String[] solution(String[] commands) {
        List<String> printedList = new ArrayList<>();
        parents = new int[2501];
        values = new String[2501];
        for(int i = 1; i <= 2500; ++i) {
            parents[i] = i;
            values[i] = "";
        }
        
        for(String command : commands) {
            String[] args = command.split(" ");
            String cmd = args[0];
            
            if(cmd.equals("UPDATE") && args.length == 4) {
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                
                int point = getPoint(x, y);
                
                values[find(point)] = args[3];
                continue;
            }
            
            if(cmd.equals("UPDATE")) {
                String from = args[1];
                String to = args[2];
                for(int point = 1; point <= 2500; ++point) {
                    if(values[find(point)].equals(from)) {
                        values[find(point)] = to;
                    }
                }
                continue;
            }
            
            if(cmd.equals("MERGE")) {
                int x1 = Integer.parseInt(args[1]);
                int y1 = Integer.parseInt(args[2]);
                int x2 = Integer.parseInt(args[3]);
                int y2 = Integer.parseInt(args[4]);
                int from = getPoint(x1, y1);
                int to = getPoint(x2, y2);
               
                if(from == to) continue;
                
                union(from, to);
                
                continue;
            }
            
            if(cmd.equals("UNMERGE")) {
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                int point = getPoint(x, y);
                
                int target = find(point);
                String before = values[find(point)];
                
                boolean[] check = new boolean[2501];
                for(int i = 1; i <= 2500; ++i) {
                    if(find(i) == target) {
                        check[i] = true;
                    }
                }
                
                for(int i = 1; i <= 2500; ++i) {
                    if(check[i]) parents[i] = i;
                }
                
                values[target] = "";
                values[point] = before;
                
                continue;
            }
            
            if(cmd.equals("PRINT")) {
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                int point = (x - 1) * 50 + y;
                
                String toPrint = values[find(point)].isBlank() ? "EMPTY" : values[find(point)];
                printedList.add(toPrint);

                continue;
            }
        }
        
        String[] answer = new String[printedList.size()];
        for(int i = 0; i < printedList.size(); ++i) {
            answer[i] = printedList.get(i);
        }
        
        return answer;
    }
    
    public int find(int point) {
        if(parents[point] == point) return point;
        return parents[point] = find(parents[point]);
    }
    
    public void union(int p1, int p2) {
        int parent1 = find(p1);
        int parent2 = find(p2);
        
        if(parent1 == parent2) return;
        
        String value = (values[parent1].isBlank()) ? values[parent2] : values[parent1];
        
        parents[parent2] = parent1;
        values[parent1] = value;
        values[parent2] = "";
    }
    
    public int getPoint(int x, int y) {
        return (x - 1) * 50 + y;
    }
}