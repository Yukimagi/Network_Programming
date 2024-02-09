package work21_2;
import java.awt.*;
import java.awt.event.*;

public class work21_2 extends Frame implements Runnable {

    int posX, posY;
    Image img;
    int[] area_flag = new int[9];
    int i;
    Image bufferPage=null;

    public static void main(String args[]) {
      work21_2 ClientStart=new work21_2();
    }
    
    public work21_2() {
        super("work21_2");
        setSize(250,280);

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
         if((posX>30)&&(posX<90)&&(posY>70)&&(posY<130)&&(area_flag[0]==0)) 
           {area_flag[0] = 1;}
         else if ((posX>90)&&(posX<150)&&(posY>70)&&(posY<130)&&(area_flag[1]==0)) 
                 {area_flag[1] = 1;}
         else if ((posX>150)&&(posX<210)&&(posY>70)&&(posY<130)&&(area_flag[2]==0)) 
                 {area_flag[2] = 1;}
         else if((posX>30)&&(posX<90)&&(posY>130)&&(posY<190)&&(area_flag[3]==0)) 
                 {area_flag[3] = 1;}
         else if((posX>90)&&(posX<150)&&(posY>130)&&(posY<190)&&(area_flag[4]==0)) 
                 {area_flag[4] = 1;}
         else if((posX>150)&&(posX<210)&&(posY>130)&&(posY<190)&&(area_flag[5]==0)) 
                 {area_flag[5] = 1;}
         else if((posX>30)&&(posX<90)&&(posY>190)&&(posY<250)&&(area_flag[6]==0)) 
                 {area_flag[6] = 1;}
         else if((posX>90)&&(posX<150)&&(posY>190)&&(posY<250)&&(area_flag[7]==0)) 
                 {area_flag[7] = 1;}
         else if((posX>150)&&(posX<210)&&(posY>190)&&(posY<250)&&(area_flag[8]==0)) 
                 {area_flag[8] = 1;}
         repaint();
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
          bufferPage = createImage(250, 250);
       bufferg = bufferPage.getGraphics();

       bufferg.drawLine(90,50,90,230);
       bufferg.drawLine(150,50,150,230);
       bufferg.drawLine(30,110,210,110);
       bufferg.drawLine(30,170,210,170);

       for (i=0; i<9; i++) {
           if (area_flag[0] == 1)
              bufferg.drawImage(img, 42, 60, this);
           if (area_flag[1] == 1)
              bufferg.drawImage(img, 102, 60, this);
           if (area_flag[2] == 1)
              bufferg.drawImage(img, 164, 60, this);
           if (area_flag[3] == 1)
              bufferg.drawImage(img, 42, 125, this);
           if (area_flag[4] == 1)
              bufferg.drawImage(img, 102, 125, this);
           if (area_flag[5] == 1)
              bufferg.drawImage(img, 164, 125, this);
           if (area_flag[6] == 1)
              bufferg.drawImage(img, 42, 187, this);
           if (area_flag[7] == 1)
              bufferg.drawImage(img, 102, 187, this);
           if (area_flag[8] == 1)
              bufferg.drawImage(img, 164, 187, this);
       }
       bufferg.dispose();
       g.drawImage(bufferPage, getInsets().left, getInsets().top, this);
    }
}





