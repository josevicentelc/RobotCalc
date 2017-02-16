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
public class MatrizTransformacion extends Matriz{
    
    public MatrizTransformacion(){
        super(4, 4);
        setIdentidad();
    }
    
    public MatrizTransformacion(MatrizTransformacion B){
        super(B);
    }
    
    public void trasladarX(double x){
        matriz[0][3] += x;
    }
    public void trasladarY(double y){
        matriz[1][3] += y;
    }
    public void trasladarZ(double z){
        matriz[2][3] += z;
    }
    
    public void setPosicion(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
    }
    
    public double[] getPosicion(){
        double[] salida = new double[3];
        salida[0] = getX();
        salida[1] = getY();
        salida[2] = getZ();
        return salida;
    }
    
    public double getX(){        return matriz[0][3];    }
    public double getY(){        return matriz[1][3];    }
    public double getZ(){        return matriz[2][3];    }
    
    public void setX(double x){matriz[0][3] = x;}
    public void setY(double y){matriz[1][3] = y;}
    public void setZ(double z){matriz[2][3] = z;}
    
    public void trasladar(double x, double y, double z){
        trasladarX(x);
        trasladarY(y);
        trasladarZ(z);
    }
    
    
}
