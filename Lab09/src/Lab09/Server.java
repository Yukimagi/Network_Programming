package Lab09;
//UDP Server 程式
// 引入必要的 Java 套件
import java.io.*;
import java.net.*;

// 定義 Server 類別
public class Server {
    // 主程式進入點
    public static void main(String args[]) throws Exception {
        // 宣告一個長度為 10 的 byte 陣列用來存放資料
        byte buffer[] = new byte[10];
        String msg; // 宣告一個字串變數用來存放訊息
        int portNo = 5555; // 設定伺服器端的通訊埠號
        System.out.println("Server端開始接受連線請求!");//請求印出

        // 進入無窮迴圈，等待連線請求
        for (;;) {
            // 建立 DatagramPacket 物件用來接收資料
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            // 建立 DatagramSocket 物件並綁定指定的通訊埠號
            DatagramSocket socket = new DatagramSocket(portNo);
            
            // 接收資料，此行程式碼會阻斷在這邊等待直到有資料進來
            socket.receive(packet);
            
            // 將接收到的資料轉換成字串
            msg = new String(buffer, 0, packet.getLength());
            
            // 顯示收到的訊息
            System.out.println("收到下面的訊息 : " + msg);
            
            // 關閉 DatagramSocket
            socket.close();
        }
    }
}