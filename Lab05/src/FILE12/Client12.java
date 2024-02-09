package FILE12;
import java.io.*;
import java.net.*;

public class Client12 {
  int i;
  Socket socket;
  static String servername;
  static int port;
  static String infilename;
  DataOutputStream  outstream;
  DataInputStream  instream;
  
  public Client12() {
     try{
    	 //以server之IP與port建置網路串流連接平台
         socket=new Socket(InetAddress.getByName(servername),port);
         //建立網路輸出串流物件outstream
         outstream = new DataOutputStream(socket.getOutputStream());
         //建立檔案輸入串流物件fis
         FileInputStream fis = new FileInputStream(infilename);
        //設計執行while迴圈，由檔案輸入串流物件fis從檔案讀取一個字元資料，
         //再以網路輸出串流物件outstream，
         //將字元資料寫入網路輸出串流傳送至server端。
         while((i=fis.read()) !=-1)
              outstream.writeInt(i);
         outstream.writeInt(i);

         System.out.println("Data sent to internet successfully!");
         socket.close(); 
      }

      catch(IOException e){
         System.out.println("IOException when connecting Server!"); 
      }
  }

  public static void main(String args[]) {
      //if (args.length < 3){
      //   System.out.println("USAGE: java Client12 [servername] [port] [infilename]");	
      //   System.exit(1);
      // }

      //servername= args[0];
      //port=Integer.parseInt(args[1]);
      //infilename = args[2];
      servername= "localhost";
      port=123;
      infilename = "testinfile.doc";
      Client12 ClientStart=new Client12();
  }
}
