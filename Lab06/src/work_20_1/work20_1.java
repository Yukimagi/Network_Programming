package work_20_1;
import java.awt.*;
import java.awt.event.*;

public class work20_1 extends Frame implements Runnable {
    int frame_size = 350;
    int posX=0, posY=frame_size/2, dx=5, dy=0;
    Image img;

    public static void main(String args[]) {
      work20_1 ClientStart=new work20_1();
    }
    
    public work20_1() {
        super("work20_1");
        setSize(frame_size, frame_size);

        img = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("fly1.gif"));

        setVisible(true);
        new Thread(this).start();
    }

    public void run() {
      while(true) {
         posX = posX + dx;
         posY = posY + dy;
         repaint();

         if(posX <= 0) dx = 5;
         else if((posX+60) >= frame_size) dx = -5;
 
         try{Thread.sleep(250);}
         catch(InterruptedException e) {;}
      }
    }

    public void paint(Graphics g) {
          g.drawImage(img, posX, posY, this);
    }
}





