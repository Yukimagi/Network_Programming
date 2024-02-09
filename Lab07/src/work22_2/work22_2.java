package work22_2;
import java.awt.*;
import java.awt.event.*;

public class work22_2 extends Frame implements Runnable {
    int posX=150, posY=235, dx, dy;
    Image img;
    Image bufferPage=null;

    public static void main(String args[]) {
      work22_2 ClientStart=new work22_2();
    }
    
    public work22_2() {
        super("work22_2");
        setSize(350,350);

        img = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("car090.gif"));
        enableEvents(AWTEvent.KEY_EVENT_MASK);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

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

    public void update(Graphics g) {
         paint(g);
    }

    public void paint(Graphics g) {
         Graphics bufferg;
         if(bufferPage == null)
            bufferPage = createImage(350, 350);
         bufferg = bufferPage.getGraphics();

         bufferg.drawImage(img, posX, posY, this);

         bufferg.dispose();
         g.drawImage(bufferPage, getInsets().left, getInsets().top, this);
    }
}





