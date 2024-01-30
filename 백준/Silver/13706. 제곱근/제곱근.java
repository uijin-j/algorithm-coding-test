import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BigInteger number = new BigInteger(bf.readLine());
        BigInteger left = BigInteger.ONE;
        BigInteger right = number;

        while(left.compareTo(right) <= 0) {
            BigInteger mid = left.add(right).divide(BigInteger.TWO);
            BigInteger pow = mid.pow(2);
            if(pow.equals(number)) {
                System.out.println(mid);
                return;
            } else if(pow.compareTo(number) > 0) {
                right = mid.subtract(BigInteger.ONE);
            } else {
                left = mid.add(BigInteger.ONE);
            }
        }
    }
}
