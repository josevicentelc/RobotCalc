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
public class TMatrizTest {
    
    public TMatrizTest() {
        
    }
    


    @Test
    public void testConstructor(){
    TMatriz A = new TMatriz(3, 5);
     if ((A.RowCount() != 3) || (A.ColCount() != 5)) {
        fail("Forma de matriz incorrecta");
    }
     boolean ok = true;
     for (int I = 0; I< A.RowCount();I++)
         for (int J = 0; J< A.ColCount(); J++)
             if (A.getValue(J,I) != 0) ok = false;
     if (!ok) fail("La matriz no esta inicializada todo a 0");
    }

    @Test
    public void testConstructorCopia(){
     TMatriz A = new TMatriz(2, 1);
     A.setValue(0, 0, 1);
     A.setValue(1, 0, 2);
     TMatriz B = new TMatriz(A);
     if ((B.RowCount() != 2) || (B.ColCount() != 1)) 
         fail("Forma de matriz incorrecta");
     if ((B.getValue(0, 0) != 1) || (B.getValue(1, 0) != 2)) 
         fail("Los valores no se copiaron correctamente");
    }


    @Test
    public void testCopia(){
     TMatriz A = new TMatriz(2, 1);
     A.setValue(0, 0, 1);
     A.setValue(1, 0, 2);
     TMatriz B = A.copia();
     if ((B.RowCount() != 2) || (B.ColCount() != 1)) 
         fail(" Forma de matriz incorrecta");
     if ((B.getValue(0, 0) != 1) || (B.getValue(1, 0) != 2)) 
         fail(" Los valores no se copiaron correctamente");
    }
    
    /**
     * Test of setCero method, of class TMatriz.
     */
    @Test
    public void testSetCero() {
        TMatriz A = new TMatriz(2, 2);
        A.setCero();
        if (A.getValue(0, 0) != 0 ||A.getValue(0, 1) != 0 ||A.getValue(1, 0) != 0 ||A.getValue(1, 1) != 0 )
            fail("Valores distintos de 0");
    }

    /**
     * Test of RowCount method, of class TMatriz.
     */
    @Test
    public void testRowCount() {
        TMatriz A = new TMatriz(10, 3);
        if (A.RowCount() != 10) fail("Numero de filas de la matriz incorrectas");
    }

    /**
     * Test of ColCount method, of class TMatriz.
     */
    @Test
    public void testColCount() {
        TMatriz A = new TMatriz(10, 3);
        if (A.ColCount() != 3) fail("Numero de columnas de la matriz incorrectas");
    }

    /**
     * Test of getValue method, of class TMatriz.
     */
    @Test
    public void testGetSetValue() {
        TMatriz A = new TMatriz(3,2);
        A.setValue(0, 0, 1); A.setValue(0, 1, 2); 
        A.setValue(1, 0, 3); A.setValue(1, 1, 4); 
        A.setValue(2, 0, 5); A.setValue(2, 1, 6); 
        
        if (A.getValue(0,0) != 1) fail("Valor obtenido inorrecto");
        if (A.getValue(2,1) != 6) fail("Valor obtenido inorrecto");
        if (A.getValue(-1,0) != 0) fail("Valor obtenido inorrecto");
        if (A.getValue(0, -1) != 0) fail("Valor obtenido inorrecto");
    }


    /**
     * Test of equals method, of class TMatriz.
     */
    @Test
    public void testEquals() {
     TMatriz A = new TMatriz(2, 1);
     TMatriz B = new TMatriz(2, 1);
     A.setValue(0,0,1); A.setValue(1,0,2);
     B.setValue(0,0,1); B.setValue(1,0,2);
     
     if (!A.equals(B)) fail("Dos matrices iguales dan Equals = false");
     B.setValue(0,0,3); B.setValue(1,0,4);
     if (A.equals(B)) fail("test 08: Dos matrices diferentes dan Equals = true");
    }

