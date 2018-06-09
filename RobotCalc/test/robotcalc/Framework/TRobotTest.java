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
public class TRobotTest {
    
    public TRobotTest() {
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
     * Test of trasladarX method, of class TRobot.
     */
    @Test
    public void testTrasladarX() {
        System.out.println("trasladarX");
        int nodo_ = 0;
        double x_ = 0.0;
        TRobot instance = null;
        instance.trasladarX(nodo_, x_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trasladarY method, of class TRobot.
     */
    @Test
    public void testTrasladarY() {
        System.out.println("trasladarY");
        int nodo_ = 0;
        double y_ = 0.0;
        TRobot instance = null;
        instance.trasladarY(nodo_, y_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trasladarZ method, of class TRobot.
     */
    @Test
    public void testTrasladarZ() {
        System.out.println("trasladarZ");
        int nodo_ = 0;
        double z_ = 0.0;
        TRobot instance = null;
        instance.trasladarZ(nodo_, z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotarX method, of class TRobot.
     */
    @Test
    public void testRotarX() {
        System.out.println("rotarX");
        int nodo_ = 0;
        double x_ = 0.0;
        TRobot instance = null;
        instance.rotarX(nodo_, x_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotarY method, of class TRobot.
     */
    @Test
    public void testRotarY() {
        System.out.println("rotarY");
        int nodo_ = 0;
        double y_ = 0.0;
        TRobot instance = null;
        instance.rotarY(nodo_, y_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotarZ method, of class TRobot.
     */
    @Test
    public void testRotarZ() {
        System.out.println("rotarZ");
        int nodo_ = 0;
        double z_ = 0.0;
        TRobot instance = null;
        instance.rotarZ(nodo_, z_);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPos method, of class TRobot.
     */
    @Test
    public void testGetPos() {
        System.out.println("getPos");
        int nodo_ = 0;
        TRobot instance = null;
        TMatriz expResult = null;
        TMatriz result = instance.getPos(nodo_);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodo method, of class TRobot.
     */
    @Test
    public void testGetNodo() {
        System.out.println("getNodo");
        int nodo_ = 0;
        TRobot instance = null;
        TNodo expResult = null;
        TNodo result = instance.getNodo(nodo_);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVectorTo method, of class TRobot.
     */
    @Test
    public void testGetVectorTo_4args() {
        System.out.println("getVectorTo");
        int nodo_ = 0;
        double x_ = 0.0;
        double y_ = 0.0;
        double z_ = 0.0;
        TRobot instance = null;
        TMatriz expResult = null;
        TMatriz result = instance.getVectorTo(nodo_, x_, y_, z_);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVectorTo method, of class TRobot.
     */
    @Test
    public void testGetVectorTo_3args() {
        System.out.println("getVectorTo");
        double x_ = 0.0;
        double y_ = 0.0;
        double z_ = 0.0;
        TRobot instance = null;
        TMatriz expResult = null;
        TMatriz result = instance.getVectorTo(x_, y_, z_);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
