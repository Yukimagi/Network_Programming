package NET10;
import java.io.*;
import java.net.*;

public class Client10 {
  Socket socket;//socket
  static String servername;//server的名稱
  static int port;//server的port
  
  public Client10() {
     try{
         socket=new Socket(InetAddress.getByName(servername),port);//socket連線(ip與port串連)
         socket.close();
      }
      catch(IOException e){
         System.out.println("IOException when connecting Server!");
      } 
  }

  public static void main(String args[]) {
      if (args.length < 2){
         System.out.println("USAGE: java Client10 [servername] [port]");
         System.exit(1);
      }
      servername= args[0];
      port=Integer.parseInt(args[1]);
      Client10 ClientStart=new Client10();
  }
}
