/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcalc;

import robotcalc.Framework.Matriz;
import robotcalc.Framework.MatrizTransformacion;
import robotcalc.Framework.Nodo;
import robotcalc.Framework.RoboticSystem;

/**
 *
 * @author user
 */
public class RobotCalc {
    
    static Matriz a;
    
    static Matriz b;
    
    static Matriz b2;

    static MatrizTransformacion mt;
    
    public static void testDeterminante(){
        Matriz v = new Matriz(3,3);
        v.setValue(0, 0, 1);v.setValue(0, 1, 2);v.setValue(0, 2, -1);
        v.setValue(1, 0, 1);v.setValue(1, 1, 3);v.setValue(1, 2, 4);
        v.setValue(2, 0, -1);v.setValue(2, 1, 4);v.setValue(2, 2, 0);
        System.out.println(v.determinante());

        Matriz m = new Matriz(4, 4);
        m.setValue(0, 0, 1);m.setValue(0, 1, -1);m.setValue(0, 2, 2);m.setValue(0, 3, 3);
        m.setValue(1, 0, 2);m.setValue(1, 1, 1);m.setValue(1, 2, 0);m.setValue(1, 3, 1);
        m.setValue(2, 0, 3);m.setValue(2, 1, -1);m.setValue(2, 2, 1);m.setValue(2, 3, 2);
        m.setValue(3, 0, 2);m.setValue(3, 1, -1);m.setValue(3, 2, 0);m.setValue(3, 3, 1);
        System.out.println(m.determinante());

        m.setValue(0, 0, 2);m.setValue(0, 1, 3);m.setValue(0, 2, -2);m.setValue(0, 3, 4);
        m.setValue(1, 0, 3);m.setValue(1, 1, -2);m.setValue(1, 2, 1);m.setValue(1, 3, 2);
        m.setValue(2, 0, 3);m.setValue(2, 1, 2);m.setValue(2, 2, 3);m.setValue(2, 3, 4);
        m.setValue(3, 0, -2);m.setValue(3, 1, 4);m.setValue(3, 2, 0);m.setValue(3, 3, 5);
        System.out.println(m.determinante());
    }

    public static void testReducida(){
        double[] ma = {1, -2, 3, 9};
        double[] mb = {-1, 3, 0, -4};
        double[] mc = {2, -5, 5, 17};
        
        Matriz mm = new Matriz(3, 4);
        mm.setRow(0, ma);
        mm.setRow(1, mb);
        mm.setRow(2, mc);
        System.out.println(mm.toString());
        System.out.println(mm.escalonada().toString());
        
        
    }
    
    public static void testInversa(){
        Matriz v = new Matriz(3,3);
        v.setValue(0, 0, 2);v.setValue(0, 1, 0);v.setValue(0, 2, 1);
        v.setValue(1, 0, 3);v.setValue(1, 1, 0);v.setValue(1, 2, 0);
        v.setValue(2, 0, 5);v.setValue(2, 1, 1);v.setValue(2, 2, 1);
        System.out.println(v.inversa().toString());
    }
    
    
    public static void testCoordenadas(){
        RoboticSystem rs = new RoboticSystem(6);
        rs.trasladarX(0, 10);
        System.out.println(rs.getPos(5).toString());
        rs.rotarZ(2, 90);
        System.out.println(rs.getPos(5).X());
        System.out.println(rs.getPos(5).Y());
        System.out.println(rs.getPos(5).Z());
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testReducida();
    }

    
    public static void calculaVectorTrasladado(){
        Nodo n1 = new Nodo();
        Nodo n2 = new Nodo();
        n1.addNodo(n2);
        n2.trasladar(6, -3, 8);
        Matriz s = n2.getGlobalVector(-2, 7, 3);
        System.out.println(s.toString());
    }
    
    public static void calculaVectorRotado(){
        Nodo n1 = new Nodo();
        Nodo n2 = n1.addNodo();
        n1.addNodo(n2);
        n2.rotarZ(-90);
        System.out.println(n2.getGlobalVector(4, 8, 12));
    }
    
    public static void testRotar(){
        
        mt = new MatrizTransformacion();
        mt.rotarX(90);
        System.out.println(mt.toString());
        mt.rotarY(180);
        System.out.println(mt.toString());
        mt.rotarX(-90);
        System.out.println(mt.toString());
    }
    
    
    
    public static void testMT(){
        mt = new MatrizTransformacion();
        System.out.println(mt.toString());
    }
    
    public static void testTrasladar(){
        mt = new MatrizTransformacion();
        mt.trasladar(10, 15, 20);
        System.out.println(mt.toString());
    }

    public static void testRowCol(){
        init();
        System.out.println(a.toString());
        System.out.println(a.getCol(2).toString());
        System.out.println(a.getRow(2).toString());
    }

    
    public static void testTraspuesta(){
        init();
        Matriz C = b.traspuesta();
        System.out.println(b);
        System.out.println(C);
    }
    
    public static void testProducto(){
        init();
        Matriz C = a.producto(b);
        System.out.println(C.toString());
    }
    
    public static void testProducto2(){
        init();
        Matriz C = a.producto(b2);
        System.out.println(C.toString());
    }

    
    
    public static void testSuma(){
        init();
        Matriz C = a.suma(b);
        System.out.println(C.toString());
    }
    
    
    
    public static void init(){
        a = new Matriz(3, 3);
        a.setValue(0, 0, 2);
        a.setValue(0, 1, 0);
        a.setValue(0, 2, 1);
        a.setValue(1, 0, 3);
        a.setValue(1, 1, 0);
        a.setValue(1, 2, 0);
        a.setValue(2, 0, 5);
        a.setValue(2, 1, 1);
        a.setValue(2, 2, 1);
        
        b = new Matriz(3, 3);
        b.setValue(0, 0, 1);
        b.setValue(0, 1, 0);
        b.setValue(0, 2, 1);
        b.setValue(1, 0, 1);
        b.setValue(1, 1, 2);
        b.setValue(1, 2, 1);
        b.setValue(2, 0, 1);
        b.setValue(2, 1, 1);
        b.setValue(2, 2, 0);

        b2 = new Matriz(3, 1);
        b2.setValue(0, 0, 1);
        b2.setValue(1, 0, 0);
        b2.setValue(2, 0, 1);
        
    }
}
