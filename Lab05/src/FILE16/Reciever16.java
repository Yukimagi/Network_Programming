package FILE16;
import java.io.*;
import java.net.*;

public class Reciever16 {
  int messagein;
  Socket socket;
  static String servername;
  static int port;
  DataInputStream  instream;
  
  public Reciever16() {
     try{
         socket=new Socket(InetAddress.getByName(servername),port);
         instream = new DataInputStream(socket.getInputStream());
 
         while(true) {
             messagein = instream.readInt();
             System.out.print((char)messagein);
         }
      }

      catch(IOException e){
         System.out.println("IOException when connecting Server!");
      }
  }

  public static void main(String args[]) {
      //if (args.length < 2){
      //   System.out.println("USAGE: java Reciever16 [servername] [port]");
      //   System.exit(1);
      //}

      //servername= args[0];
      //port=Integer.parseInt(args[1]);
	  servername= "localhost";
	  port = 222;
      Reciever16 ClientStart=new Reciever16();
  }
}
