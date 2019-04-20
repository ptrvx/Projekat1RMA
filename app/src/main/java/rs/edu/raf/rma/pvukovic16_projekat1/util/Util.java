package rs.edu.raf.rma.pvukovic16_projekat1.util;

import java.util.Random;

public class Util {

    private static final Random RANDOM = new Random();

    public static int generateId() {
        return  RANDOM.nextInt(Integer.MAX_VALUE);
    }
}
