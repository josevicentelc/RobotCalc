/*
 * El nodo representa a cada una de las partes de un sistema robotico
 * que se mueven como un todo. Cada una de las partes moviles de un robot
 * que se mueven sobre un origen de coordenadas
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
    protected MatrizTransformacion origen;
    
    /**
     * Nodo padre de este sobre el que se calcula el origen de coordenadas
     */
    protected Nodo parent = null;
    
    /**
     * Lista de nodos hijos
     */
    protected List<Nodo> nodos;
    
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
     * Hace una copia del nodo actual y de todos los nodos que son hijos de el
     * recreando asi una nueva estructura similar a la actual
     * @param n Nodo a partir del cual copiar
     */
    public Nodo(Nodo n){
        origen = n.getOrigen().copia();
        nodos = new ArrayList<Nodo>();
        for (int i=0;i<n.nodos.size();i++){
            nodos.add(new Nodo(n.nodos.get(i)));
        }
    }
    
    /**
     * Constructor por defecto
     */
    public Nodo(double x, double y, double z){
        origen = new MatrizTransformacion();
        nodos = new ArrayList<Nodo>();
        trasladar(x, y, z);
    }
    
    /**
     * Establece de manera absoluta la traslacion del punto TCP desde el origen de coordenadas
     * @param x Traslacion en X
     * @param y Traslacion en Y
     * @param z Traslacion en Z 
     */
    public void setTCP(double x, double y, double z){
        origen.setX(x);
        origen.setY(y);
        origen.setZ(z);
    }
    
    /**
     * Eliminar de la lista de hijos todas las referencias a la instancia
     * del nodo pasado por parametro
     * @param n Nodo a eliminar de la list ade hijos
     */
    public void quitarHijo(Nodo n){
        for (int i=nodos.size()-1;i>=0;i--){
            if (nodos.get(i) == n) nodos.remove(i);
        }
    }
    
    
    
    /**
     * Si el actual nodo es hijo de otro nodo, se separa de este
     * convirtiendose en un nodo independiente y con coordenadas en base al base al mundo
     */
    public void separarDePadre(){
        if (parent != null){
            Matriz nuevoOrigen = getGlobalVector(parent.getCoordenadas());
            parent.quitarHijo(this);
            parent = null;
            setPosicionAbsoluta(nuevoOrigen.getValue(0, 0), nuevoOrigen.getValue(1, 0), nuevoOrigen.getValue(2, 0));
        }
    }
    
    /**
     * Establece de manera absoluta las coordenadas de traslacion del origen de coordenadas
     * @param x Traslacion en X
     * @param y Traslacion en Y
     * @param z Traslacion en Z
     */
    public void setPosicionAbsoluta(double x, double y, double z){
        origen.setX(x);
        origen.setY(y);
        origen.setZ(z);
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
    public MatrizTransformacion getOrigen(){return origen;}
    
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
            Matriz t =  getVectorTo(vector);
            return parent.getGlobalVector(t);
        }
        else
            return vector;  
    }
    
    /**
     * Retorna el vector desde el punto TCP del nodo hacia el punto pasado
     * @param vector punto de destino
     * @return Vector desde TPC hacia el punto
     */
    public Matriz getVectorTo(Matriz vector){
        return origen.producto(vector);
    }
    
    /**
     * Retorna la matriz que contiene el los valores de las coordenas x,y,z del origen de coordenadas
     * @return matriz con los valores del origen de coordenadas
     */
    public Matriz getCoordenadas(){
        return origen.getCol(3);
    }    
    
    /**
     * Retorna el producto de todas las matrices de transformacion homogeneas
     * hasta llegar a la base del origen de coordenadas
     * @return Matriz de transformacion del mundo hacia el origen de coordenadas del nodo
     */
    public MatrizTransformacion getMatrizTransformacionGlobal(){
        if (parent == null){
            //System.out.println("Soy padre, retorno mi matriz");
            //System.out.println(origen.toString());
            return origen;
        }
        else{
            //System.out.println("Soy hijo, retorno mi matriz");
            MatrizTransformacion C = parent.getMatrizTransformacionGlobal().producto(origen);
            //System.out.println(C.toString());
            return C;
            
        }
    }
}
