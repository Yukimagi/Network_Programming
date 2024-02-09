package FILE12;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server12 {
  ServerSocket  SS; 
  Socket  socket;
  static int port;
  static String outfilename;
  DataOutputStream   outstream;
  DataInputStream    instream;
  int messagein;

  public Server12() {
     try{
         SS = new ServerSocket(port);//port建置伺服器平台
         System.out.println("Server created.");
         System.out.println("waiting for client to connect...");
         //以伺服器平台建立網路串流接收平台
         //捉取client之網址IP
         socket = SS.accept();
         System.out.println("connected from Client " + socket.getInetAddress().getHostAddress());
         //建立網路輸入串流物件instream
         instream = new DataInputStream(socket.getInputStream());
         //建立檔案輸出串流物件fos
         FileOutputStream fos = new FileOutputStream(outfilename);
         //設計while迴圈，由網路輸入串流物件instream，
         //讀取由網路輸入之字元資料，再以檔案輸出串流物件fos，
         //將字元資料寫入設定之輸出檔案內。
         while(messagein != -1){
              messagein = instream.readInt();     
              fos.write(messagein);
          }
         System.out.println("Data from internet sent to file successfully!");
      }
      catch(IOException e){
           System.out.println(e.toString());
           e.printStackTrace();
           System.exit(1);
      }		
  }

  public static void main(String args[]){
         //if(args.length < 2){
         //   System.out.println("Usage: java Server12 [port] [outfilename]");
         //   System.exit(1);
         //}
         //port=Integer.parseInt(args[0]);
	     port = 123;
	     outfilename="outfile.doc";
         //outfilename = args[1];
         Server12 ServerStart=new Server12();
  }
}