    /**
     * Test of getCol method, of class TMatriz.
     */
    @Test
    public void testGetCol() {
     TMatriz A = new TMatriz(3, 3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,6);
     A.setValue(2,0,7);A.setValue(2,1,8);A.setValue(2,2,9);
     TMatriz B = A.getCol(1);
     if (B == null) fail("GetCol ha retornado una matriz nula");
     else{
            if ((B.RowCount() != 3) || (B.ColCount() != 1)) fail("test 13: GetCol ha retornado una matriz con forma inesperada");
            else
            {
                 if ((B.getValue(0,0) != 2) || (B.getValue(1, 0) != 5) || (B.getValue(2,0) != 8))
                 {
                  fail("test 14: GetRow ha retornado una matriz con con valores ineperados");
                 }
            }
     }
    }

    /**
     * Test of getRow method, of class TMatriz.
     */
    @Test
    public void testGetRow() {
     TMatriz A = new TMatriz(3, 3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,6);
     A.setValue(2,0,7);A.setValue(2,1,8);A.setValue(2,2,9);
     TMatriz B = A.getRow(1);
     if (B == null) fail("test 09: GetRow ha retornado una matriz nula");
     else
        {
            if ((B.RowCount() != 1) || (B.ColCount() != 3)) fail("GetRow ha retornado una matriz con forma inesperada");
            else
            {
                 if ((B.getValue(0,0) != 4) || (B.getValue(0, 1) != 5) || (B.getValue(0,2)) != 6) 
                 {
                  fail("test 11: GetRow ha retornado una matriz con con valores ineperados");
                 }
            }
        }
    }

    /**
     * Test of traspuesta method, of class TMatriz.
     */
    @Test
    public void testTraspuesta() {
     TMatriz A = new TMatriz(2,3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,6);
     TMatriz B = A.traspuesta();
     if ((B.RowCount() != 3) || (B.ColCount()!=2)) 
        fail("La forma de la matriz transpuesta no es correcta");
     else
     {
          if (B.getValue(0,0) != 1 || B.getValue(0,1) != 4
          || (B.getValue(1,0) != 2) || (B.getValue(1,1) != 5)
          || (B.getValue(2,0) != 3) || (B.getValue(2,1) != 6) )
          fail(" La matriz traspuesta tiene valores inesperados");
     };
    }

    /**
     * Test of producto method, of class TMatriz.
     */
    @Test
    public void testProducto_TMatriz() {
     TMatriz A = new TMatriz(2,3);
     TMatriz B = new TMatriz(3,2);

     A.setValue(0,0,1);A.setValue(0,1,0);A.setValue(0,2,0);
     A.setValue(1,0,3);A.setValue(1,1,4);A.setValue(1,2,2);

     B.setValue(0,0,2);B.setValue(0,1,1);
     B.setValue(1,0,0);B.setValue(1,1,3);
     B.setValue(2,0,1);B.setValue(2,1,0);

     TMatriz C = A.producto(B);
     if (C.RowCount() != 2 || C.ColCount()!=2)  fail("test 18: Producto A*B ha retornado una matriz de forma incorrecta");
     else
     {
          if (C.getValue(0,0) != 2 || C.getValue(0,1) != 1
          || C.getValue(1,0) != 8 || C.getValue(1,1) != 15) 
          fail("test 19: Producto A*B ha retornado valores incorrectos");
     };
     C = B.producto(A);
     if (C.RowCount() != 3 || C.ColCount()!=3)  fail("test 20: Producto B*A ha retornado una matriz de forma incorrecta");
     else
     {
          if (C.getValue(0,0) != 5 || C.getValue(0,1) != 4 || C.getValue(0,2) != 2
          || C.getValue(1,0) != 9 || C.getValue(1,1) != 12 || C.getValue(1,2) != 6
          || C.getValue(2,0) != 1 || C.getValue(2,1) != 0 || C.getValue(2,2) != 0) 
          fail("test 21: Producto B*A ha retornado valores incorrectos");
     };
 
    }

