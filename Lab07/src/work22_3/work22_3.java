package work22_3;
import java.awt.*;
import java.awt.event.*;

public class work22_3 extends Frame implements Runnable {
    int frame_size = 350;
    int posX=0, posY=frame_size/2, dx=0, dy=0;
    int num=1, flag;
    Image img0, img1, img2;
    Image bufferPage=null;

    public static void main(String args[]) {
      work22_3 ClientStart=new work22_3();
    }
    
    public work22_3() {
        super("work22_3");
        setSize(frame_size, frame_size);

        img0 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("fly0.gif"));
        img1 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("fly1.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("fly2.gif"));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        enableEvents(AWTEvent.KEY_EVENT_MASK);
        setVisible(true);
        new Thread(this).start();
    }

    public void processKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED) {
              switch(e.getKeyCode()) {
                   case KeyEvent.VK_RIGHT:
                          dx = 2; dy = 0;
                          break;
                   case KeyEvent.VK_LEFT:
                          dx = -2; dy = 0;
                          break;
                   case KeyEvent.VK_UP:
                          dx = 0; dy = -2; 
                          break;
                   case KeyEvent.VK_DOWN:
                          dx = 0; dy = 2;
                          break;
              }
            posX = posX + dx;
            posY = posY + dy;
        }
    }

    public void run() {
      while(true) {
         posX = posX + dx;
         posY = posY + dy;
         flag = num % 3;
         repaint();
         num = num + 1;         

         if(posX <= 0) dx = 5;
         else if((posX+60) >= frame_size) dx = -5;
         if((posY-10) <= 0) dy = 5;
         else if((posY+50) >= frame_size) dy = -5;
 
         try{Thread.sleep(170);}
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

      if(flag == 0)
          bufferg.drawImage(img0, posX, posY, this);
      if(flag == 1)
          bufferg.drawImage(img1, posX, posY, this);
      if(flag == 2)
          bufferg.drawImage(img2, posX, posY, this);

      bufferg.dispose();
      g.drawImage(bufferPage, getInsets().left, getInsets().top, this);
    }
}





