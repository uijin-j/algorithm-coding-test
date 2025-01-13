import java.io.*;
import java.util.*;

// 14:38 시작!
public class Main {
    /**
     * 유니온-파인드?
     * F1과 F2가 친구일 때, 
     * F1의 친구 네트워크 O, F2의 친구 네트워크 X => F2를 F1의 친구 네트워크로
     * F1의 친구 네트워크 X, F2의 친구 네트워크 O => F1을 F2의 친구 네트워크로
     * F1의 친구 네트워크 O, F2의 친구 네트워크 O => 두 친구 네트워크를 합치기
     * F1의 친구 네트워크 X, F2의 친구 네트워크 X => 새로운 친구 네트워크를 만들기
     */
    static Map<String, Integer> mapToId;
    static int[] network;
    static int[] count;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(bf.readLine());
	    
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    while(t > 0) {
	        int n = Integer.parseInt(bf.readLine());
	        mapToId = new HashMap<>();
	        
	        network = new int[n * 2];
	        for(int i = 0; i < n * 2; ++i) network[i] = i;
	        
	        count = new int[n * 2];
	        Arrays.fill(count, 1);
	        
	        int next = 0;
	        for(int i = 0; i < n; ++i) {
	            st = new StringTokenizer(bf.readLine());
	            String first = st.nextToken();
	            String second = st.nextToken();
	            
	            int f1, f2;
	            if(mapToId.containsKey(first)) {
	                f1 = mapToId.get(first);
	            } else {
	                f1 = next++;
	                mapToId.put(first, f1);
	            }
	            
	            if(mapToId.containsKey(second)) {
	                f2 = mapToId.get(second);
	            } else {
	                f2 = next++;
	                mapToId.put(second, f2);
	            }
	            
	            sb.append(union(f1, f2)).append("\n");
	        }
	        
	        t--;
	    }
	    
	    System.out.println(sb.toString());
	}
	
	public static int find(int id) {
	    if(network[id] == id) return id;
	    return network[id] = find(network[id]);
	}
	
	public static int union(int f1, int f2) {
	    int net1 = find(f1);
	    int net2 = find(f2);
	    
	    if(net1 == net2) return count[net1];
	    
	    if(net1 < net2) {
	        network[net2] = net1;
	        return count[net1] += count[net2];
	    }
	    
	    network[net1] = net2;
	    return count[net2] += count[net1];
	}
}