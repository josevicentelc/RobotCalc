/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcalc.Framework;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jose Vicente
 */
public class TMatrizTransformacionTest {
    
    public TMatrizTransformacionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setPosicion method, of class TMatrizTransformacion.
     */
    @Test
    public void testSetPosicion() {
        System.out.println("setPosicion");
        double x_ = 0.0;
        double y_ = 0.0;
        double z_ = 0.0;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.setPosicion(x_, y_, z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trasladar method, of class TMatrizTransformacion.
     */
    @Test
    public void testTrasladar() {
        System.out.println("trasladar");
        double x_ = 0.0;
        double y_ = 0.0;
        double z_ = 0.0;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.trasladar(x_, y_, z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMatriz method, of class TMatrizTransformacion.
     */
    @Test
    public void testSetMatriz() {
        System.out.println("setMatriz");
        TMatrizTransformacion otra = null;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.setMatriz(otra);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class TMatrizTransformacion.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        TMatrizTransformacion instance = new TMatrizTransformacion();
        double expResult = 0.0;
        double result = instance.getX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class TMatrizTransformacion.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        TMatrizTransformacion instance = new TMatrizTransformacion();
        double expResult = 0.0;
        double result = instance.getY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZ method, of class TMatrizTransformacion.
     */
    @Test
    public void testGetZ() {
        System.out.println("getZ");
        TMatrizTransformacion instance = new TMatrizTransformacion();
        double expResult = 0.0;
        double result = instance.getZ();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class TMatrizTransformacion.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        double x_ = 0.0;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.setX(x_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class TMatrizTransformacion.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        double y_ = 0.0;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.setY(y_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setZ method, of class TMatrizTransformacion.
     */
    @Test
    public void testSetZ() {
        System.out.println("setZ");
        double z_ = 0.0;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.setZ(z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotar method, of class TMatrizTransformacion.
     */
    @Test
    public void testRotar() {
        System.out.println("rotar");
        double x_ = 0.0;
        double y_ = 0.0;
        double z_ = 0.0;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.rotar(x_, y_, z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotarX method, of class TMatrizTransformacion.
     */
    @Test
    public void testRotarX() {
        System.out.println("rotarX");
        double x_ = 0.0;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.rotarX(x_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotarY method, of class TMatrizTransformacion.
     */
    @Test
    public void testRotarY() {
        System.out.println("rotarY");
        double y_ = 0.0;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.rotarY(y_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotarZ method, of class TMatrizTransformacion.
     */
    @Test
    public void testRotarZ() {
        System.out.println("rotarZ");
        double z_ = 0.0;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        instance.rotarZ(z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copia method, of class TMatrizTransformacion.
     */
    @Test
    public void testCopia() {
        System.out.println("copia");
        TMatrizTransformacion instance = new TMatrizTransformacion();
        TMatrizTransformacion expResult = null;
        TMatrizTransformacion result = instance.copia();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of producto method, of class TMatrizTransformacion.
     */
    @Test
    public void testProducto_TMatrizTransformacion() {
        System.out.println("producto");
        TMatrizTransformacion otra = null;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        TMatrizTransformacion expResult = null;
        TMatrizTransformacion result = instance.producto(otra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of producto method, of class TMatrizTransformacion.
     */
    @Test
    public void testProducto_TMatriz() {
        System.out.println("producto");
        TMatriz otra = null;
        TMatrizTransformacion instance = new TMatrizTransformacion();
        TMatrizTransformacion expResult = null;
        TMatrizTransformacion result = instance.producto(otra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
