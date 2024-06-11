import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public class ChatInfo {
        boolean isIn;
        User user;
        
        public ChatInfo(boolean isIn, User user) {
            this.isIn = isIn;
            this.user = user;
        }
        
        public String getMassage() {
            if(isIn) return user.nickname + "님이 들어왔습니다.";
            return user.nickname + "님이 나갔습니다.";
        }
    }
    
    public class User {
        String uid;
        String nickname;
        
        public User(String uid, String nickname) {
            this.uid = uid;
            this.nickname = nickname;
        }
    }
    
    public String[] solution(String[] record) {
        Map<String, User> map = new HashMap<>(); // key: uid, value: user
        
        List<ChatInfo> infos = new ArrayList<>();
        for(String rec : record) {
            String[] info = rec.split(" ");
            if(info[0].equals("Leave")) {
                infos.add(new ChatInfo(false, map.get(info[1])));
                continue;
            }
            
            User user = map.getOrDefault(info[1], new User(info[1], info[2]));
            user.nickname = info[2];
            map.put(user.uid, user);
            if(info[0].equals("Enter")) {
                infos.add(new ChatInfo(true, map.get(info[1])));  
            }
        }
        
        String[] answer = new String[infos.size()];
        int idx = 0;
        for(ChatInfo info : infos) {
            answer[idx++] = info.getMassage();
        }
        
        return answer;
    }
}