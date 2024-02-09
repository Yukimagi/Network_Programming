package work21_1;
import java.awt.*;
import java.awt.event.*;

public class work21_1 extends Frame implements Runnable {

    int posX=50, posY=50;
    Image img;

    public static void main(String args[]) {
      work21_1 ClientStart=new work21_1();
    }
    
    public work21_1() {
        super("work21_1");
        setSize(250,250);

        img = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("Circle.gif"));
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        setVisible(true);
        new Thread(this).start();
    }

    public void processMouseEvent(MouseEvent e) {
        if(e.getID() == MouseEvent.MOUSE_PRESSED) {
            posX = e.getX();
            posY = e.getY();
        }
    }

    public void run() {
      while(true) {
         repaint();
         try{Thread.sleep(250);}
         catch(InterruptedException e) {;}
      }
    }

    public void paint(Graphics g) {
        g.drawImage(img, posX, posY, this);
    }


}





