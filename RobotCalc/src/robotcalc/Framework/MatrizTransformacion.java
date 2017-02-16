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
    
    public void rotar(double a, double b, double y){
        rotarX(a);
        rotarY(b);
        rotarZ(y);
    }
    
    
    public void rotarX(double A){
        A = Math.toRadians(A);
        matriz[1][1] = Math.cos(A);
        matriz[1][2] = Math.sin(A) * -1;
        matriz[2][1] = Math.sin(A);
        matriz[2][2] = Math.cos(A);
        eliminarImprecision();
    }
    public void rotarY(double A){
        A = Math.toRadians(A);
        matriz[0][0] = Math.cos(A);
        matriz[0][2] = Math.sin(A);
        matriz[2][0] = Math.sin(A)*-1;
        matriz[2][2] = Math.cos(A);
        eliminarImprecision();
    }
    public void rotarZ(double A){
        A = Math.toRadians(A);
        matriz[0][0] = Math.cos(A);
        matriz[0][1] = Math.sin(A) * -1;
        matriz[1][0] = Math.sin(A);
        matriz[1][1] = Math.cos(A);
        eliminarImprecision();
    }
    
    private void eliminarImprecision(){
        for (int i=0;i<rCount;i++)
           for (int j = 0;j<cCount;j++){
               if (Math.abs(round(matriz[i][j]) - matriz[i][j]) < 0.0000001)
                   matriz[i][j] = round(matriz[i][j]);
           }
    }
    
    public static int round(double d){
        int a = (int)d;
        int b = a+1;
        double diftoA = Math.abs(d-a);
        double diftoB = Math.abs(d-b);
        if (diftoA<diftoB) return a;
        return b;
    }
    
}
