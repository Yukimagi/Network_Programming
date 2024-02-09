package work22_1;
import java.awt.*;
import java.awt.event.*;

public class work22_1 extends Frame implements Runnable {
    int posX=150, posY=235, dx, dy;
    Image img;

    public static void main(String args[]) {
      work22_1 ClientStart=new work22_1();
    }
    
    public work22_1() {
        super("work22_1");
        setSize(350,350);

        img = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("car090.gif"));
        enableEvents(AWTEvent.KEY_EVENT_MASK);

        setVisible(true);
        new Thread(this).start();
    }

    public void processKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED) {
              switch(e.getKeyCode()) {
                   case KeyEvent.VK_RIGHT:
                          dx = 5; dy = 0;
                          break;
                   case KeyEvent.VK_LEFT:
                          dx = -5; dy = 0;
                          break;
                   case KeyEvent.VK_UP:
                          dx = 0; dy = -5; 
                          break;
                   case KeyEvent.VK_DOWN:
                          dx = 0; dy = 5;
                          break;
              }
            posX = posX + dx;
            posY = posY + dy;
        }
    }

    public void run() {
      while(true) {
         repaint();
         try{Thread.sleep(10);}
         catch(InterruptedException e) {;}
      }
    }

    public void paint(Graphics g) {
          g.drawImage(img, posX, posY, this);
    }
}





