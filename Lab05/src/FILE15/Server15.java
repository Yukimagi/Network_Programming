package FILE15;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server15 {
  ServerSocket  SS; 
  Socket  socket;
  static int port;
  DataOutputStream   outstream;
  DataInputStream    instream;
  int messagein, flag, i;

  public Server15() {
     try{
         SS = new ServerSocket(port);
         System.out.println("Server created.");
         System.out.println("waiting for client to connect...");

         socket = SS.accept();
         System.out.println("connected from Client " +
                             socket.getInetAddress().getHostAddress());

         instream = new DataInputStream(socket.getInputStream());
         outstream = new DataOutputStream(socket.getOutputStream());
 
         InputStreamReader isr = new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader(isr);
         System.out.println("Input data from keyboard...");

         while(true) {
            messagein = instream.readInt();
            System.out.print((char)messagein);
            if (messagein == 64) {
               flag = 1;
               System.out.println(" ");
            }

            while(flag == 1) {
               i = br.read();
               outstream.writeInt(i);
               if (i == 64) {
                  flag = 0;
               }
            }
         }
      }
      catch(IOException e){
           System.out.println(e.toString());
           e.printStackTrace();
           System.exit(1);
      }		
  }

  public static void main(String args[]){
         //if(args.length < 1){
         //   System.out.println("Usage: java Server15 [port]");
         //   System.exit(1);
         //}
         //port=Integer.parseInt(args[0]);
	     port = 222;
         Server15 ServerStart=new Server15();
  }
}
