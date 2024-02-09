package work23_1;
import java.awt.*;
import java.awt.event.*;

public class work23_1 extends Frame implements Runnable {
    int posX=150, posY=235, dx, dy;
    int bposX, bposY, dbx, dby, bflag=0;
    Image img;

    public static void main(String args[]) {
      work23_1 ClientStart=new work23_1();
    }
    
    public work23_1() {
        super("work23_1");
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
                   case 32:
                          bposX = posX + 30;
                          bposY = posY - 5;
                          bflag = 1;
                          break;
              }
            posX = posX + dx;
            posY = posY + dy;
        }
    }

    public void run() {
      while(true) {
         if(bposY <= 0) bflag = 0; //�l�u�������
         if(bflag == 1) bposY = bposY - 5;//�l�u���e��

         repaint();
         try{Thread.sleep(30);}
         catch(InterruptedException e) {;}
      }
    }

    public void paint(Graphics g) {
          g.drawImage(img, posX, posY, this);
          g.fillRect(bposX, bposY, 3, 5); //�e�l�u
    }
}





