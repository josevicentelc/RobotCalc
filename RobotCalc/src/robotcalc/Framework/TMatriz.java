package robotcalc.Framework;

/**
 * La clase matriz como su propio nombre indica representa a una matriz nxm
 * Dispone de metodos para calculo matricial
 * 
 *  - Producto A*B
 *  - Producto Real*A
 *  - Division A/Real
 *  - Suma A+B
 *  - Resta A-B
 *  - Determinante
 *  - Traspuesta T(A)
 *  - Adjunta
 *  - Inversa
 *  - Escalonada
 *  - Reducir (Solucion de sistema de ecuaciones)
 */


/**
 *
 * @author Jose Vicente Lozano Copa
 */
public class TMatriz {
   
    //Decimals count precision
    public static int precision = 3;
    
    //double array to contain double values
    private double[][] matriz ;
     
    //Count of Rows
    private int rCount = 0;
     
    //Count of Coolumns
    private int cCount = 0;
     
    //function that returns value rounded to nearest integer value
    public static int round(double d){
        int a = (int)d;
        int b = a+1;
        double diftoA = Math.abs(d-a);
        double diftoB = Math.abs(d-b);
        if (diftoA<diftoB) return a;
        return b;
    }
    
    /**
     * public contructor for MxN matriz
     * @param rows Row count
     * @param cools Col count
     */
    public TMatriz(int rows, int cools){
        rCount = rows;
        cCount = cools;
        matriz = new double[rows][];
        for (int i=0;i<rows;i++){
            matriz[i] = new double[cools];
            for (int j=0;j<cools;j++)
                matriz[i][j] = 0;
        }
    }
    
    /**
     * Public constructor that creates a copy of a matrix
     * @param otra Matrix to be copied
     */
    public TMatriz(TMatriz otra){
        rCount = otra.RowCount();
        cCount = otra.ColCount();
        matriz = new double[rCount][];
        for (int i=0;i<rCount;i++){
            matriz[i] = new double[cCount];
            for (int j=0;j<cCount;j++)
                matriz[i][j] = otra.getValue(i, j);
        }
     }
         
    /**
     * Set all the matriz values to 0
     */
     public void setCero(){
         for (int i=0;i<rCount;i++)
             for (int j=0;j<cCount;j++)
                 matriz[i][j] = 0;
     }
        
     /**
      * @return returns Row Count
      */
     public int RowCount(){
         return rCount;
     }
           
     /**
      * 
      * @return returns col count
      */
    public int ColCount(){
        return cCount;
    }
      
     /**
      * @param row num of Row (0 - N-1)
      * @param col num of Col (0 - N-1)
      * @return the value contained in each matrix position
      */
    public double getValue(int row, int col){
        if (row <0 || row >= rCount) return 0;
        if (col <0 || col >= cCount) return 0;
        return matriz[row][col];
    }

    /**
     * Set new value to each matrix position
     * @param row num row (0 - N-1)
     * @param col num col (0 - N-1)
     * @param value Value to be setted
     */
    public void  setValue(int row, int col, double value){
         if (row >=0 && row < rCount)
            if (col >=0 && col < cCount)
                matriz[row][col] = value;
    }

    /**
     * @param otra Matrix to be compared
     * @return return true if the two Matrix has the same size and values
     */
    public boolean equals(TMatriz otra){
        if (rCount != otra.RowCount()) return false;
        if (cCount != otra.ColCount()) return false;
        for (int i = 0;i<RowCount();i++)
            for (int j =0;j<otra.ColCount();j++)
                if ( matriz[i][j] != otra.getValue(i, j) ) return false;
        return true;
    }
           
    /**
     * @param col column to be returned (0 - N-1)
     * @return Gets a column from the matrix and returns it like a new (N,1) matrix 
     */
    public TMatriz getCol(int col){
        TMatriz result = new TMatriz(rCount, 1);
        for (int i= 0;i < rCount;i++)
         result.setValue(i, 0, matriz[i][col]);       
        return result;
    }
           
    /**
     * 
     * @param row row to be returned (0 - N-1)
     * @return Gets a Row from the matrix and returns it like a new (1,N) matrix 
     */
    public TMatriz getRow(int row){
        TMatriz result = new TMatriz(1, cCount);
        for (int I = 0;I< cCount; I++)
            result.setValue(0, I, matriz[row][I]);
        return result;
    }
           
