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
    
    @Test
    public void testConstructor() {
        TMatrizTransformacion matriz = new TMatrizTransformacion();
        if (matriz.RowCount() != 4 || matriz.ColCount() != 4) fail("Matriz de transformacion con una forma inesperada");
        else{
            for (int i=0;i<4;i++)
                for (int j = 0;j<4;j++)
                    if (i==j && matriz.getValue(i, j) != 1) fail("La matriz recien creada no es una matriz identidad");
                    else
                        if (i!=j && matriz.getValue(i, j) != 0) fail("La matriz recien creada no es una matriz identidad");
        }
    }

    @Test
    public void testConstructorCopy() {
        TMatrizTransformacion matriz = new TMatrizTransformacion();
        matriz.setValue(0, 0, 1);
        matriz.setValue(1, 1, 2);
        matriz.setValue(2, 1, 3);
        TMatrizTransformacion matriz2 = new TMatrizTransformacion(matriz);
        if (matriz2.getValue(0, 0) != 1 || matriz2.getValue(1, 1) != 2 ||matriz2.getValue(2, 1) != 3 )
            fail("La matriz no se copio correctamente");
    }

    /**
     * Test of copia method, of class TMatrizTransformacion.
     */
    @Test
    public void testCopia() {
        TMatrizTransformacion matriz = new TMatrizTransformacion();
        matriz.setValue(0, 0, 1);
        matriz.setValue(1, 1, 2);
        matriz.setValue(2, 1, 3);
        TMatrizTransformacion matriz2 = matriz.copia();
        if (matriz2.getValue(0, 0) != 1 || matriz2.getValue(1, 1) != 2 ||matriz2.getValue(2, 1) != 3 )
            fail("La matriz no se copio correctamente");
    }
    
    /**
     * Test of trasladar method, of class TMatrizTransformacion.
     */
    @Test
    public void testTrasladar() {
        TMatrizTransformacion matriz = new TMatrizTransformacion();
        matriz.trasladar(10,20,30);
        if (matriz.getValue(0, 3) != 10) fail("X No se traslado correctamente");
        if (matriz.getValue(1, 3) != 20) fail("Y No se traslado correctamente");
        if (matriz.getValue(2, 3) != 30) fail("Z No se traslado correctamente");
    }

    /**
     * Test of setMatriz method, of class TMatrizTransformacion.
     */
    @Test
    public void testSetMatriz() {
        TMatrizTransformacion m1 = new TMatrizTransformacion();
        m1.setValue(0, 0, 1); m1.setValue(0, 1, 2); m1.setValue(0, 2, 3); m1.setValue(0, 3, 4); 
        m1.setValue(1, 0, 5); m1.setValue(1, 1, 6); m1.setValue(1, 2, 7); m1.setValue(1, 3, 8); 
        m1.setValue(2, 0, 9); m1.setValue(2, 1, 10); m1.setValue(2, 2, 11); m1.setValue(2, 3, 12); 
        m1.setValue(3, 0, 13); m1.setValue(3, 1, 14); m1.setValue(3, 2, 15); m1.setValue(3, 3, 16); 
        TMatrizTransformacion m2 = new TMatrizTransformacion();
        m2.setMatriz(m1);
        if (!m1.equals(m2)) fail("Set matriz no copio los valores correctamente");
        
    }

    /**
     * Test of getX method, of class TMatrizTransformacion.
     */
    @Test
    public void testGetSetX() {
        TMatrizTransformacion m1 = new TMatrizTransformacion();
        m1.setX(10);
        if (m1.getX()!= 10) fail("Valor de X incorrecto");
        if (m1.getY()!= 0) fail("Valor de Y incorrecto");
        if (m1.getZ()!= 0) fail("Valor de Z incorrecto");
    }

    /**
     * Test of getY method, of class TMatrizTransformacion.
     */
    @Test
    public void testGetY() {
        TMatrizTransformacion m1 = new TMatrizTransformacion();
        m1.setY(10);
        if (m1.getX()!= 0) fail("Valor de X incorrecto");
        if (m1.getY()!= 10) fail("Valor de Y incorrecto");
        if (m1.getZ()!= 0) fail("Valor de Z incorrecto");
    }

    /**
     * Test of getZ method, of class TMatrizTransformacion.
     */
    @Test
    public void testGetZ() {
        TMatrizTransformacion m1 = new TMatrizTransformacion();
        m1.setZ(10);
        if (m1.getX()!= 0) fail("Valor de X incorrecto");
        if (m1.getY()!= 0) fail("Valor de Y incorrecto");
        if (m1.getZ()!= 10) fail("Valor de Z incorrecto");
    }


    /**
     * Test of rotar method, of class TMatrizTransformacion.
     */
    @Test
    public void testRotar() {
    }

    /**
     * Test of rotarX method, of class TMatrizTransformacion.
     */
    @Test
    public void testRotarX() {
        TMatrizTransformacion m1 = new TMatrizTransformacion();
        m1.trasladar(4, 8, 12);
        m1.rotarZ(-90);
        if ( !TUtil.sameValue(m1.getX(), 8)) fail("Tras la rotacion se esperaba X=8, se obtiene " + m1.getX());
        if ( !TUtil.sameValue(m1.getY(), -4)) fail("Tras la rotacion se esperaba Y=-4, se obtiene " + m1.getY());
        if ( !TUtil.sameValue(m1.getZ(), 12)) fail("Tras la rotacion se esperaba Z=12, se obtiene " + m1.getZ());
    }

    /**
     * Test of rotarY method, of class TMatrizTransformacion.
     */
    @Test
    public void testRotarY() {
        TMatrizTransformacion m1 = new TMatrizTransformacion();
        m1.trasladar(4, 8, 12);
        m1.rotarX(-90);
        //if ( !TUtil.sameValue(m1.getX(), 8)) fail("Tras la rotacion se esperaba X=8, se obtiene " + m1.getX());
        //if ( !TUtil.sameValue(m1.getY(), -4)) fail("Tras la rotacion se esperaba Y=-4, se obtiene " + m1.getY());
        //if ( !TUtil.sameValue(m1.getZ(), 12)) fail("Tras la rotacion se esperaba Z=12, se obtiene " + m1.getZ());
    }

    /**
     * Test of rotarZ method, of class TMatrizTransformacion.
     */
    @Test
    public void testRotarZ() {
        TMatrizTransformacion m1 = new TMatrizTransformacion();
        m1.trasladar(4, 8, 12);
        m1.rotarY(-90);
        //if ( !TUtil.sameValue(m1.getX(), 8)) fail("Tras la rotacion se esperaba X=8, se obtiene " + m1.getX());
        //if ( !TUtil.sameValue(m1.getY(), -4)) fail("Tras la rotacion se esperaba Y=-4, se obtiene " + m1.getY());
        //if ( !TUtil.sameValue(m1.getZ(), 12)) fail("Tras la rotacion se esperaba Z=12, se obtiene " + m1.getZ());
    }


    /**
     * Test of producto method, of class TMatrizTransformacion.
     */
    @Test
    public void testProducto_TMatrizTransformacion() {
    }

    /**
     * Test of producto method, of class TMatrizTransformacion.
     */
    @Test
    public void testProducto_TMatriz() {
    }
    
}
