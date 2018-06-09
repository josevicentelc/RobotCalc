/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcalc.Framework;

import java.util.List;
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
public class TNodoTest {
    
    public TNodoTest() {
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
     * Test of setParent method, of class TNodo.
     */
    @Test
    public void testSetParent() {
        System.out.println("setParent");
        TNodo n = null;
        TNodo instance = new TNodo();
        instance.setParent(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTCP method, of class TNodo.
     */
    @Test
    public void testSetTCP() {
        System.out.println("setTCP");
        double x_ = 0.0;
        double y_ = 0.0;
        double z_ = 0.0;
        TNodo instance = new TNodo();
        instance.setTCP(x_, y_, z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quitarHijo method, of class TNodo.
     */
    @Test
    public void testQuitarHijo() {
        System.out.println("quitarHijo");
        TNodo n = null;
        TNodo instance = new TNodo();
        instance.quitarHijo(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of separarDePadre method, of class TNodo.
     */
    @Test
    public void testSepararDePadre() {
        System.out.println("separarDePadre");
        TNodo instance = new TNodo();
        instance.separarDePadre();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNodo method, of class TNodo.
     */
    @Test
    public void testAddNodo_TNodo() {
        System.out.println("addNodo");
        TNodo n = null;
        TNodo instance = new TNodo();
        instance.addNodo(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNodo method, of class TNodo.
     */
    @Test
    public void testAddNodo_0args() {
        System.out.println("addNodo");
        TNodo instance = new TNodo();
        TNodo expResult = null;
        TNodo result = instance.addNodo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class TNodo.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        double x_ = 0.0;
        TNodo instance = new TNodo();
        instance.setX(x_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class TNodo.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        double y_ = 0.0;
        TNodo instance = new TNodo();
        instance.setY(y_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setZ method, of class TNodo.
     */
    @Test
    public void testSetZ() {
        System.out.println("setZ");
        double z_ = 0.0;
        TNodo instance = new TNodo();
        instance.setZ(z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class TNodo.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        TNodo instance = new TNodo();
        double expResult = 0.0;
        double result = instance.getX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class TNodo.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        TNodo instance = new TNodo();
        double expResult = 0.0;
        double result = instance.getY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZ method, of class TNodo.
     */
    @Test
    public void testGetZ() {
        System.out.println("getZ");
        TNodo instance = new TNodo();
        double expResult = 0.0;
        double result = instance.getZ();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotarX method, of class TNodo.
     */
    @Test
    public void testRotarX() {
        System.out.println("rotarX");
        double x_ = 0.0;
        TNodo instance = new TNodo();
        instance.rotarX(x_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotarY method, of class TNodo.
     */
    @Test
    public void testRotarY() {
        System.out.println("rotarY");
        double y_ = 0.0;
        TNodo instance = new TNodo();
        instance.rotarY(y_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotarZ method, of class TNodo.
     */
    @Test
    public void testRotarZ() {
        System.out.println("rotarZ");
        double z_ = 0.0;
        TNodo instance = new TNodo();
        instance.rotarZ(z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotar method, of class TNodo.
     */
    @Test
    public void testRotar() {
        System.out.println("rotar");
        double x_ = 0.0;
        double y_ = 0.0;
        double z_ = 0.0;
        TNodo instance = new TNodo();
        instance.rotar(x_, y_, z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRotacionX method, of class TNodo.
     */
    @Test
    public void testGetRotacionX() {
        System.out.println("getRotacionX");
        TNodo instance = new TNodo();
        double expResult = 0.0;
        double result = instance.getRotacionX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRotacionY method, of class TNodo.
     */
    @Test
    public void testGetRotacionY() {
        System.out.println("getRotacionY");
        TNodo instance = new TNodo();
        double expResult = 0.0;
        double result = instance.getRotacionY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRotacionZ method, of class TNodo.
     */
    @Test
    public void testGetRotacionZ() {
        System.out.println("getRotacionZ");
        TNodo instance = new TNodo();
        double expResult = 0.0;
        double result = instance.getRotacionZ();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobarVector method, of class TNodo.
     */
    @Test
    public void testGetGlobarVector_3args() {
        System.out.println("getGlobarVector");
        double x_ = 0.0;
        double y_ = 0.0;
        double z_ = 0.0;
        TNodo instance = new TNodo();
        TMatriz expResult = null;
        TMatriz result = instance.getGlobarVector(x_, y_, z_);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobarVector method, of class TNodo.
     */
    @Test
    public void testGetGlobarVector_TMatriz() {
        System.out.println("getGlobarVector");
        TMatriz vector = null;
        TNodo instance = new TNodo();
        TMatriz expResult = null;
        TMatriz result = instance.getGlobarVector(vector);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobalCoordinates method, of class TNodo.
     */
    @Test
    public void testGetGlobalCoordinates_0args() {
        System.out.println("getGlobalCoordinates");
        TNodo instance = new TNodo();
        TMatriz expResult = null;
        TMatriz result = instance.getGlobalCoordinates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobalCoordinates method, of class TNodo.
     */
    @Test
    public void testGetGlobalCoordinates_3args() {
        System.out.println("getGlobalCoordinates");
        double x_ = 0.0;
        double y_ = 0.0;
        double z_ = 0.0;
        TNodo instance = new TNodo();
        TMatriz expResult = null;
        TMatriz result = instance.getGlobalCoordinates(x_, y_, z_);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVectorTo method, of class TNodo.
     */
    @Test
    public void testGetVectorTo() {
        System.out.println("getVectorTo");
        TMatriz vector = null;
        TNodo instance = new TNodo();
        TMatriz expResult = null;
        TMatriz result = instance.getVectorTo(vector);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatrizTransf method, of class TNodo.
     */
    @Test
    public void testGetMatrizTransf() {
        System.out.println("getMatrizTransf");
        TNodo instance = new TNodo();
        TMatrizTransformacion expResult = null;
        TMatrizTransformacion result = instance.getMatrizTransf();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatrizTransfGlobal method, of class TNodo.
     */
    @Test
    public void testGetMatrizTransfGlobal() {
        System.out.println("getMatrizTransfGlobal");
        TNodo instance = new TNodo();
        TMatrizTransformacion expResult = null;
        TMatrizTransformacion result = instance.getMatrizTransfGlobal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodos method, of class TNodo.
     */
    @Test
    public void testGetNodos() {
        System.out.println("getNodos");
        TNodo instance = new TNodo();
        List<TNodo> expResult = null;
        List<TNodo> result = instance.getNodos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoordenadas method, of class TNodo.
     */
    @Test
    public void testGetCoordenadas() {
        System.out.println("getCoordenadas");
        TNodo instance = new TNodo();
        TMatriz expResult = null;
        TMatriz result = instance.getCoordenadas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
