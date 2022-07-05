package praktikum.aufgabe4;

import javafx.util.Pair;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger d;
    private BigInteger n;
    public Pair<Integer, Integer> publicK;
    public void init(){
        int p = getPrim(50, 150);
        int q = getPrim(50, 150);
        while(p == q){ // Wenn die beiden gleich sind, finde eine andere Primzahl für p.
            p = getPrim(50, 150);
        }
        n = BigInteger.valueOf((long)p*q);
        int phi = (q-1)*(p-1);
        int e;
        do {
            e = getPrim(phi,(int) (1.5*phi));
        } while(ggT(e, phi) != 1);
        BigInteger bigE = BigInteger.valueOf(e);
        BigInteger bigPhi = BigInteger.valueOf(phi);
        d = bigE.modInverse(bigPhi);
        publicK = new Pair<>(n.intValue(), e);
    }
    public int ggT(int a, int b){
        if (a <=0 || b<=0){
            throw new IllegalArgumentException("A und B müssen größer als 0 sein für die ggT!");
        }
        while (b != 0){
            int r = a&b;
            a = b;
            b=r;
        }
        return a;
    }
    public int decode (int encoded){
        BigInteger bigEncoded = BigInteger.valueOf(encoded);
        BigInteger bigDecoded = bigEncoded.modPow(d, n);
        return bigDecoded.intValue();
    }
    public int encode (int toEncode, Pair<Integer, Integer> publicKey){
        BigInteger bigE = BigInteger.valueOf(publicKey.getValue());
        BigInteger bigN = BigInteger.valueOf(publicKey.getKey());
        BigInteger bigToEncode = BigInteger.valueOf(toEncode);
        BigInteger encoded = bigToEncode.modPow(bigE, bigN);
        return encoded.intValue();
    }
    public boolean isPrim(int number){
        if (number<=1){
            return false;
        }
        for(int i=2; i<number;i++){
            if (number%i==0){
                return false;
            }
        }
        return true;
    }
    public int getPrim(int min, int max){
        if (min <=1 || max<=1 || min >= max){
            throw new IllegalArgumentException("Arguments for getPrim has to be greater than 1 and first argument has to be less than second argument!");
        }
        Random generator = new Random();
        int randomPrim;
        do {
            randomPrim = generator.nextInt(max-min+1);
            randomPrim +=min;
        } while (!isPrim(randomPrim));
        return randomPrim;
    }

}
