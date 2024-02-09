package ch27;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class work27_User1 extends Frame implements Runnable {

    Socket socket;
    static String servername;
    static int port;
    static String user;
    DataOutputStream  outstream;
    DataInputStream  instream;
    int pos1X, pos1Y, pos2X, pos2Y;
    int pos1X_send, pos1Y_send, pos2X_send, pos2Y_send, pos_rcv;
    int[] area_flag = new int[9];
    int i, w;

    Image img1, img2;
    Image bufferPage=null;

    public static void main(String args[]) {
    	//if (args.length < 2){
        //   System.out.println("USAGE: java work25_User1 [servername] [port]");	
        //   System.exit(1);
        //}

        //servername= args[0];
        servername= "localhost";	
        //port=Integer.parseInt(args[1]);
        port = 1235;
      work27_User1 ClientStart=new work27_User1();
    }

    
    public work27_User1() {
        super("work27_User1");
        setSize(250, 280);

        img1 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("Circle.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("Cross.gif"));
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        for (i=0; i<9; i++)
           area_flag[i] = 0;

     try{
         socket=new Socket(InetAddress.getByName(servername),port);
         outstream = new DataOutputStream(socket.getOutputStream());
         instream = new DataInputStream(socket.getInputStream()); 

         this.outstream = new DataOutputStream(outstream);
         this.instream = new DataInputStream(instream);

         new Thread(this).start();
      }
      catch (Exception e) {
            e.printStackTrace();
      }
    }

    public void processMouseEvent(MouseEvent e) {
        if(e.getID() == MouseEvent.MOUSE_PRESSED) {
          try{
            pos1X = e.getX();
            pos1Y = e.getY();

            pos1X_send = pos1X * 100 + 0;
            pos1Y_send = pos1Y * 100 + 1;
            outstream.writeInt(pos1X_send);
            outstream.writeInt(pos1Y_send);
          }
          catch (Exception f) {
            f.printStackTrace();
          }
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
              bufferg.drawImage(img1, 42, 60, this);
           if (area_flag[1] == 1)
              bufferg.drawImage(img1, 102, 60, this);
           if (area_flag[2] == 1)
              bufferg.drawImage(img1, 164, 60, this);
           if (area_flag[3] == 1)
              bufferg.drawImage(img1, 42, 125, this);
           if (area_flag[4] == 1)
              bufferg.drawImage(img1, 102, 125, this);
           if (area_flag[5] == 1)
              bufferg.drawImage(img1, 164, 125, this);
           if (area_flag[6] == 1)
              bufferg.drawImage(img1, 42, 187, this);
           if (area_flag[7] == 1)
              bufferg.drawImage(img1, 102, 187, this);
           if (area_flag[8] == 1)
              bufferg.drawImage(img1, 164, 187, this);
         }

        for (i=0; i<9; i++) {
           if (area_flag[0] == 2)
                 bufferg.drawImage(img2, 42, 60, this);
           if (area_flag[1] == 2)
              bufferg.drawImage(img2, 102, 60, this);
           if (area_flag[2] == 2)
              bufferg.drawImage(img2, 164, 60, this);
           if (area_flag[3] == 2)
              bufferg.drawImage(img2, 42, 125, this);
           if (area_flag[4] == 2)
              bufferg.drawImage(img2, 102, 125, this);
           if (area_flag[5] == 2)
              bufferg.drawImage(img2, 164, 125, this);
           if (area_flag[6] == 2)
              bufferg.drawImage(img2, 42, 187, this);
           if (area_flag[7] == 2)
              bufferg.drawImage(img2, 102, 187, this);
           if (area_flag[8] == 2)
              bufferg.drawImage(img2, 164, 187, this);
          }
       bufferg.dispose();
       g.drawImage(bufferPage, getInsets().left, getInsets().top, this);
    }

    public void run() {
      while(true) {

        try {
             pos_rcv = instream.readInt();
             w=w+1;

             if (pos_rcv % 100 == 0) 
                pos1X = (pos_rcv - 0) / 100;
             else if (pos_rcv % 100 == 1) 
                pos1Y = (pos_rcv - 1) / 100;
             else if (pos_rcv % 100 == 2) 
                pos2X = (pos_rcv - 2) / 100;
             else if (pos_rcv % 100 == 3) 
                pos2Y = (pos_rcv - 3) / 100;

            if((pos1X>30)&&(pos1X<90)&&(pos1Y>70)&&(pos1Y<130)&&(w%2==0)&&(area_flag[0]==0)) 
              {area_flag[0] = 1;}
            else if ((pos1X>90)&&(pos1X<150)&&(pos1Y>70)&&(pos1Y<130)&&(w%2==0)&&(area_flag[1]==0)) 
                    {area_flag[1] = 1;}
            else if ((pos1X>150)&&(pos1X<210)&&(pos1Y>70)&&(pos1Y<130)&&(w%2==0)&&(area_flag[2]==0)) 
                    {area_flag[2] = 1;}
            else if((pos1X>30)&&(pos1X<90)&&(pos1Y>130)&&(pos1Y<190)&&(w%2==0)&&(area_flag[3]==0)) 
                    {area_flag[3] = 1;}
            else if((pos1X>90)&&(pos1X<150)&&(pos1Y>130)&&(pos1Y<190)&&(w%2==0)&&(area_flag[4]==0)) 
                    {area_flag[4] = 1;}
            else if((pos1X>150)&&(pos1X<210)&&(pos1Y>130)&&(pos1Y<190)&&(w%2==0)&&(area_flag[5]==0)) 
                    {area_flag[5] = 1;}
            else if((pos1X>30)&&(pos1X<90)&&(pos1Y>190)&&(pos1Y<250)&&(w%2==0)&&(area_flag[6]==0)) 
                    {area_flag[6] = 1;}
            else if((pos1X>90)&&(pos1X<150)&&(pos1Y>190)&&(pos1Y<250)&&(w%2==0)&&(area_flag[7]==0)) 
                    {area_flag[7] = 1;}
            else if((pos1X>150)&&(pos1X<210)&&(pos1Y>190)&&(pos1Y<250)&&(w%2==0)&&(area_flag[8]==0)) 
                    {area_flag[8] = 1;}

            if((pos2X>30)&&(pos2X<90)&&(pos2Y>70)&&(pos2Y<130)&&(w%2==0)&&(area_flag[0]==0)) 
              {area_flag[0] = 2;}
            else if ((pos2X>90)&&(pos2X<150)&&(pos2Y>70)&&(pos2Y<130)&&(w%2==0)&&(area_flag[1]==0)) 
                    {area_flag[1] = 2;}
            else if ((pos2X>150)&&(pos2X<210)&&(pos2Y>70)&&(pos2Y<130)&&(w%2==0)&&(area_flag[2]==0)) 
                    {area_flag[2] = 2;}
            else if((pos2X>30)&&(pos2X<90)&&(pos2Y>130)&&(pos2Y<190)&&(w%2==0)&&(area_flag[3]==0)) 
                    {area_flag[3] = 2;}
            else if((pos2X>90)&&(pos2X<150)&&(pos2Y>130)&&(pos2Y<190)&&(w%2==0)&&(area_flag[4]==0)) 
                    {area_flag[4] = 2;}
            else if((pos2X>150)&&(pos2X<210)&&(pos2Y>130)&&(pos2Y<190)&&(w%2==0)&&(area_flag[5]==0)) 
                    {area_flag[5] = 2;}
            else if((pos2X>30)&&(pos2X<90)&&(pos2Y>190)&&(pos2Y<250)&&(w%2==0)&&(area_flag[6]==0)) 
                    {area_flag[6] = 2;}
            else if((pos2X>90)&&(pos2X<150)&&(pos2Y>190)&&(pos2Y<250)&&(w%2==0)&&(area_flag[7]==0)) 
                    {area_flag[7] = 2;}
            else if((pos2X>150)&&(pos2X<210)&&(pos2Y>190)&&(pos2Y<250)&&(w%2==0)&&(area_flag[8]==0)) 
                    {area_flag[8] = 2;}
               
             repaint();
        } 
        catch (Exception f) {
            f.printStackTrace();
        }
      }
    }
}





