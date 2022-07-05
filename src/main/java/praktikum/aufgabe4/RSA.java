package praktikum.aufgabe4;

import java.util.Random;

public class RSA {
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
    public int getPrim(){
        Random generator = new Random();
        int randomPrim;
        do {
            randomPrim = generator.nextInt(101);
            randomPrim +=50;
        } while (!isPrim(randomPrim));
        return randomPrim;
    }

}
