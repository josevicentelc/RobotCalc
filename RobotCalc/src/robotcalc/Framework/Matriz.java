/**
 * La clase matriz como su propio nombre indica representa a una matriz nxm
 * Dispone de metodos para calculo matricial
 * 
 *  - Producto A*B
 *  - Suma A+B
 *  - Resta A-B
 *  - Traspuesta T(A)
 */
package robotcalc.Framework;

/**
 *
 * @author Jose Vicente Lozano Copa
 */
public class Matriz {
    
    /**
     * Numero de filas
     */
    protected int rCount = 0;
    
    /**
    * Numero de columnas
    */
    protected int cCount = 0;
    
    /**
     * Matriz
     */
    protected double[][] matriz;
    
    /**
     * Constructor
     * @param filas Numero de filas
     * @param columnas Numero de columnas
     */
    public Matriz(int filas, int columnas){
        rCount = filas;
        cCount = columnas;
        matriz = new double[filas][];
        for (int i=0;i<filas;i++){
            matriz[i] = new double[columnas];
            for (int j=0;j<columnas;j++)
                matriz[i][j] = 0;
        }
    }
    
    /**
     * Constructor de copia
     * @param B Matriz a duplicar
     */
    public Matriz(Matriz B){
        rCount = B.rowCount();
        cCount = B.colCount();
        matriz = new double[rCount][];
        for (int i=0;i<rCount;i++){
            matriz[i] = new double[cCount];
            for (int j=0;j<cCount;j++)
                matriz[i][j] = B.getValue(i, j);
        }
    }
    
    /**
     * Verifica que dos matrices tienen la misma dimension y valores
     * @param B Matriz con la que comparar
     * @return True si las dos matrices coinciden en orden y valores
     */
    public boolean equals(Matriz B){
        if (rCount != B.rowCount() || cCount != B.colCount()) return false;
        for (int i=0;i<rCount;i++)
            for (int j=0;j<cCount;j++)
                if (matriz[i][j] != B.getValue(i, j)) return false;
        return true;
    }
    
    /**
     * Obtiene todos los elementos de la columna indicada
     * @param col Columna
     * @return Lista de valores de la columna, si no existe se retorna una lista vacia
     */
    public Matriz getCol(int col){
        if (col < 0 || col >= cCount)
            return new Matriz(0, 0);
        else
        {
            Matriz salida = new Matriz(rCount, 1);
            for (int i=0;i<rCount;i++)
                salida.setValue(col, 0, matriz[i][col]); 
            return salida;
        }
    }

    /**
     * Obtiene todos los elementos de la fila indicada
     * @param row Fila
     * @return Lista de todos los elementos de la fila, si no existe retorna una lista vacia
     */
    public Matriz getRow(int row){
        if (row < 0 || row >= rCount)
            return new Matriz(0, 0);
        else
        {
            Matriz salida = new Matriz(1, cCount);
            for (int i=0;i<cCount;i++)
                salida.setValue(1, i, matriz[row][i]); 
            return salida;
        }
    }

    /**
     * @return  Numero de columnas de la matriz
     */
    public int colCount(){return cCount;}
    
    /**
     * @return Numero de filas de la Matriz
     */
    public int rowCount(){return rCount;}
    
    /**
     * Establece el valor de la posicion ( fila, columna)
     * Si la posicion no existe, no hace nada
     * @param row Fila
     * @param col Columna
     * @param d Valor
     */
    public void setValue(int row, int col, double d){
        if (row >= 0 && row < rCount && col >= 0 && col < cCount)
            matriz[row][col] = d;
    }
    
    /**
     * Obtiene el valor de la posicion (fila,columna)
     * @param row Fila
     * @param col Columna
     * @return Valor en la posicion, si no existe retorna 0
     */
    public double getValue(int row, int col){
        if (row >= 0 && row < rCount && col >= 0 && col < cCount)
            return matriz[row][col];
        else
            return 0;
    }
    
    /**
     * Otiene la matriz traspuesta a la actual
     * @return Traspuesta
     */
    public Matriz traspuesta(){
        Matriz C = new Matriz(cCount, rCount);
        for (int i=0;i<rCount;i++)
            for (int j=0;j<cCount;j++)
                C.setValue(j, i, matriz[i][j]);
        return C;
    }
    
    /**
     * Obtiene una matriz que es el producto de la matriz actual por la pasada
     * @param B
     * @return this*B
     */
    public Matriz producto(Matriz B){
      if (cCount == B.rowCount()){
          Matriz C = new Matriz(rCount, B.colCount());
          for (int i=0;i<B.rowCount();i++)
              for (int j=0;j<B.colCount();j++){
                  
                  double t = 0;
                  for (int r=0;r<cCount;r++){
                      t+= matriz[i][r] * B.getValue(r, j);
                  }
                  C.setValue(i, j, t);
              }
          
          return C;
      }  
      else
          return new Matriz(0, 0);
    }
    
    /**
     * Obtiene una matriz qu es la suma de la matriz actual por la matriz B
     * @param B Matriz B
     * @return Suma de this+B
     */
    public Matriz suma(Matriz B){
        if (rCount == B.rowCount() && cCount == B.colCount()){
            Matriz C = new Matriz(rCount, cCount);
            for (int i=0;i<rCount;i++)
                for (int j=0;j<cCount;j++)
                    C.setValue(i, j, matriz[i][j] + B.getValue(i, j));
            return C;
        }
        else
            return new Matriz(0, 0);
    }

    /**
     * Obtiene una matriz qu es la resta de la matriz actual menos la matriz B
     * @param B Matriz B
     * @return Suma de this-B
     */
    public Matriz resta(Matriz B){
        if (rCount == B.rowCount() && cCount == B.colCount()){
            Matriz C = new Matriz(rCount, cCount);
            for (int i=0;i<rCount;i++)
                for (int j=0;j<cCount;j++)
                    C.setValue(i, j, matriz[i][j] - B.getValue(i, j));
            return C;
        }
        else
            return new Matriz(0, 0);
    }
    
    
    /**
     * Pone todos los elementos de la matriz a 0
     */
    public void setCero(){
        for (int i=0;i<rCount;i++)
            for (int j=0;j<cCount;j++)
                matriz[i][j] = 0;
    }
    
    /**
     * Cambia los valores de la matriz para convertirla en la identidad
     */
    public void setIdentidad(){
        setCero();
        for (int p=0; p<rCount && p<cCount; p++)
            matriz[p][p] = 1;
    }
    
    /**
     * Obtiene la matriz inversa a la actual tal que (actual*inversa = Identidad)
     * @return Matriz inversa a this
     */
    public Matriz inversa(){
        System.err.println("La funcion Matriz inversa no esta implementada");
        return new Matriz(this);
    }
    
    /**
     * Retorna la matriz en forma de String
     * @return Matriz en forma de String
     */
    public String toString(){
        String salida = "";
        for (int i=0;i<rCount;i++){
            for (int j=0;j<cCount;j++){
                salida += "| "+matriz[i][j];
            }
            salida+=" |\n";
        }
        return salida;
    }
    
}
