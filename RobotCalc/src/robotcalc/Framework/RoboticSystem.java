
package robotcalc.Framework;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Vicente
 */
public class RoboticSystem {
    
    
    private int gradosLibertad = 0;
    
    List<Nodo> nodos;
    
    Nodo SistemaCoordenadas;
    
    public RoboticSystem(int grados){
        nodos = new ArrayList<>();
        SistemaCoordenadas = new Nodo();
        Nodo ultimoNodo = SistemaCoordenadas;
        nodos.add(ultimoNodo);
        if (grados > 0){
            gradosLibertad = grados;
            for (int i= 1;i<grados;i++){
                ultimoNodo = ultimoNodo.addNodo();
                nodos.add(ultimoNodo);
            }
                
        }
    }

    public void trasladarX(int nodo, double x){
        if (nodo < gradosLibertad && nodo >= 0){
            nodos.get(nodo).trasladarX(x);
        }
    }
    public void trasladarY(int nodo, double y){
        if (nodo < gradosLibertad && nodo >= 0){
            nodos.get(nodo).trasladarY(y);
        }
    }
    public void trasladarZ(int nodo, double z){
        if (nodo < gradosLibertad && nodo >= 0){
            nodos.get(nodo).trasladarZ(z);
        }
    }
    
    
    public void rotarX(int nodo, double x){
        if (nodo < gradosLibertad && nodo >= 0){
            nodos.get(nodo).rotarX(x);
        }
    }
    public void rotarY(int nodo, double y){
        if (nodo < gradosLibertad && nodo >= 0){
            nodos.get(nodo).rotarY(y);
        }
    }
    public void rotarZ(int nodo, double z){
        if (nodo < gradosLibertad && nodo >= 0){
            nodos.get(nodo).rotarZ(z);
        }
    }
    
    public Matriz getPos(int nodo){
        if (nodo < gradosLibertad && nodo >= 0){
            return nodos.get(nodo).getMatrizTransformacionGlobal().producto(nodos.get(nodo).getCoordenadas());
        }
        return new Matriz(0,0);
    }
    
    public Nodo getNodo(int i){
        if (i<0 || i>=nodos.size())
            return new Nodo();
        else
            return nodos.get(i);
    }
    
    public Matriz getVectorTo(int nodo, double x, double y, double z){
        if (nodo >= 0 && nodo < gradosLibertad){
            return nodos.get(nodo).getGlobalVector(x, y, z);
        }
        return new Matriz(0,0);
    }

    public Matriz getVectorTo(double x, double y, double z){
        return nodos.get(gradosLibertad-1).getGlobalVector(x, y, z);
    }
    
    public MatrizTransformacion getMatrizHomogenea(int nodo){
        if (nodo >= 0 && nodo < gradosLibertad)
            return nodos.get(nodo).getOrigen();
        else
            return new MatrizTransformacion();
    }
    
    
}