    /**
     * Test of suma method, of class TMatriz.
     */
    @Test
    public void testSuma() {
     TMatriz A = new TMatriz(2,3);
     TMatriz B = new TMatriz(2,3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,7);

     B.setValue(0,0,2);B.setValue(0,1,5);B.setValue(0,2,8);
     B.setValue(1,0,3);B.setValue(1,1,7);B.setValue(1,2,9);

     TMatriz C = A.suma(B);
     if (C.RowCount() != 2 || C.ColCount()!=3)  fail("test 15: suma de matrices ha retornado una matriz de forma inesperada");
     else
     {
      if (C.getValue(0, 0) != 3 || C.getValue(0, 1) != 7 || C.getValue(0, 2) != 11
      || C.getValue(1, 0) != 7 || C.getValue(1, 1) != 12 || C.getValue(1, 2) != 16) 
      fail("test 16: suma de matrices ha retornado valores inesperados");
     }
    
    }

    /**
     * Test of resta method, of class TMatriz.
     */
    @Test
    public void testResta() {
     TMatriz A = new TMatriz(2,3);
     TMatriz B = new TMatriz(2,3);
     A.setValue(0,0,1);A.setValue(0,1,2);A.setValue(0,2,3);
     A.setValue(1,0,4);A.setValue(1,1,5);A.setValue(1,2,7);

     B.setValue(0,0,2);B.setValue(0,1,5);B.setValue(0,2,8);
     B.setValue(1,0,3);B.setValue(1,1,7);B.setValue(1,2,9);

     TMatriz C = A.resta(B);
     if (C.RowCount() != 2 || C.ColCount()!=3)  fail("resta de matrices ha retornado una matriz de forma inesperada");
     else
     {
      if (C.getValue(0, 0) != -1 || C.getValue(0, 1) != -3 || C.getValue(0, 2) != -5
      || C.getValue(1, 0) != 1 || C.getValue(1, 1) != -2 || C.getValue(1, 2) != -2) 
      fail("resta de matrices ha retornado valores inesperados");
     }
    }

    /**
     * Test of setIdentidad method, of class TMatriz.
     */
    @Test
    public void testSetIdentidad() {
        TMatriz A = new TMatriz(2, 3);
        A.setIdentidad();
        if (A.getValue(0,0) != 1 || A.getValue(1,1) != 1) fail("test 06: Matriz identidad incorrecta");
        if (A.getValue(0,1) != 0 || A.getValue(1,0) != 0) fail("test 06: Matriz identidad incorrecta");
    }

    /**
     * Test of absoluteMatrix method, of class TMatriz.
     */
    @Test
    public void testAbsoluteMatrix() {
     TMatriz A = new TMatriz(2,2);
     A.setValue(0,0,1);A.setValue(0,1,-2);
     A.setValue(1,0,0);A.setValue(1,1, 2.5);
     TMatriz B = A.absoluteMatrix();
     if (B.RowCount() != 2 || B.ColCount()!=2) 
        fail("text 24: La matriz absoluta tiene una forma incorrecta");
     else
     {
          if (B.getValue(0,0) != 1 || B.getValue(0,1) != 2
             || B.getValue(1,0) != 0 || B.getValue(1,1) != 2.5) 
          fail("test 25: La matriz absoluta tiene valores inesperados");
     };    }

    

    /**
     * Test of intercambioFilas method, of class TMatriz.
     */
    @Test
    public void testIntercambioFilas() {
    }

    /**
     * Test of setRow method, of class TMatriz.
     */
    @Test
    public void testSetRow_int_doubleArr() {
    }

