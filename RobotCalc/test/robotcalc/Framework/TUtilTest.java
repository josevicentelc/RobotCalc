
package robotcalc.Framework;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class TUtilTest {
    

    /**
     * Test of sameValue method, of class TUtil.
     */
    @Test
    public void testSameValue() {
        TUtil.setPrecision(3);
        if (!TUtil.sameValue(1.0000, 1.0001))   fail("Test 1: La diferencia esta dentro del margen de error");
        if (!TUtil.sameValue(1.000, 1.001))     fail("Test 2: La diferencia esta dentro del margen de error");
        if (TUtil.sameValue(1.000, 1.002))      fail("Test 3: La diferencia excede el margen de error");
    }

    /**
     * Test of degToRad method, of class TUtil.
     */
    @Test
    public void testDegToRad() {
        if (!TUtil.sameValue(TUtil.degToRad(10), 0.174533)) fail("Test 4: Error de conversion de Grados a Radianes");
        if (!TUtil.sameValue(TUtil.degToRad(125), 2.18166)) fail("Test 5: Error de conversion de Grados a Radianes");
        if (!TUtil.sameValue(TUtil.degToRad(-90), -1.5708)) fail("Test 6: Error de conversion de Grados a Radianes");
    }
    
}
