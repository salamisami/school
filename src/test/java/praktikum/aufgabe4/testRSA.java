package praktikum.aufgabe4;

import org.junit.jupiter.api.Test;
import praktikum.aufgabe2.Constants;
import praktikum.aufgabe2.Mergesort;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testRSA {

        @Test
        public void testRSA() {
            for (int i=0; i>100; i++){
               RSA rsa = new RSA();
               Random generator = new Random();
               int toEncode = generator.nextInt(100);
               int encoded;
               rsa.init();
               encoded = rsa.encode(toEncode, rsa.publicK);
               encoded = rsa.decode(encoded);
               assertEquals(toEncode, encoded);
            }

        }
}
