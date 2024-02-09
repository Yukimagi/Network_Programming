package NET10;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server10 {
  ServerSocket  SS; 
  Socket  socket;
  static int  port;
  Date currentDate;

  public Server10() {
     try{
         SS = new ServerSocket(port);

         System.out.println("Server created.");
         System.out.println("waiting for client to connect...");
			
         while(true){//多while迴圈
              socket = SS.accept();
              currentDate = new Date();

              System.out.println("connected from Client " +
                                  socket.getInetAddress().getHostAddress());
              System.out.println("date:" + currentDate);//顯示目前時間
              System.out.println("waiting...");
          }
      }
      catch(IOException e){
           System.out.println(e.toString());
           e.printStackTrace();
           System.exit(1);
      }	
  }

  public static void main(String args[]){
         if(args.length < 1){
            System.out.println("Usage: java Server10 [port]");
            System.exit(1);
         }
         port=Integer.parseInt(args[0]);
         Server10 ServerStart=new Server10();
  }
}
