
package robotcalc;

import Viwer.Display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import robotcalc.Framework.TRobot;



/**
 *
 * @author user
 */
public class RobotCalc {

    public static void main(String[] args) {
        Display d = new Display();
        d.run();
        TRobot robot = new TRobot(6);
        Graphics g = d.getGraphic();
        //g.setColor(Color.GREEN);
        //g.drawString("RoboCalc.", 5, 15);
        
        //robot.draw(d.getGraphic());
        //d.repaint();
    }

}