    /**
     * Test of setRow method, of class TMatriz.
     */
    @Test
    public void testSetRow_int_TMatriz() {
        TMatriz A=new TMatriz(1,3);
        A.setValue(0,0,2);A.setValue(0,1,3);A.setValue(0,2,4);
        TMatriz B=new TMatriz(3,3);
        B.setRow(1, A);
        if (B.getValue(0,0) != 0 || B.getValue(0,1) != 0 || B.getValue(0,2) != 0
            || B.getValue(1,0) != 2 || B.getValue(1,1) != 3 || B.getValue(1,2) != 4
            || B.getValue(2,0) != 0 || B.getValue(2,1) != 0 || B.getValue(2,2) != 0) 
       fail("test 26: SetRow(Matriz) produjo valores insperados");        
    }

    /**
     * Test of reordenarParaReducir method, of class TMatriz.
     */
    @Test
    public void testReordenarParaReducir() {
     TMatriz A=new TMatriz(3,4);
     A.setValue(0,0,3);A.setValue(0,1,-1);A.setValue(0,2,8);A.setValue(0,3,0);
     A.setValue(1,0,2);A.setValue(1,1,0);A.setValue(1,2,5);A.setValue(1,3,9);
     A.setValue(2,0,-4);A.setValue(2,1,3);A.setValue(2,2,1);A.setValue(2,3,0);
     TMatriz B = A.reordenarParaReducir();

    }

    /**
     * Test of eliminarImprecision method, of class TMatriz.
     */
    @Test
    public void testEliminarImprecision() {
    }

    /**
     * Test of reducir method, of class TMatriz.
     */
    @Test
    public void testReducir() {
     TMatriz A=new TMatriz(3,4);
     A.setValue(0,0,3);A.setValue(0,1,-1);A.setValue(0,2,8);A.setValue(0,3,0);
     A.setValue(1,0,2);A.setValue(1,1,0);A.setValue(1,2,5);A.setValue(1,3,5);
     A.setValue(2,0,-4);A.setValue(2,1,3);A.setValue(2,2,1);A.setValue(2,3,0);
     TMatriz B=A.reducir();
     double n1 = B.getValue(0,3);
     double n2 = B.getValue(1,3);
     double n3 = B.getValue(2,3);
     if (n1 != 5 || n2 != 7  || n3 != -1)
     {
        fail("Reducir matriz produjo valores inesperados");
     };

    }

    /**
     * Test of escalonada method, of class TMatriz.
     */
    @Test
    public void testEscalonada() {
     TMatriz A=new TMatriz(3,4);
     A.setValue(0,0,3);A.setValue(0,1,-1);A.setValue(0,2,8);A.setValue(0,3,0);
     A.setValue(1,0,2);A.setValue(1,1,0);A.setValue(1,2,5);A.setValue(1,3,9);
     A.setValue(2,0,-4);A.setValue(2,1,3);A.setValue(2,2,1);A.setValue(2,3,0);
     TMatriz B=A.escalonada();

    }

    /**
     * Test of subMatriz method, of class TMatriz.
     */
    @Test
    public void testSubMatriz() {
     TMatriz A=new TMatriz(4, 4);
     A.setValue(0,0,0);A.setValue(0,1,0);A.setValue(0,2,0);A.setValue(0,3,0);
     A.setValue(1,0,1);A.setValue(1,1,2);A.setValue(1,2,3);A.setValue(1,3,0);
     A.setValue(2,0,4);A.setValue(2,1,5);A.setValue(2,2,6);A.setValue(2,3,0);
     A.setValue(3,0,0);A.setValue(3,1,0);A.setValue(3,2,0);A.setValue(3,3,0);
     TMatriz B=A.subMatriz(1,0,2,2);
     if (B.RowCount()!=2 || B.ColCount()!=3) 
     {
        fail("Submatriz produjo una matriz con forma inesperada");
     }
     else
     {
      if (B.getValue(0,0)!=1 || B.getValue(0,1)!=2 || B.getValue(0,2)!=3
        || B.getValue(1,0)!=4 || B.getValue(1,1)!=5 || B.getValue(1,2)!=6) 
      {
         fail("test 29: Submatriz produjo una matriz con valores ineperados");
         fail(B.ToString()) ;
      };
     };

    }

    
    /**
     * Test of ToString method, of class TMatriz.
     */
    @Test
    public void testToString_0args() {
    }

