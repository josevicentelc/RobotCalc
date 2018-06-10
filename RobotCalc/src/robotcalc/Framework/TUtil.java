
package robotcalc.Framework;

/**
 *
 * @author user
 */
public class TUtil {

private static int decimalPrecision = 3;

    public static void setPrecision(int v){
        if (v >= 0)
        decimalPrecision = v;
    }

    public static int getDecimalPrecision(){
        return decimalPrecision;
    }
    
    public static boolean sameValue(double a, double b){
        double error = 1/Math.pow(10, decimalPrecision);
        return (a >= b-error && a <= b+error);
    }
    
    public static double degToRad(double a){
        return Math.toRadians(a);
    }

}
