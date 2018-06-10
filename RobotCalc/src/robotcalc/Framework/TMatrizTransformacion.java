/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcalc.Framework;

/**
 *
 * @author Jose Vicente
 */
public class TMatrizTransformacion extends TMatriz{
     
    
        public TMatrizTransformacion(){
            super(4, 4); 
            setIdentidad();
        }
      
        public TMatrizTransformacion(TMatrizTransformacion otra){
            super(4, 4);
            for (int i=0;i<rCount;i++)
                for (int j =0;j<cCount;j++)
                    matriz[i][j] = otra.getValue(i, j);
        }
        
        public TMatrizTransformacion(TMatriz otra){
            super(4, 4); 
            setIdentidad();
            for (int i=0;i<otra.RowCount() && i<rCount;i++)
                for (int j =0;j<otra.ColCount() && j<cCount;j++)
                    matriz[i][j] = otra.getValue(i, j);
        }

        public void trasladar(double x_, double y_, double z_){
            setX(x_);
            setY(y_);
            setZ(z_);
        }
        
        public void setMatriz(TMatrizTransformacion otra){
            for (int i=0;i<otra.RowCount() && i<rCount;i++)
                for (int j =0;j<otra.ColCount() && j<cCount;j++)
                    matriz[i][j] = otra.getValue(i, j);
        }

      public double getX(){
            return matriz[0][3];
      }
      
      public double getY(){
            return matriz[1][3];
      }
      
      public double getZ(){
            return matriz[2][3];
      }
      
      public void setX(double x_){
          matriz[0][3] = x_;
      }
      
      public void setY(double y_){
          matriz[1][3] = y_;
      }
      
      public void setZ(double z_){
          matriz[2][3] = z_;
      }

      public void rotar(double x_, double y_, double z_){
          rotarX(x_);
          rotarY(y_);
          rotarZ(z_);
      }
      
      public void rotarX(double x_){
        TMatrizTransformacion rotMat = new TMatrizTransformacion();
        double angleInRadias = TUtil.degToRad(x_);
        rotMat.setValue(1,1,Math.cos(angleInRadias));
        rotMat.setValue(1,2,Math.sin(angleInRadias)*-1);
        rotMat.setValue(2,1,Math.sin(angleInRadias));
        rotMat.setValue(2,2,Math.cos(angleInRadias));
        setMatriz(rotMat.producto(this));
      }
      
      
      public void rotarY(double y_){
        TMatrizTransformacion rotMat = new TMatrizTransformacion();
        double angleInRadias = TUtil.degToRad(y_);
        rotMat.setValue(0,0, Math.cos(angleInRadias));
        rotMat.setValue(0,2, Math.sin(angleInRadias));
        rotMat.setValue(2,0, Math.sin(angleInRadias)*-1);
        rotMat.setValue(2,2, Math.cos(angleInRadias));
        setMatriz(rotMat.producto(this));
      }

      public void rotarZ(double z_){
        TMatrizTransformacion rotMat = new TMatrizTransformacion();
        double angleInRadias = TUtil.degToRad(z_);
        rotMat.setValue(0,0,Math.cos(angleInRadias));
        rotMat.setValue(0,1,Math.sin(angleInRadias) * -1);
        rotMat.setValue(1,0,Math.sin(angleInRadias));
        rotMat.setValue(1,1,Math.cos(angleInRadias));
        setMatriz(rotMat.producto(this));
      }

      public TMatrizTransformacion copia(){
          return new TMatrizTransformacion(this);
      }
      
      public TMatrizTransformacion producto(TMatrizTransformacion otra){
            return new TMatrizTransformacion(super.producto( (TMatriz)otra ));
      }
      
      public TMatrizTransformacion producto(TMatriz otra){
            return new TMatrizTransformacion(super.producto( otra ));
      }
      
}
