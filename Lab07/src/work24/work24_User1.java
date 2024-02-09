package work24;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class work24_User1 extends Frame implements Runnable {

    Socket socket;
    static String servername;
    static int port;
    DataOutputStream  outstream;
    DataInputStream  instream;
    int pos1X=120, pos1Y=120;
    int pos1X_send, pos1Y_send, pos_rcv;

    Image img1;

    public static void main(String args[]) {
      /*if (args.length < 2){
         System.out.println("USAGE: java work24_User1 [servername] [port]");	
         System.exit(1);
      }*/

      //servername= args[0];
      servername = "localhost";
      //port=Integer.parseInt(args[1]);
      port =1111;
      work24_User1 ClientStart=new work24_User1();
    }

    public work24_User1() {
        super("work24_User1");
        setSize(250,280);

        img1 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("Circle.gif"));

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
        g.drawImage(img1, pos1X, pos1Y, this);
   }

    public void run() {
      while(true) {
        try {
             pos_rcv = instream.readInt();
             if (pos_rcv % 100 == 0) 
                pos1X = (pos_rcv - 0) / 100;
             else if (pos_rcv % 100 == 1) 
                pos1Y = (pos_rcv - 1) / 100;

             repaint();
        } 
        catch (Exception f) {
            f.printStackTrace();
        }
      }
    }
}





