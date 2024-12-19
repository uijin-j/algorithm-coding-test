import java.io.*;
import java.util.*;

public class Main {
    static int[][] cases = {
        { 1, 3, 5, 7, 9, 11, 24, 22 },
        { 2, 4, 6, 8, 10, 12, 23, 21 },
        { 13, 14, 5, 6, 17, 18, 21, 22 },
        { 15, 16, 7, 8, 19, 20, 23, 24 },
        { 1, 2, 18, 20, 12, 11, 15, 13 },
        { 3, 4, 17, 19, 10, 9, 16, 14 }
    };
    
	static int[] cube = new int[25];
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(bf.readLine());
	    for(int i = 1; i <= 24; ++i) {
	        cube[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    if(isAllFacesSame()) {
	        System.out.println(0);
    	    return;
	    }
	    
	    for(int i = 0; i < 6; ++i) {
	        for(int j = 0; j < 4; ++j) {
	            // 시계
	            turnClock(cases[i]);
    	        if(isAllFacesSame()) {
    	            System.out.println(1);
    	            return;
    	        }

    	        turnCounterClock(cases[i]);
    	        
    	        // 반시계
    	        turnCounterClock(cases[i]);
    	        if(isAllFacesSame()) {
    	            System.out.println(1);
    	            return;
    	        }
    	        
    	        turnClock(cases[i]);
	        }
	    }
	    
	    
	    System.out.println(0);
	}
	
	public static boolean isSameColor(int start) {
	    int color = cube[start];
	    
	    for(int i = 1; i <= 3; ++i) {
	        if(color != cube[start + i]) {
	            return false;
	        }
	    }
	    
	    return true;
	}
	
	public static boolean isAllFacesSame() {
	    return isSameColor(1) && isSameColor(5) && isSameColor(9) 
	        && isSameColor(13) && isSameColor(17) && isSameColor(21);
	}
	
	public static void turnClock(int[] arr) {
	    int[] original = new int[8];
	    for(int i = 0; i < 8; ++i) {
	        original[i] = cube[arr[i]];
	    }
	
	    for(int i = 0; i < 8; ++i) {
	        cube[arr[i]] = original[(i + 2) % 8];
	    }
	}
	
	public static void turnCounterClock(int[] arr) {
	    int[] original = new int[8];
	    for(int i = 0; i < 8; ++i) {
	        original[i] = cube[arr[i]];
	    }
	
	    for(int i = 0; i < 8; ++i) {
	        cube[arr[(i + 2) % 8]] = original[i];
	    }
	}
}