    /**
     * @return returns the Transpose Matrix to this Matrix
     * https://en.wikipedia.org/wiki/Transpose
     */
    public TMatriz traspuesta(){
        TMatriz result = new TMatriz(cCount, rCount);
        for (int i =0;i< rCount;i++)
            for (int j =0;j< cCount;j++)
                result.setValue(j, i, matriz[i][j]);
        return result;
    }
           
    /**
     * @param otra Other matrix
     * @return returns the matrix multiplication for This matrix * other Matrix
     */
    public TMatriz producto(TMatriz otra){
        int m,n,p;
        double t;
        TMatriz result;
        if (cCount == otra.RowCount()){
            n = cCount;
            m = RowCount();
            p = otra.ColCount();
            result = new TMatriz(m, p);
            for (int I = 0;I< m;I++)
                for (int J = 0;J< p;J++) 
                {
                    t = 0;
                    for (int R = 0; R< n;R++) 
                    {
                        t = t +  getValue(I, R)  * otra.getValue(R, J);
                    }
                    result.setValue(I,J,t);
                }
        }
        else{
            result = new TMatriz(0, 0);
        }
        return result;
    }
           
    /**
     * @param otra Matrix to be summed
     * @return returns the sum of the twho Matrixs
     */
    public TMatriz suma(TMatriz otra){
        TMatriz result;
        if (rCount == otra.RowCount() && cCount == otra.ColCount()) 
        {
            result = new TMatriz(rCount, cCount);
            for (int i =0;i< rCount;i++)
                for (int j =0;j< cCount;j++)
                      result.setValue(i, j, matriz[i][j] + otra.getValue(i, j));
        }
        else
        {
            result = new TMatriz(0, 0);
        }
        return result;
    }
           
    /**
     * 
     * @param otra Matrix to be reseted
     * @return Returns the rest of the two matrixs
     */
    public TMatriz resta(TMatriz otra){
        TMatriz result;
        if (rCount == otra.RowCount() && cCount == otra.ColCount()) 
        {
            result = new TMatriz(rCount, cCount);
            for (int i =0;i< rCount;i++)
                for (int j =0;j< cCount;j++)
                      result.setValue(i, j, matriz[i][j] - otra.getValue(i, j));
        }
        else
        {
            result = new TMatriz(0, 0);
        }
        return result;
    }
           
    /**
     * Set matrix values to an Indentity Matrix where the main diagonal has 1 and the rest is set to 0
     */
    public void setIdentidad(){
        int min;
        if (rCount < cCount)  min = rCount;
        else                  min = cCount;
        for (int I = 0; I< min;I++)
            matriz[I][I] = 1;        
    }
     
    /**
     * 
     * @return Returns the same matrix whith positive values
     */
    public TMatriz absoluteMatrix(){
        TMatriz result = new TMatriz(rCount, cCount);
        for (int i =0;i< rCount;i++)
            for (int j =0;j< cCount;j++)
                result.setValue(i, j, Math.abs(matriz[i][j]));
        return result;
    }
     
    /**
     * Randomize all the matrix values
     * @param min min random value
     * @param max Max random value
     */
    public void randomMatrix(double min, double max){
        System.err.println("Random Matrix not implemented");
    }
     
    /**
     * Chage the position between two rows
     * @param fila1 row 1
     * @param fila2 row 2
     */
    public void intercambioFilas(int fila1, int fila2){
        TMatriz m1, m2;
        if (fila1 < rCount && fila2 < rCount && fila1 >=0 && fila2 >=0){
            m1 = getRow(fila1);
            m2 = getRow(fila2);
            setRow(fila1, m2);
            setRow(fila2, m1);
        }
    }
     
    /**
     * 
     * @param fila
     * @param m 
     */
    public void setRow(int fila, double[] m ){
        if (fila >= 0 && fila < rCount)      //La fila elegida debe estar dentro de la matriz
            for (int j =0;j< cCount;j++ )
                if (j<m.length)      matriz[fila][j] = m[j];
                else                 matriz[fila][j] = 0;
    }
       
    /**
     * 
     * @param fila
     * @param m 
     */    
    public void setRow(int fila, TMatriz m){
        if (fila >= 0 && fila < rCount)          //La fila elegida debe estar dentro de la matriz
            if (m.ColCount() >= cCount)           //La Matriz de valores debe contener suficientes valores
                if (m.RowCount() > 0)
                    for (int j =0;j<cCount;j++)
                        matriz[fila][j] = m.getValue(0, j);
    }

