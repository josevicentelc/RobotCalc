/*
 * Matriz de transformacion
 * Es una matriz cuadrada de dimension 4x4
 * Utilizada en robotica para representar un origen de coordenadas asi como su rotacion y traslacion
 */
package robotcalc.Framework;

/**
 *
 * @author Jose Vicente Lozano Copa
 */
public class MatrizTransformacion extends Matriz{
    
    /**
     * Constructor por defecto
     */
    public MatrizTransformacion(){
        super(4, 4);
        setIdentidad();
    }
    
    /**
     * Constructor de copia
     * @param B Matriz de transformacion a duplicar
     */
    public MatrizTransformacion(MatrizTransformacion B){
        super(B);
    }
    
    /**
     * Constructor de copia
     * @param B Matriz de transformacion a duplicar
     */
    public MatrizTransformacion(Matriz B){
        super(B);
    }
    
    
    
    /**
     * Traslada el origen de coordenadas en el eje X en un incremento indicado por el parametro
     * @param x Cantidad a incrementar
     */
    public void trasladarX(double x){
        matriz[0][3] += x;
    }

    /**
     * Retorna la matriz de transformacion resultante de hacer el producto de esta matriz por la pasada por parametros A*B
     * @param otra Matriz B
     * @return Producto de las dos matrices
     */
    public MatrizTransformacion producto(MatrizTransformacion otra){
       // System.out.println("Producto de la matriz");
        //System.out.println(toString());
        //System.out.println(" X ");
        //System.out.println(otra.toString());

        Matriz m = super.producto((Matriz)otra);
        //System.out.println(" = ");
        //System.out.println(m.toString());
        
        return new MatrizTransformacion(m);
    }
    
    /**
     * Traslada el origen de coordenadas en el eje Y en un incremento indicado por el parametro
     * @param y Cantidad a incrementar
     */
    public void trasladarY(double y){
        matriz[1][3] += y;
    }
    
    /**
     * Traslada el origen de coordenadas en el eje Z en un incremento indicado por el parametro
     * @param z Cantidad a incrementar
     */
    public void trasladarZ(double z){
        matriz[2][3] += z;
    }
    
    /**
     * Establece de manera absoluta la traslacion del origen de coordenadas
     * @param x Traslacion en X
     * @param y Traslacion en Y
     * @param z Traslacion en Z
     */
    public void setPosicion(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
    }
    
    /**
     * Retorna array que contiene las coordenadas x,y,z de la traslacion 
     * @return coordenadas x,y,z
     */
    public double[] getPosicion(){
        double[] salida = new double[3];
        salida[0] = getX();
        salida[1] = getY();
        salida[2] = getZ();
        return salida;
    }
    
    /**
     * @return Retorna el valor de traslacion en el eje X
     */
    public double getX(){        return matriz[0][3];    }

    /**
     * @return Retorna el valor de traslacion en el eje Y
     */
    public double getY(){        return matriz[1][3];    }

    /**
     * @return Retorna el valor de traslacion en el eje Z
     */
    public double getZ(){        return matriz[2][3];    }
    
    /**
     * Establece de forma absoluta la traslacion en el eje X
     * @param x Traslacion en X
     */
    public void setX(double x){matriz[0][3] = x;}

    /**
     * Establece de forma absoluta la traslacion en el eje Y
     * @param y Traslacion en Y
     */
    public void setY(double y){matriz[1][3] = y;}

    /**
     * Establece de forma absoluta la traslacion en el eje Z
     * @param z Traslacion en Z
     */
    public void setZ(double z){matriz[2][3] = z;}
    
    /**
     * Traslada el origen de coordenadas en un incremento de los tres ejes
     * @param x Incremento de X
     * @param y Incremento de Y
     * @param z Incremento de Z
     */
    public void trasladar(double x, double y, double z){
        trasladarX(x);
        trasladarY(y);
        trasladarZ(z);
    }
    
    /**
     * Retorna una matriz de transformacion que es inversa a la actual si es que es invertible
     * En caso de no serlo retorna una matriz vacia
     * @return Matriz inversa
     */
    @Override
    public MatrizTransformacion inversa(){
        if (!esInvertible())
            return new MatrizTransformacion();
        else
            return new MatrizTransformacion(super.inversa());
    }

    
    /**
     * Rota el origen de coordenadas sobre los tres ejes los grados indicados
     * @param a rotacion en X
     * @param b rotacion en Y
     * @param y rotacion en Z
     */
    public void rotar(double a, double b, double y){
        rotarX(a);
        rotarY(b);
        rotarZ(y);
    }
    
    /**
     * Rota el origen de coordenadas sobre el eje X
     * @param A Rotacion en grados 
     */
    public void rotarX(double A){
        A = Math.toRadians(A);
        matriz[1][1] = Math.cos(A);
        matriz[1][2] = Math.sin(A) * -1;
        matriz[2][1] = Math.sin(A);
        matriz[2][2] = Math.cos(A);
        eliminarImprecision();
    }

    /**
     * Rota el origen de coordenadas sobre el eje Y
     * @param A Rotacion en grados 
     */
    public void rotarY(double A){
        A = Math.toRadians(A);
        matriz[0][0] = Math.cos(A);
        matriz[0][2] = Math.sin(A);
        matriz[2][0] = Math.sin(A)*-1;
        matriz[2][2] = Math.cos(A);
        eliminarImprecision();
    }

    /**
     * Rota el origen de coordenadas sobre el eje Z
     * @param A Rotacion en grados 
     */
    public void rotarZ(double A){
        A = Math.toRadians(A);
        matriz[0][0] = Math.cos(A);
        matriz[0][1] = Math.sin(A) * -1;
        matriz[1][0] = Math.sin(A);
        matriz[1][1] = Math.cos(A);
        eliminarImprecision();
    }
    
    /**
     * Si a la hora de hacer las operaciones se produce un error de precision
     * este metodo lo redondea siempre que el error sea inferior a un margen 0.0000001
     */
    private void eliminarImprecision(){
        for (int i=0;i<rCount;i++)
           for (int j = 0;j<cCount;j++){
               if (Math.abs(round(matriz[i][j]) - matriz[i][j]) < 0.0000001)
                   matriz[i][j] = round(matriz[i][j]);
           }
    }
    
    /**
     * Metodo utilitario que redondea un flotante al entero mas proximo
     * @param d Flotante a redondear
     * @return Entero mas proximo
     */
    public static int round(double d){
        int a = (int)d;
        int b = a+1;
        double diftoA = Math.abs(d-a);
        double diftoB = Math.abs(d-b);
        if (diftoA<diftoB) return a;
        return b;
    }
    
    /**
     * Crea una instancia de una nueva matriz que es copia de la actual
     * @return 
     */
    @Override
    public MatrizTransformacion copia() {
        MatrizTransformacion salida = new MatrizTransformacion();
        for (int i=0;i<rCount;i++)
            for (int j=0;j<cCount;j++)
                salida.matriz[i][j] = matriz[i][j];
        return salida;    
    }

}
