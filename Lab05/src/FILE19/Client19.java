package FILE19;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

class Client19 extends Frame implements ActionListener, Runnable {
  ChatRoom ChatWork = new ChatRoom();
  TextField ChatInput = new TextField();
  Socket socket;
  static String servername;
  static int port;
  static String user;
  DataOutputStream  outstream;
  DataInputStream  instream;
  
  public Client19() {
     super("Client");
     try{
         socket=new Socket(InetAddress.getByName(servername),port);
         outstream = new DataOutputStream(socket.getOutputStream());
         instream = new DataInputStream(socket.getInputStream()); 

         this.outstream = new DataOutputStream(outstream);
         this.instream = new DataInputStream(instream);
 
         add(ChatWork, BorderLayout.CENTER);
         add(ChatInput, BorderLayout.SOUTH);
         ChatInput.addActionListener(this);

         setSize(500, 350);
         show();
         new Thread(this).start();
      }
      catch (Exception e) {
            e.printStackTrace();
      }
  }

   public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == ChatInput) {
            try {
                outstream.writeUTF(user+"> "+ChatInput.getText());
                ChatWork.PrintChat(ChatInput.getText(), Color.red);
            } catch (Exception e) {
                ChatWork.PrintChat("Connection is Interrupted", Color.green);
            }
            ChatInput.setText(null);
        } 
    }

   public void run() {
        try {
            while (true) {
                String NetTransferLine = instream.readUTF();
                ChatWork.PrintChat(NetTransferLine, Color.black);
            }
        } 
        catch (Exception e) {
            ChatWork.PrintChat("Connection is Interrupted!", Color.green);
        }
    }


  public static void main(String args[]) {
      if (args.length < 3){
         System.out.println("USAGE: java Client19 [servername] [port] [user]");	
         System.exit(1);
      }

      servername= args[0];
      port=Integer.parseInt(args[1]);
      user=args[2];
      Client19 ClientStart=new Client19();
  }
}

class ChatRoom extends Component {
    Vector PastLine = new Vector();
    Vector NewLine = new Vector();

    synchronized void PrintChat(String s, Color c) {
        NewLine.addElement(new SaveLine(s, c));
        repaint();
    }

    public void paint(Graphics g) {
        synchronized (this) {
            while (NewLine.size() > 0) {
                PastLine.addElement(NewLine.elementAt(0));
                NewLine.removeElementAt(0);
            }
            while (PastLine.size() > 40) {
                PastLine.removeElementAt(0);
            }
        }

        FontMetrics fontM = g.getFontMetrics();
        int margin = fontM.getHeight()/2;
        int w = getSize().width;
        int y = getSize().height-fontM.getHeight()-margin;

        for (int i=PastLine.size()-1; i>=0; i--) {
            SaveLine ShowLine = (SaveLine)PastLine.elementAt(i);
            g.setColor(ShowLine.clr);
            g.setFont(new Font(ShowLine.str,Font.BOLD,12)); 
            g.drawString(ShowLine.str, margin, y+fontM.getAscent());
            y -= fontM.getHeight();
        }
    }
}

class SaveLine {
    String str;
    Color clr;
    SaveLine(String str, Color clr) {
        this.str = str;
        this.clr = clr;
    }
}
