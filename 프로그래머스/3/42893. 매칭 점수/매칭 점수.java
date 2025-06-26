import java.util.*;

// 21:57 시작!
class Solution {
    /**
     * 모든 페이지의 모든 글자를 탐색하더라도 O(20 * 1500) => O(30,000)
     * 각 페이지 별로 기본 점수(word가 나온 횟수), 외부 링크 수를 카운트
     */
    public int solution(String word, String[] pages) {
        word = word.toLowerCase();
        int n = pages.length;
        int[] basic = new int[n];
        int[] out = new int[n];
        Map<Integer, String> idxToUrl = new HashMap<>();
        Map<String, List<Integer>> map = new HashMap<>(); // map.get(i)는 i를 참조하는 페이지의 idx 리스트
        for(int i = 0; i < n; ++i) {
            String page = pages[i].toLowerCase();
            int m = page.length();
            int idx = 0;
            int outCount = 0;
            int wordCount = 0;
            StringBuilder tagBuilder = new StringBuilder();
            StringBuilder tokenBuilder = new StringBuilder();
            boolean tagging = false;
            while(idx < m) {
                char ch = page.charAt(idx++);
                
                if(ch == '<') {
                    tagging = true;
                }
                
                if(ch == '>') {
                    String tag = tagBuilder.toString().trim();
                    String[] pairs = tag.split(" ");
                    String name = pairs[0].trim();
                    if(name.equals("meta")) {
                        //System.out.println("[meta] " + tag);
                        for(int j = 1; j < pairs.length; ++j) {
                            String[] pair = pairs[j].split("=");
                            String key = pair[0].trim();
                            String value = pair[1].trim();
                            value = value.substring(1, value.length() - 2);
                            
                            //System.out.println("(" + key+ ", " + value + ") ");

                            if(key.equals("content")) {
                                idxToUrl.put(i, value);
                                break;
                            }
                        }
                    }

                    if(name.equals("a")) {
                        //System.out.println("[a] " + tag);
                        for(int j = 1; j < pairs.length; ++j) {
                            String[] pair = pairs[j].split("=");
                            String key = pair[0].trim();
                            String value = pair[1].trim();
                            value = value.substring(1, value.length() - 1);

                            if(key.equals("href")) {
                                map.putIfAbsent(value, new ArrayList<>());
                                map.get(value).add(i);
                                //System.out.println("put " + value + " " + i);
                                outCount++;
                                break;
                            }
                        }
                    }
                    
                    tagging = false;
                    tagBuilder = new StringBuilder();
                }
                
                if(tagging && ch != '<') {
                    tagBuilder.append(ch);
                }
                
                if(isLetter(ch)) tokenBuilder.append(ch + "");
                else {
                    if(tokenBuilder.toString().equals(word)) wordCount++;
                    tokenBuilder = new StringBuilder();
                }
            }
            
            basic[i] = wordCount;
            out[i] = outCount;
            
            // System.out.println("url=" + idxToUrl.get(i));
            // System.out.println("wordCount=" + wordCount);
            // System.out.println("outCount=" + outCount);
            // System.out.println();
        }
        
        float max = 0;
        int answer = 0;
        for(int i = 0; i < n; ++i) {
            String page = pages[i];
            float point = basic[i];
            
            //System.out.print(point);
            for(int from : map.getOrDefault(idxToUrl.get(i), new ArrayList<>())) {
                point += basic[from] / (float) out[from];
                //System.out.print(" -> " + point);
            }
            
            //System.out.println();
            
            if(point > max) {
                max = point;
                answer = i;
            }
        }
        
        return answer;
    }
    
    public boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z');
    }
}