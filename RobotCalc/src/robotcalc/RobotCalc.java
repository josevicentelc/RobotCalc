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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RoboticSystem rs = new RoboticSystem(6);
        rs.trasladarX(0, 10);
        
        System.out.println(rs.getPos(5).toString());
        rs.rotarZ(2, 90);
        
        System.out.println(rs.getPos(5).X());
        System.out.println(rs.getPos(5).Y());
        System.out.println(rs.getPos(5).Z());
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
