import java.io.*;
import java.util.*;

// 13:05 시작!
public class Main
{
    // 3번 나오는 애가 순환선과 지선의 경계에 있는 노드
    static int n;
    static List<List<Integer>> stations;
    static int[] dist;
    static boolean[] cycle;
    static boolean[][] visit;
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(bf.readLine());
	    stations = new ArrayList<>();
	    for(int i = 0; i <= n; ++i) {
	        stations.add(new ArrayList<>());
	    }
	    
	    StringTokenizer st;
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int s1 = Integer.parseInt(st.nextToken());
	        int s2 = Integer.parseInt(st.nextToken());
	        stations.get(s1).add(s2);
	        stations.get(s2).add(s1);
	    }
	    
	    cycle = new boolean[n + 1];
	    for(int i = 1; i <= n; ++i) {
	        visit = new boolean[n + 1][n + 1];
	        if(check(i, i)) {
	            cycle[i] = true;
	            break;
	        }
	    }
	    
	    dist = new int[n + 1];
	    for(int i = 1; i <= n; ++i) {
	        if(stations.get(i).size() == 1) {
	            // 지선 끝에 있는 노드
	            visit = new boolean[n + 1][n + 1];
	            dist[i] = search(i);
	        }
	    }
	    
	    for(int i = 1; i <= n; ++i) {
	        System.out.print(dist[i] + " ");
	    }
	}
	
	public static int search(int station) {
	    if(dist[station] > 0) return dist[station];
	    
	    for(int next : stations.get(station)) {
	        if(visit[station][next]) continue;
	        visit[station][next] = true;
	        visit[next][station] = true;
	        
	        if(cycle[next]) return 1;
	        dist[next] = search(next);
	        if(dist[next] == -1) {
	            dist[next] = 0;
	            continue;
	        }
	        
	        return dist[next] + 1;
	    }
	    
	    return -1;
	}
	
	// station이 순환선인지 확인하는 메서드
	public static boolean check(int station, int start) {
	    for(int next : stations.get(station)) {
	        if(visit[station][next]) continue;
	        visit[station][next] = true;
	        visit[next][station] = true;
	        
	        if(next == start) {
	            cycle[station] = true;
	            return true;
	        }
	        
	        if(check(next, start)) {
	            cycle[station] = true;
	            return true;
	        }
	    }
	    
	    return false;
	}
}