package rs.edu.raf.rma.pvukovic16_projekat1.util;

import android.content.Context;

import java.util.Random;

public class Util {

    private static final Random RANDOM = new Random();

    public static int generateId() {
        return  RANDOM.nextInt(Integer.MAX_VALUE);
    }

    public static float PxToDp(Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float DpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
