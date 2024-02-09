package NET09;
import java.io.*;
import java.net.*;

public class Client09 { 
  Socket socket;
  static String servername;
  static int port;
  
  public Client09() {
     try{			
         socket=new Socket(InetAddress.getByName(servername),port);
         socket.close();
      }
      catch(IOException e){
         System.out.println("IOException when connecting Server!"); 
      }
  }

  public static void main(String args[]) {
      if (args.length < 2){
         System.out.println("USAGE: java Client09 [servername] [port]");
         System.exit(1);
      }
      servername= args[0];
      port=Integer.parseInt(args[1]);
      Client09 ClientStart=new Client09();
  }
}