    /**
     * 
     * @return 
     */
    public TMatriz reordenarParaReducir(){
        TMatriz result = new TMatriz(this);
        for (int i = 0;i< rCount;i++)
            if (result.getValue(i, i) == 0 &&  i< rCount-1) 
                result.intercambioFilas(i, i+1);
        return result;
    }
       
    /**
     * 
     */
    public void eliminarImprecision(){
        for (int i=0;i<rCount;i++)
           for (int j = 0;j<cCount;j++){
               if (Math.abs(round(matriz[i][j]) - matriz[i][j]) < 1/Math.pow(10, precision))
                   matriz[i][j] = round(matriz[i][j]);
            }
    }
       
    /**
     * 
     * @return 
     */
    public TMatriz reducir(){
        TMatriz t, linea;
        TMatriz result = escalonada();
        // Teniendo una matriz escalonada, voy de abajo a arriba eliminando terminos
        for (int i = result.RowCount()-1;i>=0;i--)
            //usando el 1 del eelemento i,i, elimino los terminos de las casillas i-1,
            for (int i2 = i-1;i2>= 0;i2--)
            {
                //Obtengo la linea con el termino en 1
                //y la multiplico por el valor que deseo eliminar
                t = result.getRow(i);
                linea  = t.producto(result.getValue(i2, i));

                //si el signo es igual, las resto
                if ((linea.getValue(0, i) >0 && result.getValue(i2, i) > 0)
                   ||
                (linea.getValue(0, i) <0 && result.getValue(i2, i) < 0))
                {
                   t = result.getRow(i2);
                   result.setRow(i2, t.resta(linea));
                }
                else
                {
                   t = result.getRow(i2);
                   result.setRow(i2, t.suma(linea));
                   //si es distinto, las sumo
                }
            }
        return result;
    }
       
    /**
     * 
     * @return 
     */
    public TMatriz escalonada(){
        int j, j2;
        TMatriz N,N1;
        TMatriz result;
        result = reordenarParaReducir();
        for (j = 0; j< result.RowCount();j++)
        {
           //Primero hago 1 en la diagonal
           if (result.getValue(j, j) != 0) result.setRow(j, result.getRow(j).division(result.getValue(j, j)));
            for (j2 = j+1;j2 < result.RowCount();j2++)
            {
                //Ahora recorro en J2 todas las filas inferiores a J y les resto Nveces la fila actual
                //donde N es el valor de la columna J para la fila J2
                N = result.getRow(j);
                N1 = N.producto(result.getValue(j2, j));
                //Si tienen el mismo signo las resto, si son diferentes las sumo
                N = result.getRow(j2);
                if ((N1.getValue(0, j) > 0 && result.getValue(j2, j) > 0)
                   ||
                   (N1.getValue(0, j) < 0) && (result.getValue(j2, j) < 0 )) 
                {
                   result.setRow(j2,  N.resta(N1) );
                }
                else
                {
                  result.setRow(j2,  N.suma(N1));
                }
            }
        }
        result.eliminarImprecision();
        return result;
    }
       
    /**
     * 
     * @param fOrigen
     * @param cOrigen
     * @param fDestino
     * @param cDestino
     * @return 
     */
    public TMatriz subMatriz(int fOrigen,int  cOrigen, int fDestino, int cDestino){
     if (fOrigen >= fDestino || cOrigen >= cDestino) 
        return new TMatriz(0,0);
     else
        {
            TMatriz result;
            result = new TMatriz(fDestino-fOrigen+1, cDestino-cOrigen+1);
             for (int i = fOrigen;i<= fDestino;i++)
                for (int j = cOrigen; j<= cDestino;j++)
                    result.setValue(i-fOrigen, j-cOrigen, matriz[i][j]);
            return result;
        }
    }
       
    /**
     * 
     * @return 
     */
    public TMatriz copia(){
        TMatriz result = new TMatriz(rCount, cCount);
        for (int i=0;i<rCount;i++)
            for (int j = 0;j<cCount;j++)
                result.setValue(i, j, matriz[i][j]);
        return result;
    }
       
    /**
     * 
     * @return 
     */
    public String ToString(){
        return ToString(2);
    }
           
    public String ToString(int pos){
        String salida = "";
        if (rCount==0 || cCount == 0) return "Matriz vacia";
        
        for (int i=0;i<rCount;i++){
            for (int j=0;j<cCount;j++){
                salida += "| "+ String.format("%." + pos+"f", matriz[i][j]);
            }
            salida+=" |\n";
        }
        return salida;    
    }
       