    /**
     * Test of ToString method, of class TMatriz.
     */
    @Test
    public void testToString_int() {
    }

    /**
     * Test of esInvertible method, of class TMatriz.
     */
    @Test
    public void testEsInvertible() {
     TMatriz A=new TMatriz(3,4);
     if (A.esInvertible())  fail("Una matriz no cuadrada no puede ser invertible");
     A=new TMatriz(3,3);
     if (A.esInvertible())  fail("Una matriz ccuadra con determinante 0 no es invertible");
     A=new TMatriz(3,3);
     A.setValue(0,0,2);A.setValue(0,1,5);A.setValue(0,2,6);
     A.setValue(1,0,1);A.setValue(1,1,2);A.setValue(1,2,3);
     A.setValue(2,0,4);A.setValue(2,1,4);A.setValue(2,2,8);
     if (!A.esInvertible())  fail("Una matriz ccuadra con determinante !=0 es invertible");
    }

    /**
     * Test of adjunta method, of class TMatriz.
     */
    @Test
    public void testAdjunta_0args() {
     TMatriz A=new TMatriz(3,3);
     A.setValue(0, 0, 0);A.setValue(0, 1, 1);A.setValue(0, 2, 0);
     A.setValue(1, 0, 1);A.setValue(1, 1, 0);A.setValue(1, 2, 0);
     A.setValue(2, 0, 1);A.setValue(2, 1, 0);A.setValue(2, 2, 1);
     TMatriz B=A.adjunta();
     if (B.RowCount()!=3 || B.ColCount()!=3) 
        fail("Matriz adjunta ha retornado una matriz de tamaño inesperado");
     else
     {
          if (B.getValue(0, 0) != 0 || B.getValue(0, 1) != -1 || B.getValue(0, 2) != 0
             || B.getValue(1, 0) != -1 || B.getValue(1, 1) != 0 || B.getValue(1, 2) != 1
             || B.getValue(2, 0) != 0 || B.getValue(2, 1) != 0 || B.getValue(2, 2) !=  -1) 
             fail("test 41: Matriz adjunta ha retornado una matriz con valores inesperados");
     };

    }

    /**
     * Test of adjunta method, of class TMatriz.
     */
    @Test
    public void testAdjunta_int_int() {
     TMatriz A=new TMatriz(3,4);
     A.setValue(0, 0, 1);A.setValue(0, 1, 2);A.setValue(0, 2, 3);A.setValue(0, 3, 4);
     A.setValue(1, 0, 5);A.setValue(1, 1, 6);A.setValue(1, 2, 7);A.setValue(1, 3, 8);
     A.setValue(2, 0, 9);A.setValue(2, 1, 10);A.setValue(2, 2, 11);A.setValue(2, 3, 12);
     TMatriz B=A.adjunta(0, 0);
     if (B.RowCount()!=2 || B.ColCount()!=3) 
        fail("Matriz adjunta(f, c) ha retornado una matriz de tamaño inesperado");
     else
     {
          if (B.getValue(0, 0) != 6 || B.getValue(0, 1) != 7 || B.getValue(0, 2) != 8 ||
             B.getValue(1, 0) != 10 || B.getValue(1, 1) != 11 || B.getValue(1, 2) != 12) 
             fail("Matriz adjunta(f, c) ha retornado una matriz con valores inesperados");
     };

    }

