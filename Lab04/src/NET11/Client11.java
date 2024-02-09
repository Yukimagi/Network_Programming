package NET11;
import java.io.*;
import java.net.*;

public class Client11 {
  Socket socket;
  DataOutputStream  outstream;
  DataInputStream  instream;
  String messagein;
  static String messageout;
  static String servername;
  static int port;
  
  public Client11() {
     try{
         socket=new Socket(InetAddress.getByName(servername),port);

         outstream = new DataOutputStream(socket.getOutputStream());
         outstream.writeUTF(messageout);
	
         instream=new DataInputStream(socket.getInputStream());
         messagein = instream.readUTF();
         System.out.println("message: " + messagein);

         socket.close(); 
      }

      catch(IOException e){
         System.out.println("IOException when connecting Server!"); 
      }
  }

  public static void main(String args[]) {
      if (args.length < 3){
         System.out.println("USAGE: java Client11 [servername] [port] [messageout]");
         System.exit(1);
      }

      servername= args[0];
      port=Integer.parseInt(args[1]);
      messageout = args[2];
      Client11 ClientStart=new Client11();
  }
}
