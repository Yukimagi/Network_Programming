package NET11;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server11 {
  ServerSocket  SS; 
  DataOutputStream   outstream;//**網路串流物件，以server即可執行網路資料輸入
  DataInputStream    instream;//**網路串流物件，以server即可執行網路資料輸入
  Socket  socket;
  static int  port;
  static String messageout;
  static String messagein;
  Date currentDate;

  public Server11() {
     try{
         SS = new ServerSocket(port);
         currentDate = new Date();

         System.out.println("Server created.");
         System.out.println("waiting for client to connect...");
			
         while(true){
              socket = SS.accept();

              System.out.println("connected from Client " +
                                  socket.getInetAddress().getHostAddress());
              System.out.println("ClientName:" +
                                  socket.getInetAddress().getHostName());
              System.out.println("date:" + currentDate);

              instream = new DataInputStream(socket.getInputStream());//***instream是新產生之網路輸入串流物件，以socket為網路連接平台
              messagein = instream.readUTF();						//***將輸入的資料讀取至變數messagein
              System.out.println("message: " + messagein);

              System.out.println("waiting...");

              outstream = new DataOutputStream(socket.getOutputStream());//***outstream是新產生之網路輸出串流物件，以socket為網路連接平台
              outstream.writeUTF(messageout);//***將變數messagein內的資料寫至網路輸出串流並傳送至網路
              outstream.close();
          }
      }
      catch(IOException e){
           System.out.println(e.toString());
           e.printStackTrace();
           System.exit(1);
      }		
  }

  public static void main(String args[]){
         if(args.length < 2){
            System.out.println("Usage: java Server11 [port] [messageout]");
            System.exit(1);
         }
         port=Integer.parseInt(args[0]);
         messageout = args[1];
         Server11 ServerStart=new Server11();
  }
}
