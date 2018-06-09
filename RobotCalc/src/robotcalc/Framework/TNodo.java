
package robotcalc.Framework;

import java.util.List;

/**
 *
 * @author Jose Vicente
 */
public class TNodo {

    
    private List<TNodo> nodos;
    
    private TNodo parent = null;
    
    private double rotx = 0;
    
    private double roty = 0;
    
    private double rotz = 0;
    
    private double trax = 0;
    
    private double tray = 0;
    
    private double traz = 0;
    

    public TNodo(){
        
    }
    
    public TNodo(TNodo otro){
        
    }
    
    public TNodo(double x_, double y_, double z_){
        
    }

    public void setParent(TNodo n){
        
    }
    
    public void setTCP(double x_, double y_, double z_){
        
    }
        
    public void quitarHijo(TNodo n){
        
    }
    
    public void separarDePadre(){
        
    }
    
    public void addNodo(TNodo n){
        
    }
        
    public TNodo addNodo(){
        return null;
    }

         
    public void setX(double x_){
        
    }

    public void setY(double y_){
        
    }
         
    public void setZ(double z_){
        
    }
         
    public double getX(){
        return 0;
    }
    
    public double getY(){
        return 0;
    }

    public double getZ(){
        return 0;
    }

    public void rotarX(double x_){
        
    }
         
    public void rotarY(double y_){
        
    }

    public void rotarZ(double z_){
        
    }
         
    public void rotar(double x_, double y_, double z_){
        
    }
         
    public double getRotacionX(){
        return 0;
    }
         
    public double getRotacionY(){
        return 0;
    }

    public double getRotacionZ(){
        return 0;
    }
    

    public TMatriz getGlobarVector(double x_, double y_, double z_){
        return null;
    }
    
    public TMatriz getGlobarVector(TMatriz vector){
        return null;
    }
    
    public TMatriz getGlobalCoordinates(){
        return null;
    }
    
    public TMatriz getGlobalCoordinates(double x_, double y_, double z_){
        return null;
    }
    
    public TMatriz getVectorTo(TMatriz vector){
        return null;
    }
    
    public TMatrizTransformacion getMatrizTransf(){
        return null;
    }
    
    public TMatrizTransformacion getMatrizTransfGlobal(){
        return null;
    }
    
    public List<TNodo> getNodos(){
        return null;
    }
    
    public TMatriz getCoordenadas(){
        return null;
    }
    
    
}