    /**
     * 
     * @return 
     */
    public boolean esInvertible(){
        if (rCount != cCount || rCount == 0) 
           return false;
        else
           if (determinante()==0)
              return false;
        return true;
    }
       
    /**
     * 
     * @return 
     */
    public TMatriz adjunta(){
        TMatriz adj;
        if (rCount != cCount)  return new TMatriz(0,0);
        else
        {
            TMatriz result = new TMatriz(rCount, cCount);
            for (int i = 0;i< rCount; i++)
                for (int j = 0;j< cCount;j++)
                {
                    adj = adjunta(i, j);
                    result.setValue(i, j, adj.determinante()  );
                    // si el elemento i,j tiene un i+j impar, se invierte su signo
                    if (  (i+j) % 2 != 0  ) result.setValue(i, j, result.getValue(i, j) * -1);
                }
            return result;
        }
    }
       
    /**
     * 
     * @param fila
     * @param columna
     * @return 
     */
    public TMatriz adjunta(int fila, int columna){
        int filaDestino, colDestino;
        TMatriz result;
        //Si alguno de los valores esta fuera de la matriz, retorno una matriz que es copia de esta
        if (fila >= rCount || columna >= cCount || fila < 0 || columna < 0) 
           return copia();
        else
        {
            //Si ambos estan dentro, obtengo la matriz resultante de eliminar dicha fila y columna
            result = new TMatriz(rCount-1, cCount-1);
            for (int i = 0;i< rCount;i++)
                if (i != fila) 
                {
                    for (int j = 0;j< cCount;j++)
                    {
                        if (j != columna)
                        {
                             if (i<fila) filaDestino = i;
                             else        filaDestino = i-1;
                             if (j<columna) colDestino = j;
                             else           colDestino = j-1;
                             result.setValue(filaDestino, colDestino, matriz[i][j]);
                        }
                    }
                }
        }
        return result;
    }
       
    /**
     * 
     * @return 
     */
    public TMatriz inversa(){
        double deter;
        deter = determinante();
        if (deter == 0) 
            return new TMatriz(0, 0);
        else
        {
            return traspuesta().adjunta().producto(1/deter);
        }
    }
       
    /**
     * 
     * @return 
     */
    public double determinante(){
        double det1, det2, result;
        boolean sum;
        TMatriz adj;

        if (rCount != cCount) return 0;
        else
        if (rCount == 0) return 0;
        else
        if (rCount == 1) return matriz[0][0];
        else
        if (rCount == 2) return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        else
        if (rCount == 3)
        {
                det1 = 0;
                det2 = 0;
                det1 = det1 + matriz[0][0] * matriz[1][1] * matriz[2][2];
                det1 = det1 + matriz[1][0] * matriz[2][1] * matriz[0][2];
                det1 = det1 + matriz[2][0] * matriz[0][1] * matriz[1][2];
                det2 = det2 + matriz[0][2] * matriz[1][1] * matriz[2][0];
                det2 = det2 + matriz[1][2] * matriz[2][1] * matriz[0][0];
                det2 = det2 + matriz[2][2] * matriz[0][1] * matriz[1][0];
                return det1 - det2;
        }
        else
        {
            result =0;
            sum = true;
            for (int i = 0;i< rCount;i++)
            {
                adj = adjunta(i,0);
                if (sum) 
                {
                    result = result + (adj.determinante() * matriz[i][0]);
                    sum = false;
                }
                else
                {
                    result =  result - (adj.determinante() * matriz[i][0]);
                    sum = true;
                }
            }
            return result;
        }
    }
       
    /**
     * 
     * @param value
     * @return 
     */
    public TMatriz producto(double value){
        TMatriz result = new TMatriz(rCount, cCount);
        for (int i = 0;i< rCount;i++)
            for (int j = 0;j< cCount;j++)
                result.setValue(i, j, matriz[i][j] * value);
        return result;
    }
       
    /**
     * 
    */
    public TMatriz division(double value){
        TMatriz result = new TMatriz(rCount, cCount);
        if (value == 0) return result;
        for (int i = 0;i< rCount;i++)
            for (int j = 0;j< cCount;j++)
                result.setValue(i, j, matriz[i][j] / value);
        return result;
    }

    /**
     * 
     * @return 
     */
    public double getX(){
        return matriz[0][3];
    }
       
    /**
     * 
     * @return 
     */
    public double getY(){
        return matriz[1][3];
    }
       
    /**
     * 
     * @return 
     */
    public double getZ(){
        return matriz[2][3];
    }
           
           
}
