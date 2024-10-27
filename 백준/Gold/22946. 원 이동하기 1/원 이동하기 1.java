import java.io.*;
import java.util.*;

/**
 *  20:10 START!
 */
public class Main
{
    public static class Circle {
        int x, y, r, maxDepth;
        List<Circle> children;
        
        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
            children = new ArrayList<>();
        }
        
        public int addChild(Circle circle) {
            boolean done = false;
            int depth = 1;
            
            for(Circle child : children) {
                if(child.include(circle)) {
                    depth += child.addChild(circle);
                    done = true;
                    break;
                }
            }
            
            if(!done) children.add(circle);
            
            maxDepth = Math.max(maxDepth, depth);
            
            return depth;
        }
        
        public boolean include(Circle circle) {
            return Math.pow(circle.x - x, 2) + Math.pow(circle.y - y, 2) < Math.pow(r, 2);
        }
    }
    
	public static void main(String[] args) throws Exception {
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(bf.readLine());
	    int[][] circles = new int[n][3];
	    
	    StringTokenizer st;
	    for(int i = 0; i < n; ++i) {
	        st = new StringTokenizer(bf.readLine());
	        int x = Integer.parseInt(st.nextToken());
	        int y = Integer.parseInt(st.nextToken());
	        int r = Integer.parseInt(st.nextToken());
	        
	        circles[i] = new int[]{x, y, r};
	    }
	    
	    Arrays.sort(circles, (a, b) -> b[2] - a[2]);
	    
	    List<Circle> parents = new ArrayList<>();
	    for(int[] circle : circles) {
	        boolean done = false;
	        Circle c = new Circle(circle[0], circle[1], circle[2]);
	        for(Circle parent : parents) {
	            if(parent.include(c)) {
	                parent.addChild(c);
	                done = true;
	                break;
	            }
	        }
	        
	        if(!done) parents.add(c);
	    }
	    
	    recurCheck(parents);
	    
	    System.out.println(max);
	}
	
	static int max;
	public static void recurCheck(List<Circle> circles) {
	    if(circles.size() == 0) return;
	    
	    Collections.sort(circles, (a, b) -> b.maxDepth - a.maxDepth);
	    
	    int sum = 0;
	    for(int i = 0; i < 2; ++i) {
            if(circles.size() <= i) break;
            sum += circles.get(i).maxDepth + 1;
        }
        
        max = Math.max(max, sum);
        
        recurCheck(circles.get(0).children);
	}
}