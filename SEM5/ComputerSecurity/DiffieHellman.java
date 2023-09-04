import java.math.*;
import java.util.*;

public class DiffieHellman {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the prime number (p): ");
        BigInteger p = scanner.nextBigInteger();
        
        System.out.print("Enter the generator (q): ");
        BigInteger q = scanner.nextBigInteger();
        
        System.out.print("Enter Alice's private key (a): ");
        BigInteger a = scanner.nextBigInteger();
        
        System.out.print("Enter Bob's private key (b): ");
        BigInteger b = scanner.nextBigInteger();
        
        scanner.close();
        
        BigInteger aStar = q.modPow(a, p);
        BigInteger bStar = q.modPow(b, p);
        
        BigInteger sharedSecretA = bStar.modPow(a, p);
        BigInteger sharedSecretB = aStar.modPow(b, p);
        
        System.out.println("Shared secret calculated by Alice: " + sharedSecretA);
        System.out.println("Shared secret calculated by Bob: " + sharedSecretB);
    }
}
