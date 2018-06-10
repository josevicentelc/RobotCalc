package Viwer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


/**
 *
 * @author Josev
 */
public class Display  {

    Canvas canvas;
    final String title = "Test Window";
    final int width = 1200;
    final int height = width / 16 * 9;
    JFrame frame;
    Graphics graphics; 
    BufferStrategy bufferStrategy;

    
    public void run(){
        //Creating the frame.
        frame = new JFrame(title);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //Creating the canvas.
        canvas = new Canvas();

        canvas.setSize(width, height);
        canvas.setBackground(Color.BLACK);
        canvas.setVisible(true);
        canvas.setFocusable(false);
        frame.add(canvas);

        canvas.createBufferStrategy(3);
    }

    
    
    public Graphics getGraphic(){
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();        
        graphics.clearRect(0, 0, width, height);
        graphics.setColor(Color.GREEN);
        graphics.drawString("RoboCalc.", 5, 15);        
        bufferStrategy.show();
        graphics.dispose();
        frame.repaint();
        return graphics;
    }
    
    public void repaint(){
        if (bufferStrategy != null){
            bufferStrategy.show();
            //graphics.dispose();
        }else{
            System.out.println("Cant repaint, Buffer is null");
        }
            
    }
    
}
