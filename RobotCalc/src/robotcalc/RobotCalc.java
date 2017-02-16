/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcalc;

import robotcalc.Framework.Matriz;
import robotcalc.Framework.MatrizTransformacion;

/**
 *
 * @author user
 */
public class RobotCalc {
    
    static Matriz a;
    
    static Matriz b;
    
    static Matriz b2;

    static MatrizTransformacion mt;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testTrasladar();
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
