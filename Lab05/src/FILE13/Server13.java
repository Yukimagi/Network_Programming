package FILE13;
import java.net.*;//套件
import java.io.*;//套件
import java.util.*;//套件
//將client鍵盤輸入的資料，傳遞至server
public class Server13 {
  ServerSocket  SS; // 創建伺服器套接字，用於監聽連接
  Socket  socket;// 伺服器接受的客戶端套接字
  static int port;// port變數
  static String outfilename;//***用於本次作業的檔案輸出
  DataOutputStream   outstream;// 用於將數據寫入客戶端的輸出流
  DataInputStream    instream;// 用於從客戶端讀取數據的輸入流
  int messagein;// 用於存儲從客戶端接收的整數數據

  public Server13() {
     try{//例外處理
         SS = new ServerSocket(port);// 創建伺服器套接字並綁定到指定端口
         System.out.println("Server created.");//回應
         System.out.println("waiting for client to connect...");//回應

         socket = SS.accept();// 等待並接受客戶端連接
         System.out.println("connected from Client " +
                             socket.getInetAddress().getHostAddress());//印出連接結果
         //***Server完成網站建置後，等待捕捉client從網路送來的資料串流，
         //***server於讀取資料後於視窗印出：
         instream = new DataInputStream(socket.getInputStream());
         //***由while迴圈由輸入串流讀取資料，再於視窗印出並檔案輸出
         
         //***建立檔案輸出串流物件fos(用於本次作業的檔案輸出)
         FileOutputStream fos = new FileOutputStream(outfilename);
         
         while(true) {//while迴圈
            messagein = instream.readInt();// 從客戶端讀取整數數據
            fos.write(messagein);// 寫入文件
            System.out.print((char)messagein);//// 在控制台顯示接收到的字符
         }
      }
      catch(IOException e){//catch例外處理
           System.out.println(e.toString());
           e.printStackTrace();
           System.exit(1);
      }		
  }

  public static void main(String args[]){
         //if(args.length < 1){
         //   System.out.println("Usage: java Server13 [port]");
         //   System.exit(1);
         //}
         //port=Integer.parseInt(args[0]);
	     port = 234;// 伺服器端口號
	     outfilename="A1105505_FILE13.doc";// ***用於本次作業的文件名
         Server13 ServerStart=new Server13();// 創建伺服器對象並啟動伺服器
  }
}
