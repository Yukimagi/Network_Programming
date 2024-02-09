package work20_2;
import java.awt.*;
import java.awt.event.*;

public class work20_2 extends Frame implements Runnable {
    int frame_size=350;
    int posX=0, posY=frame_size/2, dx=5, dy=0;
    Image img;
    Image bufferPage=null;

    public static void main(String args[]) {
      work20_2 ClientStart=new work20_2();
    }
    
    public work20_2() {
        super("work20_2");
        setSize(frame_size, frame_size);

        img = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("fly1.gif"));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

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





