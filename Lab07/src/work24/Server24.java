package work24;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server24 {
  private static ServerSocket SSocket;
  private static int port;
  private Hashtable ht = new Hashtable();
  Socket socket;

  public Server24() throws IOException {
     try {
          SSocket = new ServerSocket(port);
          System.out.println("Server created.");
          System.out.println("waiting for client to connect...");
	
          while (true) {
              socket = SSocket.accept();
              System.out.println("connected from Client " +
                                socket.getInetAddress().getHostAddress());
				
              DataOutputStream outstream = new DataOutputStream(socket.getOutputStream());
              ht.put(socket, outstream);
              Thread thread = new Thread(new ServerThread(socket, ht));
              thread.start();
           }
      }
      catch (IOException ex) {
           ex.printStackTrace();
      }
   }

  public static void main(String[] args) throws Exception {
      //if (args.length < 1) {
      //   System.out.println("Usage: java Server24 [port]");
      //   System.exit(1);
      //}

      //port=Integer.parseInt(args[0]) ;
      port = 1111;
      Server24 ServerStart=new Server24();
  }
}

class ServerThread extends Thread implements Runnable {
  private Socket socket;
  private Hashtable ht;

  public ServerThread(Socket socket, Hashtable ht) {
     this.socket = socket;
     this.ht = ht;
  }

  public void run() {
     DataInputStream instream;

     try {
          instream = new DataInputStream(socket.getInputStream());
			
          while (true) {
              int message = instream.readInt();
              System.out.println("Message: " + message);

              synchronized(ht) {
                   for (Enumeration e = ht.elements(); e.hasMoreElements(); ) {
                      DataOutputStream outstream = (DataOutputStream)e.nextElement();
			
                      try {
                           outstream.writeInt(message);
                      } 
                      catch (IOException ex) {
                           ex.printStackTrace();
                      }
                   }
              }
         }
     } 
     catch (IOException ex) {
     }
     finally {
           synchronized(ht) {
                 System.out.println("Remove connection: " + socket);		
                 ht.remove(socket);		
                 try {
                      socket.close();
                 } 
                 catch (IOException ex) {
                 }
            }
      }
  }
}