package work20_3;
import java.awt.*;
import java.awt.event.*;

public class work20_3 extends Frame implements Runnable {
    int frame_size = 350;
    int posX=0, posY=frame_size/2, dx=5, dy=0;
    int num=1, flag;
    Image img0, img1, img2;
    Image bufferPage=null;

    public static void main(String args[]) {
      work20_3 ClientStart=new work20_3();
    }
    
    public work20_3() {
        super("work20_3");
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

        setVisible(true);
        new Thread(this).start();
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

       if(flag == 0)
          bufferg.drawImage(img0, posX, posY, this);
       else if(flag == 1)
          bufferg.drawImage(img1, posX, posY, this);
       else if(flag == 2)
          bufferg.drawImage(img2, posX, posY, this);

       bufferg.dispose();
       g.drawImage(bufferPage, getInsets().left, getInsets().top, this);
    }
}





