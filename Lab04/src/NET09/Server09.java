package NET09;
import java.net.*;
import java.io.*;

public class Server09 {
  ServerSocket  SS; //建server
  Socket  socket;//建socket
  static int  port;//設port

  public Server09() {
     try{
         SS = new ServerSocket(port);//建新server
         System.out.println("Server created.");
         System.out.println("waiting for client to connect...");
			
         socket = SS.accept();//等待client連線(成功後執行下面)
         System.out.println("connected from Client " +
                             socket.getInetAddress().getHostAddress());
         socket.close();
      }
      catch(IOException e){
           System.out.println(e.toString());
           e.printStackTrace();
           System.exit(1);
      }		
  }

  public static void main(String args[]){
         if(args.length < 1){
            System.out.println("Usage: java Server09 [port]");
            System.exit(1);
         }
         port=Integer.parseInt(args[0]);
         Server09 ServerStart=new Server09();
  }
}
