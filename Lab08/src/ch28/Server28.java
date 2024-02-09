package ch28; // 套件名稱

import java.net.*; // 匯入處理網路相關的類別
import java.io.*; // 匯入處理輸入輸出的類別
import java.util.*; // 匯入處理集合的類別

public class Server28 {
  private static ServerSocket SSocket; // 伺服器 Socket 物件
  private static int port; // 伺服器使用的通訊埠
  private Hashtable ht = new Hashtable(); // 用來存放連線的 Socket 和資料輸出流

  // static int currentPlayer = 1; // 初始為玩家1（被註解掉的程式碼）
  //static int time = 0; // 是否為第一次
  Socket socket; // 代表伺服器和客戶端之間的通訊連接

  // 伺服器建構子，處理伺服器的初始化工作
  public Server28() throws IOException {
    try {
      SSocket = new ServerSocket(port); // 創建伺服器 Socket 並指定port
      System.out.println("Server created."); // 顯示建立成功
      System.out.println("waiting for client to connect..."); // 顯示等待客戶端連線

      while (true) {
        socket = SSocket.accept(); // 等待客戶端連線，並接受連線
        System.out.println("connected from Client " + socket.getInetAddress().getHostAddress()); // 顯示客戶端連線成功的訊息

        DataOutputStream outstream = new DataOutputStream(socket.getOutputStream()); // 創建DataOutputStream
        ht.put(socket, outstream); // 將 socket 和對應的資料輸出流存放到 Hashtable 中
        Thread thread = new Thread(new ServerThread(socket, ht)); // 創建新的執行緒處理客戶端的請求
        thread.start(); // 啟動執行緒
      }
    } catch (IOException ex) {
      ex.printStackTrace(); // 處理 IOException 例外，顯示錯誤訊息
    }
  }

  // 主函式
  public static void main(String[] args) throws Exception {
    // if (args.length < 1) {
    // System.out.println("Usage: java Server25 [port]");
    // System.exit(1);
    // }

    // port=Integer.parseInt(args[0]) ;
    port = 1235; // 指定伺服器使用的port
    Server28 ServerStart = new Server28(); // 創建伺服器物件
  }
}

// 伺服器執行緒，處理與客戶端的通訊
class ServerThread extends Thread implements Runnable {
  private Socket socket; // 代表伺服器和客戶端之間的通訊連接
  private Hashtable ht; // 用來存放連線的 Socket 和資料輸出流

  // 伺服器執行緒建構子
  public ServerThread(Socket socket, Hashtable ht) {
    this.socket = socket; // 初始化 socket
    this.ht = ht; // 初始化 ht
  }

  // 執行緒執行的方法
  public void run() {
    DataInputStream instream;

    try {
      instream = new DataInputStream(socket.getInputStream()); // 創建資料輸入流

      while (true) {//當正確
        int message = instream.readInt(); // 讀取從客戶端傳來的訊息
        System.out.println("Message: " + message); // 顯示接收到的訊息

        // if(Server28.time==0) {
        // if((message%100 == 2||message%100 == 3)) {
        // Server28.currentPlayer=2;
        // }
        // Server28.time=1;
        // }

        synchronized (ht) {//thread
          // 判斷是否該執行
          // System.out.println(message%100+"");
          // System.out.println(Server28.currentPlayer+"");
          // if (((message%100 == 1||message%100 == 0) && Server28.currentPlayer == 1)||((message%100
          // == 2||message%100 == 3) && Server28.currentPlayer == 2)) {
          for (Enumeration e = ht.elements(); e.hasMoreElements();) {//集合進行
            DataOutputStream outstream = (DataOutputStream) e.nextElement(); // 取得資料輸出流

            try {
              outstream.writeInt(message); // 將訊息寫入資料輸出流
              System.out.println(message); // 顯示寫入的訊息
            } catch (IOException ex) {
              ex.printStackTrace(); // 處理 IOException 例外，顯示錯誤訊息
            }
          }

          // if(message%100 == 1||message%100 == 3) {
          // 切換當前玩家
          // Server28.currentPlayer = (Server28.currentPlayer == 1) ? 2 : 1;
          // System.out.println("Change: "+Server28.currentPlayer+"");
          // }
          // }
        }
      }
    } catch (IOException ex) {//例外處理
    } finally {
      synchronized (ht) {
        System.out.println("Remove connection: " + socket); // 顯示移除連線的訊息

        ht.remove(socket); // 從 Hashtable 中移除連線

        try {
          socket.close(); // 關閉連線的 Socket
        } catch (IOException ex) {//例外處理
        }
      }
    }
  }
}
