import java.util.*;

public class CRCPROG {
    public static void main(String[] args) {
        int[] data = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
        int[] divisor = {1, 1, 0, 0, 1};
        
        int n = divisor.length;
        int[] result = new int[n];
        System.arraycopy(data, 0, result, 0, n);
        
        for (int i = n - 1; i >= 0; i--) {
            if (result[i] != divisor[i]) {
                for (int j = 0; j <= i; j++) {
                    result[j] ^= divisor[j];
                }
                System.out.println(Arrays.toString(result));
            }
        }
        
        int leadingZeros = 0;
        while (leadingZeros < result.length && result[leadingZeros] == 0) {
            leadingZeros++;
        }
        
        int[] newData = new int[n + leadingZeros];
        System.arraycopy(data, n, newData, 0, n + leadingZeros);
        
        for (int i = n; i < newData.length; i++) {
            newData[i] = result[i - n];
        }
        
       
        for (int bit : newData) {
            System.out.print(bit);
        }
        System.out.println(); 
    }
}
