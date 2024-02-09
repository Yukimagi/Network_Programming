package ch25;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class work25_User2 extends Frame implements Runnable {

    Socket socket;
    static String servername;
    static int port;
    static String user;
    DataOutputStream  outstream;
    DataInputStream  instream;
    int pos1X=120, pos1Y=240, pos2X=120, pos2Y=50;
    int pos1X_send, pos1Y_send, pos2X_send, pos2Y_send, pos_rcv;

    Image img1, img2;

    public static void main(String args[]) {
      //if (args.length < 2){
      //   System.out.println("USAGE: java work25_User1 [servername] [port]");	
      //   System.exit(1);
      //}

      //servername= args[0];
      servername= "localhost";	
      //port=Integer.parseInt(args[1]);
      port = 1234;
      work25_User2 ClientStart=new work25_User2();
    }

    
    public work25_User2() {
        super("work25_User2");
        setSize(250,280);

        img1 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("Circle.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("Cross.gif"));

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        setVisible(true);

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
            pos2X = e.getX();
            pos2Y = e.getY();

            pos2X_send = pos2X * 100 + 2;
            pos2Y_send = pos2Y * 100 + 3;
            outstream.writeInt(pos2X_send);
            outstream.writeInt(pos2Y_send);
          }
          catch (Exception f) {
            f.printStackTrace();
          }
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img1, pos1X, pos1Y, this);
        g.drawImage(img2, pos2X, pos2Y, this);
    }

    public void run() {
      while(true) {

        try {
             pos_rcv = instream.readInt();

             if (pos_rcv % 100 == 0) 
                pos1X = (pos_rcv - 0) / 100;
             else if (pos_rcv % 100 == 1) 
                pos1Y = (pos_rcv - 1) / 100;
             else if (pos_rcv % 100 == 2) 
                pos2X = (pos_rcv - 2) / 100;
             else if (pos_rcv % 100 == 3) 
                pos2Y = (pos_rcv - 3) / 100;

             repaint();
        } 
        catch (Exception f) {
            f.printStackTrace();
        }
      }
    }
}





