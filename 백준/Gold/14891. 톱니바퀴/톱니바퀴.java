import java.io.*;
import java.util.*;

public class Main
{
    public static class Wheel {
        List<Integer> magnets;

        public Wheel(List<Integer> magnets) {
            this.magnets = magnets;
        }

        public int getLeft() {
            return magnets.get(6);
        }

        public int getRight() {
            return magnets.get(2);
        }

        public int get12() {
            return magnets.get(0);
        }

        public void rotateClockwise() {
            magnets.add(0, magnets.remove(7));
        }

        public void rotateCounterClockwise() {
            magnets.add(7, magnets.remove(0));
        }

    }

    public static class Wheels {
        private Wheel[] wheels;

        public Wheels(Wheel[] wheels) {
            this.wheels = wheels;
        }

        public void rotate(int num, int direction) {
            int[] directions = new int[4];
            directions[num-1] = direction;

            int left = num - 2;
            while(left >= 0) {
                if(wheels[left+1].getLeft() != wheels[left].getRight()) {
                    directions[left] = directions[left+1] * -1;
                    left--;
                    continue;
                }
                break;
            }

            int right = num;
            while(right <= 3) {
                if(wheels[right-1].getRight() != wheels[right].getLeft()) {
                    directions[right] = directions[right-1] * -1;
                    right++;
                    continue;
                }
                break;
            }

            for(int i = 0; i < 4; ++i) {
                if(directions[i] == 1) {
                    wheels[i].rotateClockwise();
                    continue;
                } 
                
                if(directions[i] == -1) {
                    wheels[i].rotateCounterClockwise();
                } 
            }
        }

        public int getScore() {
            int score = 0;
            for(int i = 0; i < 4; ++i) {
                if(wheels[i].get12() == 1) {
                    score += Math.pow(2, i);
                }
            }

            return score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        Wheel[] wheelArray = new Wheel[4];
        for(int i = 0; i < 4; ++i) {
            String wheel = bf.readLine();
            List<Integer> magnets = new ArrayList<>();
            for(char c : wheel.toCharArray()) {
                magnets.add(c - '0');
            }
            wheelArray[i] = new Wheel(magnets);
        }
    
        Wheels wheels = new Wheels(wheelArray);

        int k = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        for(int i = 0; i < k; ++i) {
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            wheels.rotate(num, direction);
        }

        System.out.println(wheels.getScore());
	}

}
