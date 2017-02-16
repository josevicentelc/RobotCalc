/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcalc.Framework;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Vicente Lozano Copa
 */
public class Nodo {
    
    /**
     * Origen de coordenadas
     */
    MatrizTransformacion origen;
    
    /**
     * Nodo padre de este sobre el que se calcula el origen de coordenadas
     */
    Nodo parent = null;
    
    /**
     * Lista de nodos hijos
     */
    List<Nodo> nodos;
    
    /**
     * Establece el nodo que es padre de este
     * Si no hay padre, se usa la posicion del mundo
     * @param n Nodo padre
     */
    public void setParent(Nodo n){parent = n;}
    
    /**
     * Constructor por defecto
     */
    public Nodo(){
        origen = new MatrizTransformacion();
        nodos = new ArrayList<Nodo>();
    }
    
    /**
     * Añade el nodo pasado como un nodo hijo
     * @param nodo nuevo hijo
     */
    public void addNodo(Nodo nodo){
        nodos.add(nodo);
        nodo.setParent(this);
    }

    /**
     * Añade un nuevo nodo hijo y retorna una referencia a el
     * @return referencia al nuevo nodo
     */
    public Nodo addNodo(){
        Nodo n = new Nodo();
        addNodo(n);
        return n;
    }

    /**
     * Retorna la matriz transformada del origen de coordenadas
     * @return Matriz Homogenea de origen de coordenadas
     */
    public Matriz getOrigen(){return origen;}
    
    /**
     * Traslada el origen de coordenadas en el eje X
     * @param x desplazamiento en X
     */
    public void trasladarX(double x){origen.trasladarX(x);}
    
    /**
     * Traslada el origen de coordenadas en el eje Y
     * @param y desplazamiento en Y
     */
    public void trasladarY(double y){origen.trasladarY(y);}
    
    /**
     * Traslada el origen de coordenadas en el eje Z
     * @param z desplazamiento en Z
     */
    public void trasladarZ(double z){origen.trasladarZ(z);}
    
    /**
     * Traslada el origen de coordenadas en los tres ejes
     * @param x desplazamiento en X
     * @param y desplazamiento en Y
     * @param z desplazamiento en Z
     */
    public void trasladar(double x, double y, double z){ origen.trasladar(x, y, z);}
    
    /**
     * Rota el origen de coordenadas sobre el eje X
     * @param x Rotacion en X
     */
    public void rotarX(double x){origen.rotarX(x);}
    
    /**
     * Rota el origen de coordenadas sobre el eje Y
     * @param y Rotacion en Y
     */
    public void rotarY(double y){origen.rotarY(y);}
    
    /**
     * Rota el origen de coordenadas sobre el eje Z
     * @param z Rotacion en Z
     */
    public void rotarZ(double z){origen.rotarZ(z);}
    
    /**
     * Rota el origen de coordenadas sobre los tres ejes
     * @param x Rotacion en X
     * @param y Rotacion en Y
     * @param z Rotacion en Z
     */
    public void rotar(double x, double y, double z){origen.rotar(x, y, z);}
    
    /**
     * Obtiene la posicion en el mundo de un vector (x,y,z) a partir de su propio origen de coordenadas
     * @param x componente X del vector
     * @param y componente Y del vector
     * @param z componente Z del vector
     * @return posicion del vector sobre el origen de coordenadas global
     */
    public Matriz getGlobalVector(double x, double y, double z){
        Matriz m = new Matriz(4, 1);
        m.setValue(0, 0, x);
        m.setValue(1, 0, y);
        m.setValue(2, 0, z);
        m.setValue(3, 0, 1);
        return getGlobalVector(m);
    }
    
    /**
     * Metodo de uso privado a la clase que ahce llamadas recursivas a los nodos padres
     * para calcular la posicion del un vector a partir de la posicion relativa del propio origen de coordenadas
     * respecto del origen de coordenadas del padre y de su rotacion
     * 
     * La recursividad alcanza al nodo inicial que no es hijo de ningun otro
     * 
     * @param vector Vector a calcular
     * @return Posicion del vector sobre el origen de coordenandas del mundo
     */
    protected Matriz getGlobalVector(Matriz vector){
        if (parent != null){
            Matriz t =  origen.producto(vector);
            return parent.getGlobalVector(t);
        }
        else
            return vector;  
    }
    
    /**
     * Retorna la matriz que contiene el los valores de las coordenas x,y,z del origen de coordenadas
     * @return matriz con los valores del origen de coordenadas
     */
    public Matriz getCoordenadas(){
        return origen.getCol(4);
    }    
    
}
