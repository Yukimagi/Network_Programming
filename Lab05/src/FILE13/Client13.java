package FILE13;
import java.io.*;//套件
import java.net.*;//套件

public class Client13 {
  int i;//變數
  Socket socket;// 客戶端套接字，用於連接伺服器
  static String servername;// 伺服器名稱或IP地址
  static int port;// 伺服器端口號
  DataOutputStream  outstream;// 用於將數據寫入伺服器的輸出流
  DataInputStream  instream;// 用於從伺服器讀取數據的輸入流
  
  public Client13() {
     try{//例外處理
         socket=new Socket(InetAddress.getByName(servername),port);// 連接到指定的伺服器和端口
         outstream = new DataOutputStream(socket.getOutputStream());// 創建用於寫入伺服器的輸出流
         //***Client端以本機當地資料串流讀取鍵盤之輸入資料
         InputStreamReader isr = new InputStreamReader(System.in);
         //***其中isr為新產生之本機輸入資料串流物件，
         //***System.in為鍵盤檔案。鍵盤輸入為人工手動操作，
         //***其速度自然不可與電子操作相提並論，
         //***為了節省電腦操作時間，
         //***須將鍵盤打字的資料先送儲於緩衝器內，
         //***等待一定資料量之後，再作輸入：
         BufferedReader br = new BufferedReader(isr);
         System.out.println("Input data from keyboard...");//請使用者輸入字

         while(true) {//while迴圈
             i = br.read();// 從鍵盤讀取字符
             outstream.writeInt(i);// 將字符寫入伺服器
         }
      }

      catch(IOException e){//例外處理抓取並印出
         System.out.println("IOException when connecting Server!");
      }
  }

  public static void main(String args[]) {
      //if (args.length < 2){
      //   System.out.println("USAGE: java Client13 [servername] [port]");
      //   System.exit(1);
      //}

      //servername= args[0];
      servername= "localhost";// 伺服器名稱或IP地址
      port = 234; // 伺服器端口號
      //port=Integer.parseInt(args[1]);
      Client13 ClientStart=new Client13(); // 創建客戶端對象並連接到伺服器
  }
}
