/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcalc.Framework;

/**
 *
 * @author user
 */
public class Nodo {
    
    MatrizTransformacion matriz;
    
    public Nodo(){
        matriz = new MatrizTransformacion();
    }
    
    public Nodo(Nodo n){
        
    }

    public Matriz getCoordenadas(){
        return matriz.getCol(4).traspuesta();
    }    
    
}
