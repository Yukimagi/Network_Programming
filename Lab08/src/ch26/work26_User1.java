package ch26;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class work26_User1 extends Frame implements Runnable {

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

    public static void main(String args[]) {
    	//if (args.length < 2){
        //   System.out.println("USAGE: java work25_User1 [servername] [port]");	
        //   System.exit(1);
        //}

        //servername= args[0];
        servername= "localhost";	
        //port=Integer.parseInt(args[1]);
        port = 1235;
      work26_User1 ClientStart=new work26_User1();
    }

    
    public work26_User1() {
        super("work26_User1");
        setSize(256, 256);

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

    public void paint(Graphics g) {
        g.drawLine(90,50,90,230);
        g.drawLine(150,50,150,230);
        g.drawLine(30,110,210,110);
        g.drawLine(30,170,210,170);

        for (i=0; i<9; i++) {
           if (area_flag[0] == 1)
              g.drawImage(img1, 42, 60, this);
           if (area_flag[1] == 1)
              g.drawImage(img1, 102, 60, this);
           if (area_flag[2] == 1)
              g.drawImage(img1, 164, 60, this);
           if (area_flag[3] == 1)
              g.drawImage(img1, 42, 125, this);
           if (area_flag[4] == 1)
              g.drawImage(img1, 102, 125, this);
           if (area_flag[5] == 1)
              g.drawImage(img1, 164, 125, this);
           if (area_flag[6] == 1)
              g.drawImage(img1, 42, 187, this);
           if (area_flag[7] == 1)
              g.drawImage(img1, 102, 187, this);
           if (area_flag[8] == 1)
              g.drawImage(img1, 164, 187, this);
         }

        for (i=0; i<9; i++) {
           if (area_flag[0] == 2)
                 g.drawImage(img2, 42, 60, this);
           if (area_flag[1] == 2)
              g.drawImage(img2, 102, 60, this);
           if (area_flag[2] == 2)
              g.drawImage(img2, 164, 60, this);
           if (area_flag[3] == 2)
              g.drawImage(img2, 42, 125, this);
           if (area_flag[4] == 2)
              g.drawImage(img2, 102, 125, this);
           if (area_flag[5] == 2)
              g.drawImage(img2, 164, 125, this);
           if (area_flag[6] == 2)
              g.drawImage(img2, 42, 187, this);
           if (area_flag[7] == 2)
              g.drawImage(img2, 102, 187, this);
           if (area_flag[8] == 2)
              g.drawImage(img2, 164, 187, this);
          }
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

            if((pos1X>30)&&(pos1X<90)&&(pos1Y>50)&&(pos1Y<110)&&(w%2==0)&&(area_flag[0]==0)) 
              {area_flag[0] = 1;}
            else if ((pos1X>90)&&(pos1X<150)&&(pos1Y>50)&&(pos1Y<110)&&(w%2==0)&&(area_flag[1]==0)) 
                    {area_flag[1] = 1;}
            else if ((pos1X>150)&&(pos1X<210)&&(pos1Y>50)&&(pos1Y<110)&&(w%2==0)&&(area_flag[2]==0)) 
                    {area_flag[2] = 1;}
            else if((pos1X>30)&&(pos1X<90)&&(pos1Y>110)&&(pos1Y<170)&&(w%2==0)&&(area_flag[3]==0)) 
                    {area_flag[3] = 1;}
            else if((pos1X>90)&&(pos1X<150)&&(pos1Y>110)&&(pos1Y<170)&&(w%2==0)&&(area_flag[4]==0)) 
                    {area_flag[4] = 1;}
            else if((pos1X>150)&&(pos1X<210)&&(pos1Y>110)&&(pos1Y<170)&&(w%2==0)&&(area_flag[5]==0)) 
                    {area_flag[5] = 1;}
            else if((pos1X>30)&&(pos1X<90)&&(pos1Y>170)&&(pos1Y<230)&&(w%2==0)&&(area_flag[6]==0)) 
                    {area_flag[6] = 1;}
            else if((pos1X>90)&&(pos1X<150)&&(pos1Y>170)&&(pos1Y<230)&&(w%2==0)&&(area_flag[7]==0)) 
                    {area_flag[7] = 1;}
            else if((pos1X>150)&&(pos1X<210)&&(pos1Y>170)&&(pos1Y<230)&&(w%2==0)&&(area_flag[8]==0)) 
                    {area_flag[8] = 1;}

            if((pos2X>30)&&(pos2X<90)&&(pos2Y>50)&&(pos2Y<110)&&(w%2==0)&&(area_flag[0]==0)) 
              {area_flag[0] = 2;}
            else if ((pos2X>90)&&(pos2X<150)&&(pos2Y>50)&&(pos2Y<110)&&(w%2==0)&&(area_flag[1]==0)) 
                    {area_flag[1] = 2;}
            else if ((pos2X>150)&&(pos2X<210)&&(pos2Y>50)&&(pos2Y<110)&&(w%2==0)&&(area_flag[2]==0)) 
                    {area_flag[2] = 2;}
            else if((pos2X>30)&&(pos2X<90)&&(pos2Y>110)&&(pos2Y<170)&&(w%2==0)&&(area_flag[3]==0)) 
                    {area_flag[3] = 2;}
            else if((pos2X>90)&&(pos2X<150)&&(pos2Y>110)&&(pos2Y<170)&&(w%2==0)&&(area_flag[4]==0)) 
                    {area_flag[4] = 2;}
            else if((pos2X>150)&&(pos2X<210)&&(pos2Y>110)&&(pos2Y<170)&&(w%2==0)&&(area_flag[5]==0)) 
                    {area_flag[5] = 2;}
            else if((pos2X>30)&&(pos2X<90)&&(pos2Y>170)&&(pos2Y<230)&&(w%2==0)&&(area_flag[6]==0)) 
                    {area_flag[6] = 2;}
            else if((pos2X>90)&&(pos2X<150)&&(pos2Y>170)&&(pos2Y<230)&&(w%2==0)&&(area_flag[7]==0)) 
                    {area_flag[7] = 2;}
            else if((pos2X>150)&&(pos2X<210)&&(pos2Y>170)&&(pos2Y<230)&&(w%2==0)&&(area_flag[8]==0)) 
                    {area_flag[8] = 2;}
               
             repaint();
        } 
        catch (Exception f) {
            f.printStackTrace();
        }
      }
    }
}