    /**
     * Test of inversa method, of class TMatriz.
     */
    @Test
    public void testInversa() {
     TMatriz A=new TMatriz(3,3);
     A.setValue(0,0,2);A.setValue(0,1,5);A.setValue(0,2,6);
     A.setValue(1,0,1);A.setValue(1,1,2);A.setValue(1,2,3);
     A.setValue(2,0,4);A.setValue(2,1,4);A.setValue(2,2,8);
     TMatriz B=A.inversa();
     if (B.RowCount()!=3 || B.ColCount()!=3) 
        fail("Inversa produjo una matriz de forma inesperada");
     else
     {
          if (B.getValue(0, 0)!= 1 || B.getValue(0, 1) != -4 || B.getValue(0, 2) != 0.75
          || B.getValue(1, 0)!= 1 || B.getValue(1, 1) != -2 || B.getValue(1, 2) != 0
          || B.getValue(2, 0)!= -1 || B.getValue(2, 1) != 3 || B.getValue(2, 2) != -0.25)
          
          {
             fail("Inversa produjo una matriz con valores inesperados \n"+B.ToString(2));
          }
     }
    }

    /**
     * Test of determinante method, of class TMatriz.
     */
    @Test
    public void testDeterminante() {
        //No cuadrada            //error
        TMatriz A = new TMatriz(2,3);
        A.setIdentidad();
        if (A.determinante() != 0)  fail("El determinante de una matriz no cuadrada es 0");

        //1x1
        A = new TMatriz(1,1);
        A.setIdentidad();
        if (A.determinante() != 1)  fail("El determinante de una matriz 1x1 es el valor de V(0,0)");

        //2x2
        A = new TMatriz(2,2);
        A.setValue(0,0, 1); A.setValue(0,1,2);
        A.setValue(1,0, 3); A.setValue(1,1,4);
        if (A.determinante() != -2)  fail("test 44: El determinante de una matriz 2x2 arrojo un resultado inesperado ");
        //3x3

        A = new TMatriz(3,3);       //error
        A.setValue(0,0, 1); A.setValue(0,1,2); A.setValue(0,2,3);
        A.setValue(1,0, 4); A.setValue(1,1,5); A.setValue(1,2,4);
        A.setValue(2,0, 7); A.setValue(2,1,8); A.setValue(2,2,1);
        if (A.determinante() != 12)  fail("test 45: El determinante de una matriz 3x3 arrojo un resultado inesperado ");

        //NxN
        A = new TMatriz(4,4);       //error
        A.setValue(0,0, 2); A.setValue(0,1,3); A.setValue(0,2,-2);A.setValue(0,3,4);
        A.setValue(1,0, 3); A.setValue(1,1,-2); A.setValue(1,2,1);A.setValue(1,3,2);
        A.setValue(2,0, 3); A.setValue(2,1, 2); A.setValue(2,2,3);A.setValue(2,3,4);
        A.setValue(3,0,-2); A.setValue(3,1, 4); A.setValue(3,2,0);A.setValue(3,3,5);
        if (A.determinante() !=-286)  fail("test 46: El determinante de una matriz 4x4 arrojo un resultado inesperado ");
    }

    /**
     * Test of producto method, of class TMatriz.
     */
    @Test
    public void testProducto_double() {
    }

    /**
     * Test of division method, of class TMatriz.
     */
    @Test
    public void testDivision() {
    }

    /**
     * Test of getX method, of class TMatriz.
     */
    @Test
    public void testGetX() {
     TMatriz A= new TMatriz(4,4);
     A.setValue(0, 3, 1);
     A.setValue(1, 3, 2);
     A.setValue(2, 3, 3);
	if (A.getX() != 1)  fail("getX retorno un valor inesperado");
    }

    /**
     * Test of getY method, of class TMatriz.
     */
    @Test
    public void testGetY() {
     TMatriz A= new TMatriz(4,4);
     A.setValue(0, 3, 1);
     A.setValue(1, 3, 2);
     A.setValue(2, 3, 3);
     if (A.getY() != 2)  fail("test 48: getY retorno un valor inesperado");

    }

    /**
     * Test of getZ method, of class TMatriz.
     */
    @Test
    public void testGetZ() {
     TMatriz A= new TMatriz(4,4);
     A.setValue(0, 3, 1);
     A.setValue(1, 3, 2);
     A.setValue(2, 3, 3);
     if (A.getZ() != 3)  fail("getZ retorno un valor inesperado");
    }
    
}
