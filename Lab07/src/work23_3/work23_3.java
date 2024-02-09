package work23_3;
import java.awt.*;
import java.awt.event.*;

public class work23_3 extends Frame implements Runnable {
    int posX=150, posY=235, dx, dy, num=0, flag=0;
    int b0posX, b0posY, b0run=0;
    int b1posX, b1posY, b1run=0;
    int b2posX, b2posY, b2run=0;
    int b3posX, b3posY, b3run=0;
    int b4posX, b4posY, b4run=0;
    int b5posX, b5posY, b5run=0;
     
    Image img;
    Image bufferPage=null;

    public static void main(String args[]) {
      work23_3 ClientStart=new work23_3();
    }
    
    public work23_3() {
        super("work23_3");
        setSize(350,350);

        img = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("car090.gif"));

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
                          flag = num % 6;
                          if(flag==0) {
                            b0posX = posX + 30;
                            b0posY = posY;
                            b0run = 1;
                          }
                         else if(flag==1) {
                            b1posX = posX + 30;
                            b1posY = posY;
                            b1run = 1;
                          }
                         else if(flag==2) {
                            b2posX = posX + 30;
                            b2posY = posY;
                            b2run = 1;
                          }
                          if(flag==3) {
                            b3posX = posX + 30;
                            b3posY = posY;
                            b3run = 1;
                          }
                         else if(flag==4) {
                            b4posX = posX + 30;
                            b4posY = posY;
                            b4run = 1;
                          }
                         else if(flag==5) {
                            b5posX = posX + 30;
                            b5posY = posY;
                            b5run = 1;
                          }
                          num = num + 1;
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
         }
         else if(flag==1) {
           if(b1posY <= 0) b1run = 0;
          }
         else if(flag==2) {
           if(b2posY <= 0) b2run = 0;
         }
         if(flag==3) {
           if(b3posY <= 0) b3run = 0;
         }
         else if(flag==4) {
           if(b4posY <= 0) b4run = 0;
          }
         else if(flag==5) {
           if(b5posY <= 0) b5run = 0;
         }
         if(b0run == 1) b0posY = b0posY - 10;
         if(b1run == 1) b1posY = b1posY - 10;
         if(b2run == 1) b2posY = b2posY - 10;
         if(b3run == 1) b3posY = b3posY - 10;
         if(b4run == 1) b4posY = b4posY - 10;
         if(b5run == 1) b5posY = b5posY - 10;
         repaint();
         try{Thread.sleep(50);}
         catch(InterruptedException e) {;}
      }
    }

    public void update(Graphics g) {
          paint(g);
    }

    public void paint(Graphics g) {
          Graphics bufferg;
          if(bufferPage==null)
            bufferPage = createImage(350,350);
          bufferg = bufferPage.getGraphics();
          bufferg.setColor(Color.black);
          bufferg.drawImage(img, posX, posY, this);
          if(b0run==1){
             bufferg.setColor(Color.black);
             bufferg.fillRect(b0posX, b0posY, 3, 5);
             bufferg.setColor(Color.white);
             bufferg.fillRect(b0posX, b0posY+5, 3, 25);
          }
          if(b1run==1){
             bufferg.setColor(Color.black);
             bufferg.fillRect(b1posX, b1posY, 3, 5);
             bufferg.setColor(Color.white);
             bufferg.fillRect(b1posX, b1posY+5, 3, 25);
          }
          if(b2run==1){
             bufferg.setColor(Color.black);
             bufferg.fillRect(b2posX, b2posY, 3, 5);
             bufferg.setColor(Color.white);
             bufferg.fillRect(b2posX, b2posY+5, 3, 25);
          }
          if(b3run==1){
             bufferg.setColor(Color.black);
             bufferg.fillRect(b3posX, b3posY, 3, 5);
             bufferg.setColor(Color.white);
             bufferg.fillRect(b3posX, b3posY+5, 3, 25);
          }
          if(b4run==1){
             bufferg.setColor(Color.black);
             bufferg.fillRect(b4posX, b4posY, 3, 5);
             bufferg.setColor(Color.white);
             bufferg.fillRect(b4posX, b4posY+5, 3, 25);
          }
          if(b5run==1){
             bufferg.setColor(Color.black);
             bufferg.fillRect(b5posX, b5posY, 3, 5);
             bufferg.setColor(Color.white);
             bufferg.fillRect(b5posX, b5posY+5, 3, 25);
          }
          bufferg.dispose();
          g.drawImage(bufferPage, getInsets().left, getInsets().top, this);
    }
}





