
package robotcalc.Framework;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Vicente
 */
public class TRobot {

    private int gradosLibertad = 0;
    
    private List<TNodo> nodos = null;
    
    private TNodo sistemaCoordenadas = null;
    
 
    public TRobot(int grados){
          nodos = new ArrayList<TNodo>();
          sistemaCoordenadas = new TNodo();
          TNodo ultimoNodo = sistemaCoordenadas;

          nodos.add(ultimoNodo);

          if (grados > 0) 
          {
                gradosLibertad = grados;
                for (int i = 1 ;i<grados;i++)
                {
                    ultimoNodo = ultimoNodo.addNodo();
                    nodos.add(ultimoNodo);
                }
          }else{
              gradosLibertad = 1;
          }
    }
    
    public void draw(Graphics g){
        if (g == null) System.out.println("Try to draw in a Null Canvas");
        else
        {
        int marginLeft =100;
        int lastx = 0;
        int lasty = 0;
        for (int i=0;i<nodos.size();i++){
              //canvas.Pen.Width:=3;

              TMatriz position = nodos.get(i).getGlobalCoordinates();
              
              int x = Math.round((float)position.getValue(0,0));
              int y = Math.round((float)position.getValue(1,0));
              g.setColor(Color.GRAY);

              g.drawLine(marginLeft+lastx, lasty, marginLeft+x, y);
              
              //canvas.Pen.Width:=4;


              g.setColor(Color.red);
              g.drawRect(marginLeft+x -5, y-5, marginLeft+x+5, y+5);
              lastx = x;
              lasty = y;
              //canvas.Pen.Width:=2;

              //Eje X
              //position := nodos[I].getGlobalCoordinates(30, 0, 0);
              //canvas.Pen.color := clred;
              //Canvas.Line(marginLeft+lastx, canvas.Height - lasty, marginLeft+Round(position.getValue(0,0)), canvas.Height - Round(position.getValue(1,0)));
              //canvas.TextOut(marginLeft+Round(position.getValue(0,0)), canvas.Height -Round(position.getValue(1,0)), 'X');
              //position.free;

              //Eje Y
              //position := nodos[I].getGlobalCoordinates(0, 30, 0);
              //canvas.Pen.color := clGreen;
              //Canvas.Line(marginLeft+lastx, canvas.Height - lasty, marginLeft+Round(position.getValue(0,0)), canvas.Height - Round(position.getValue(1,0)));
              //canvas.TextOut(marginLeft+Round(position.getValue(0,0)), canvas.Height -Round(position.getValue(1,0)), 'Y');
              //position.free;

              //Eje Z
              //position := nodos[I].getGlobalCoordinates(0, 0, 30);
              //canvas.Pen.color := clBlue;
              //Canvas.Line(marginLeft+lastx, canvas.Height - lasty, marginLeft+Round(position.getValue(0,0)), canvas.Height - Round(position.getValue(1,0)));
              //canvas.TextOut(marginLeft+Round(position.getValue(0,0)), canvas.Height -Round(position.getValue(1,0)), 'Z');
              //position.free;
            
              g.setColor(Color.GREEN);
              g.drawString("RoboCalc.", 5, 15);
        }

        }
    }
    
    public void trasladarX(int nodo_, double x_){
        if (nodo_ >=0 && nodo_<gradosLibertad)
            nodos.get(nodo_).setX(x_);
    }
    
    public void trasladarY(int nodo_, double y_){
        if (nodo_ >=0 && nodo_<gradosLibertad)
            nodos.get(nodo_).setY(y_);
    }

    public void trasladarZ(int nodo_, double z_){
        if (nodo_ >=0 && nodo_<gradosLibertad)
            nodos.get(nodo_).setZ(z_);
    }
    
    public void rotarX(int nodo_, double x_){
        if (nodo_ >=0 && nodo_<gradosLibertad)
            nodos.get(nodo_).rotarX(x_);
    }
    
    public void rotarY(int nodo_, double y_){
        if (nodo_ >=0 && nodo_<gradosLibertad)
            nodos.get(nodo_).rotarY(y_);
    }

    public void rotarZ(int nodo_, double z_){
        if (nodo_ >=0 && nodo_<gradosLibertad)
            nodos.get(nodo_).rotarZ(z_);
    }
    
    public TMatriz getPos(int nodo_){
        if (nodo_ < gradosLibertad && nodo_ >= 0) 
           return nodos.get(nodo_).getMatrizTransfGlobal().producto(nodos.get(nodo_).getCoordenadas());
        else
             return new TMatriz(0, 0);
    }
    
    public TNodo getNodo(int nodo_){
        return nodos.get(nodo_);
    }
    
    public TMatriz getVectorTo(int nodo_, double x_, double y_, double z_){
        if (nodo_ < 0 || nodo_ >= gradosLibertad) return new TMatriz(4, 0);
        return nodos.get(gradosLibertad-1).getGlobarVector(x_, y_, z_);
    }
    
    public TMatriz getVectorTo(double x_, double y_, double z_){
        return nodos.get(gradosLibertad-1).getGlobarVector(x_, y_, z_);
    }
    
    
}
