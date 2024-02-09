package Lab09;
//UDP 客戶端的程式
// 引入必要的 Java 套件
import java.io.*;
import java.net.*;

// 定義 Client 類別
public class Client {
    // 主程式進入點
    public static void main(String args[]) throws Exception {
        int portNo = 5555; // 設定客戶端的通訊埠號
        System.out.print("Please input the IP address of destination :");//印出輸入需要
        
        // 用來讀取使用者輸入的 BufferedReader 物件
        BufferedReader uip = new BufferedReader(new InputStreamReader(System.in)); 
        
        // 讀取使用者輸入的目標伺服器的 IP 位址
        String serverIP = uip.readLine();
        
        // 將目標伺服器的 IP 位址轉換成 InetAddress 物件
        InetAddress addr = InetAddress.getByName(serverIP);
        
        // 進入無窮迴圈，等待使用者輸入訊息並傳送
        while (true) {
            System.out.print("輸入送出的訊息 :");
            
            // 讀取使用者輸入的訊息
            String msg = uip.readLine();
            
            // 取得訊息的長度
            int originalLength = msg.length();   
            
            // 宣告一個 byte 陣列，用來存放轉換成 byte 的訊息
            byte buffer[] = new byte[originalLength];     
            buffer = msg.getBytes();
            
            // 建立 DatagramPacket 物件，用來包裝要發送的資料
            DatagramPacket packet = new DatagramPacket(buffer, originalLength, addr, portNo); 
            
            // 建立 DatagramSocket 物件
            DatagramSocket socket = new DatagramSocket();  
            
            // 發送資料
            socket.send(packet);          
            
            // 關閉 DatagramSocket
            socket.close();               
        }
    }
}
