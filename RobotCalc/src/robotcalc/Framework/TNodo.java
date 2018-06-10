
package robotcalc.Framework;

import java.awt.Graphics;
import java.util.ArrayList;
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
    
    private boolean modified = true;
    
    TMatrizTransformacion matrizHomogenea = null;
    

    public TNodo(){
     rotx=0; 
     roty=0; 
     rotz=0;
     trax=0;  
     tray=0; 
     traz=0;
     nodos = new ArrayList<TNodo>();
    }
    
    public TNodo(TNodo otro){
        rotx=0; 
        roty=0; 
        rotz=0;
        trax=0;  
        tray=0; 
        traz=0;
        nodos = new ArrayList<TNodo>();
        for (int i = 0; i< otro.getNodos().size();i++)
        {
            nodos.add( new TNodo(otro.getNodos().get(i)));
        }
    }
    
    public TNodo(double x_, double y_, double z_){
        rotx=0; 
        roty=0; 
        rotz=0;
        trax=0;  
        tray=0; 
        traz=0;
        nodos = new ArrayList<TNodo>();
        setTCP(x_, y_, z_);
    }

    public void setParent(TNodo n){
       parent = n; 
    }
    
    public void setTCP(double x_, double y_, double z_){
        trax=x_;
        tray=y_;
        traz=z_;
    }
        
    public void quitarHijo(TNodo n){
        for (int i = nodos.size()-1; i>=0;i--)
        {
            if (nodos.get(i) == n) {
                nodos.remove(i);
            }
        }
    }
    
    public void separarDePadre(){
        if (parent != null)
        {
             TMatriz nuevoOrigen = getGlobarVector(parent.getCoordenadas());
             parent.quitarHijo(this);
             parent = null;
             setTCP(nuevoOrigen.getValue(0, 0), nuevoOrigen.getValue(1, 0), nuevoOrigen.getValue(2, 0));
        }
    }
    
    public void addNodo(TNodo n){
        n.setParent(this);
        nodos.add(n);
    }
        
    public TNodo addNodo(){
        TNodo n = new TNodo();
        n.setParent(this);
        nodos.add(n);
        return n;
    }

         
    public void setX(double x_){
        modified = true;
        trax = x_;
    }

    public void setY(double y_){
        modified = true;
        tray = y_;
    }
         
    public void setZ(double z_){
        modified = true;
        traz = z_;
    }
         
    public double getX(){
        return trax;
    }
    
    public double getY(){
        return tray;
    }

    public double getZ(){
        return traz;
    }

    public void rotarX(double x_){
        modified = true;
        rotx = x_;
    }
         
    public void rotarY(double y_){
        modified = true;
        roty = y_;
    }

    public void rotarZ(double z_){
        modified = true;
        rotz = z_;
    }
         
    public void rotar(double x_, double y_, double z_){
        rotarX(x_);
        rotarY(y_);
        modified = true;
        rotarZ(z_);
    }
         
    public double getRotacionX(){
        return rotx;
    }
         
    public double getRotacionY(){
        return roty;
    }

    public double getRotacionZ(){
        return rotz;
    }
    

    public TMatriz getGlobarVector(double x_, double y_, double z_){
        TMatriz m = new TMatriz(4, 1);
        m.setValue(0, 0, x_);
        m.setValue(1, 0, y_);
        m.setValue(2, 0, z_);
        m.setValue(3, 0, 1);
        return getGlobarVector(m);
    }
    
    public TMatriz getGlobarVector(TMatriz vector){
        if (parent != null)
        {
             return parent.getGlobarVector(getVectorTo(vector));
        }
        else
        {
             return vector;
        }
    }
    
    public TMatriz getGlobalCoordinates(){
        TMatriz pos = getCoordenadas();
        TMatrizTransformacion transf = getMatrizTransfGlobal();
        return transf.producto(pos);
    }
    
    public TMatriz getGlobalCoordinates(double x_, double y_, double z_){
     TMatriz pos = new TMatriz(4,1);
     pos.setValue(0,0,x_);
     pos.setValue(1,0,y_);
     pos.setValue(2,0,z_);
     pos.setValue(3,0,1);
     TMatrizTransformacion transf = getMatrizTransfGlobal();
     return transf.producto(pos);
    }
    
    public TMatriz getVectorTo(TMatriz vector){
        //TODO
        return null;
    }
    
    public TMatrizTransformacion getMatrizTransf(){
        if (!modified) return matrizHomogenea;
        TMatrizTransformacion result = new TMatrizTransformacion();
        result.setX(trax);
        result.setY(tray);
        result.setZ(traz);
        result.rotar(rotx, roty, rotz);
        matrizHomogenea = result;
        modified = false;
        return result;
    }
    
    public TMatrizTransformacion getMatrizTransfGlobal(){
        if (parent == null)
        {
             return getMatrizTransf();
        }
        else
        {
             TMatrizTransformacion C = getMatrizTransf();
             return parent.getMatrizTransfGlobal().producto(C);
        }
    }
    
    public List<TNodo> getNodos(){
        return nodos;
    }
    
    public TMatriz getCoordenadas(){
        TMatriz result = new TMatriz(4,1);
        result.setValue(3,0,1);
        return result;
    }
    
    public void draw(Graphics g){
        
    }
    
}
