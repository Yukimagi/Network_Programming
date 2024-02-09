package work23_2;
import java.awt.*;
import java.awt.event.*;

public class work23_2 extends Frame implements Runnable {
    int posX=150, posY=235, dx, dy, num=0, flag=0;
    int b0posX, b0posY, b0run=0;
    int b1posX, b1posY, b1run=0;
    int b2posX, b2posY, b2run=0;
     
    Image img;

    public static void main(String args[]) {
      work23_2 ClientStart=new work23_2();
    }
    
    public work23_2() {
        super("work23_2");
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
                          if(flag==0) {
                            b0posX = posX + 30;
                            b0posY = posY -5;
                            b0run = 1;
                          }
                         else if(flag==1) {
                            b1posX = posX + 30;
                            b1posY = posY -5;
                            b1run = 1;
                          }
                         else if(flag==2) {
                            b2posX = posX + 30;
                            b2posY = posY -5;
                            b2run = 1;
                          }
                          break;
              }
            posX = posX + dx;
            posY = posY + dy;

        }
    }

    public void run() {
      while(true) {
         if(flag==0) {
           if(b0posY <= 0) b0run = 0;
           if(b0run == 1) b0posY = b0posY - 5;
         }
         else if(flag==1) {
           if(b1posY <= 0) b1run = 0;
           if(b1run == 1) b1posY = b1posY - 5;
         }
         else if(flag==2) {
           if(b2posY <= 0) b2run = 0;
           if(b2run == 1) b2posY = b2posY - 5;
         }

         flag = num % 3;
         repaint();
         num = num + 1;
         try{Thread.sleep(20);}
         catch(InterruptedException e) {;}
      }
    }

    public void paint(Graphics g) {
          g.drawImage(img, posX, posY, this);
          if(flag==0)
             g.fillRect(b0posX, b0posY, 3, 5);
          if(flag==1)
             g.fillRect(b1posX, b1posY, 3, 5);
          if(flag==2)
             g.fillRect(b2posX, b2posY, 3, 5);
    }
}





