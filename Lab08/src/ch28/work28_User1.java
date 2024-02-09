package ch28; // 指定程式碼所在的套件名稱

import java.awt.*; // 匯入處理圖形介面的類別
import java.awt.event.*; // 匯入處理事件的類別
import java.math.*; // 匯入處理數學運算的類別

import java.io.*; // 匯入處理輸入輸出的類別
import java.net.*; // 匯入處理網路相關的類別
import java.util.*; // 匯入處理集合的類別

// 繼承 Frame 類別並實作 Runnable 介面
public class work28_User1 extends Frame implements Runnable {
	static int player = 0; // 玩家編號，初始值為0
    Socket socket; // 用於建立與伺服器的連接的 Socket 物件
    static String servername; // 伺服器名稱
    static int port; // 通訊埠
    static String user; // 使用者名稱
    DataOutputStream outstream; // 用於傳送資料的輸出流
    DataInputStream instream; // 用於接收資料的輸入流
    int pos1X, pos1Y, pos2X, pos2Y; // 玩家1和玩家2的座標
    int pos1X_send, pos1Y_send, pos2X_send, pos2Y_send, pos_rcv; // 發送和接收座標
    int[] area_flag = new int[9]; // 標記每個區域的狀態
    int i, w; // 迴圈計數器，w代表步數
    String message = ""; // 顯示的訊息

    Image img1, img2; // 用於顯示玩家1和玩家2的圖片
    Image bufferPage = null; // 雙緩衝技術

    public static void main(String args[]) {
        //if (args.length < 2){
        //   System.out.println("USAGE: java work25_User1 [servername] [port]");	
        //   System.exit(1);
        //}

        //servername= args[0];
        servername = "localhost"; // 伺服器名稱
        //port=Integer.parseInt(args[1]);
        port = 1235; // 通訊埠
        work28_User1 ClientStart = new work28_User1(); // 創建 work28_User1 物件
    }

    // work28_User1 的建構子
    public work28_User1() {
        super("work28_User1"); // 設定視窗標題
        setSize(250, 280); // 設定視窗大小

        img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Circle.gif")); // 載入玩家1的圖片
        img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Cross.gif")); // 載入玩家2的圖片
        enableEvents(AWTEvent.MOUSE_EVENT_MASK); // 啟用滑鼠事件

        setVisible(true); // 顯示視窗
        addWindowListener(new WindowAdapter() {//使視窗可以關閉
            public void windowClosing(WindowEvent e) {//關閉視窗
                System.exit(0);//離開系統
            }
        });

        for (i = 0; i < 9; i++)
            area_flag[i] = 0; // 初始化區域狀態

        try {
            socket = new Socket(InetAddress.getByName(servername), port); // 連接伺服器
            outstream = new DataOutputStream(socket.getOutputStream()); // 初始化資料輸出流
            instream = new DataInputStream(socket.getInputStream()); // 初始化資料輸入流

            this.outstream = new DataOutputStream(outstream);//賦值
            this.instream = new DataInputStream(instream);//賦值

            new Thread(this).start(); // 啟動執行緒處理伺服器訊息
        } catch (Exception e) {
            e.printStackTrace(); // 處理例外，顯示錯誤訊息
        }
    }


 // 處理滑鼠事件的方法
    public void processMouseEvent(MouseEvent e) {
        // 當滑鼠按下且前一個玩家是2或尚未指定玩家時執行
        if (e.getID() == MouseEvent.MOUSE_PRESSED && (player == 2 || player == 0)) {
            try {
                // 取得滑鼠點擊的座標
                pos1X = e.getX();//x
                pos1Y = e.getY();//y

                // 將座標轉換成整數並組成傳送給伺服器的訊息
                pos1X_send = pos1X * 100 + 0;//對於user1的x會是0(乘100)
                pos1Y_send = pos1Y * 100 + 1;//對於user1的y會是1(乘100)

                // 傳送座標訊息至伺服器
                outstream.writeInt(pos1X_send);//傳x
                outstream.writeInt(pos1Y_send);//傳y
            } catch (Exception f) {//例外處理
                f.printStackTrace();//例外處理
            }
        }
    }

    // 更新畫面的方法
    public void update(Graphics g) {
        paint(g);//用paint
    }

    // 繪製畫面的方法
    public void paint(Graphics g) {
        Graphics bufferg;//緩衝
        // 若雙緩衝的緩衝畫布為空，則建立一個新的緩衝畫布
        if (bufferPage == null)
            bufferPage = createImage(250, 250);//建立一個新的緩衝畫布
        bufferg = bufferPage.getGraphics();//建立一個新的緩衝畫布

        // 在緩衝畫布上繪製訊息和遊戲區域的線條
        bufferg.drawString(message, 20, 20);//訊息
        bufferg.drawLine(90, 50, 90, 230);//線條
        bufferg.drawLine(150, 50, 150, 230);//線條
        bufferg.drawLine(30, 110, 210, 110);//線條
        bufferg.drawLine(30, 170, 210, 170);//線條

        // 根據遊戲區域標記，繪製玩家1的圖片
        for (i = 0; i < 9; i++) {
            if (area_flag[0] == 1)
                bufferg.drawImage(img1, 42, 60, this);
            if (area_flag[1] == 1)
                bufferg.drawImage(img1, 102, 60, this);
            if (area_flag[2] == 1)
                bufferg.drawImage(img1, 164, 60, this);
            if (area_flag[3] == 1)
                bufferg.drawImage(img1, 42, 125, this);
            if (area_flag[4] == 1)
                bufferg.drawImage(img1, 102, 125, this);
            if (area_flag[5] == 1)
                bufferg.drawImage(img1, 164, 125, this);
            if (area_flag[6] == 1)
                bufferg.drawImage(img1, 42, 187, this);
            if (area_flag[7] == 1)
                bufferg.drawImage(img1, 102, 187, this);
            if (area_flag[8] == 1)
                bufferg.drawImage(img1, 164, 187, this);
        }

        // 根據遊戲區域標記，繪製玩家2的圖片
        for (i = 0; i < 9; i++) {
            if (area_flag[0] == 2)
                bufferg.drawImage(img2, 42, 60, this);
            if (area_flag[1] == 2)
                bufferg.drawImage(img2, 102, 60, this);
            if (area_flag[2] == 2)
                bufferg.drawImage(img2, 164, 60, this);
            if (area_flag[3] == 2)
                bufferg.drawImage(img2, 42, 125, this);
            if (area_flag[4] == 2)
                bufferg.drawImage(img2, 102, 125, this);
            if (area_flag[5] == 2)
                bufferg.drawImage(img2, 164, 125, this);
            if (area_flag[6] == 2)
                bufferg.drawImage(img2, 42, 187, this);
            if (area_flag[7] == 2)
                bufferg.drawImage(img2, 102, 187, this);
            if (area_flag[8] == 2)
                bufferg.drawImage(img2, 164, 187, this);
        }
        bufferg.dispose();
        g.drawImage(bufferPage, getInsets().left, getInsets().top, this);
    }


 // 執行緒的運行方法
    public void run() {
        while (true) { // 無窮迴圈，持續接收伺服器傳來的訊息

            try {
                // 讀取從伺服器傳來的訊息
                pos_rcv = instream.readInt();
                w = w + 1; // 計算步數

                // 根據從伺服器收到的訊息解析出座標和玩家
                if (pos_rcv % 100 == 0) {
                    pos1X = (pos_rcv - 0) / 100;
                    player = 1;
                } else if (pos_rcv % 100 == 1) {
                    pos1Y = (pos_rcv - 1) / 100;
                } else if (pos_rcv % 100 == 2) {
                    pos2X = (pos_rcv - 2) / 100;
                    player = 2;
                } else if (pos_rcv % 100 == 3) {
                    pos2Y = (pos_rcv - 3) / 100;
                }

                // 根據座標和玩家設定遊戲區域的標記
                if ((pos1X > 30) && (pos1X < 90) && (pos1Y > 70) && (pos1Y < 130) && (w % 2 == 0) && (area_flag[0] == 0)) {
                    area_flag[0] = 1;
                } else if ((pos1X > 90) && (pos1X < 150) && (pos1Y > 70) && (pos1Y < 130) && (w % 2 == 0) && (area_flag[1] == 0)) {
                    area_flag[1] = 1;
                } else if ((pos1X > 150) && (pos1X < 210) && (pos1Y > 70) && (pos1Y < 130) && (w % 2 == 0) && (area_flag[2] == 0)) {
                    area_flag[2] = 1;
                } else if ((pos1X > 30) && (pos1X < 90) && (pos1Y > 130) && (pos1Y < 190) && (w % 2 == 0) && (area_flag[3] == 0)) {
                    area_flag[3] = 1;
                } else if ((pos1X > 90) && (pos1X < 150) && (pos1Y > 130) && (pos1Y < 190) && (w % 2 == 0) && (area_flag[4] == 0)) {
                    area_flag[4] = 1;
                } else if ((pos1X > 150) && (pos1X < 210) && (pos1Y > 130) && (pos1Y < 190) && (w % 2 == 0) && (area_flag[5] == 0)) {
                    area_flag[5] = 1;
                } else if ((pos1X > 30) && (pos1X < 90) && (pos1Y > 190) && (pos1Y < 250) && (w % 2 == 0) && (area_flag[6] == 0)) {
                    area_flag[6] = 1;
                } else if ((pos1X > 90) && (pos1X < 150) && (pos1Y > 190) && (pos1Y < 250) && (w % 2 == 0) && (area_flag[7] == 0)) {
                    area_flag[7] = 1;
                } else if ((pos1X > 150) && (pos1X < 210) && (pos1Y > 190) && (pos1Y < 250) && (w % 2 == 0) && (area_flag[8] == 0)) {
                    area_flag[8] = 1;
                }

                // 根據座標和玩家設定遊戲區域的標記
                if ((pos2X > 30) && (pos2X < 90) && (pos2Y > 70) && (pos2Y < 130) && (w % 2 == 0) && (area_flag[0] == 0)) {
                    area_flag[0] = 2;
                } else if ((pos2X > 90) && (pos2X < 150) && (pos2Y > 70) && (pos2Y < 130) && (w % 2 == 0) && (area_flag[1] == 0)) {
                    area_flag[1] = 2;
                } else if ((pos2X > 150) && (pos2X < 210) && (pos2Y > 70) && (pos2Y < 130) && (w % 2 == 0) && (area_flag[2] == 0)) {
                    area_flag[2] = 2;
                } else if ((pos2X > 30) && (pos2X < 90) && (pos2Y > 130) && (pos2Y < 190) && (w % 2 == 0) && (area_flag[3] == 0)) {
                    area_flag[3] = 2;
                } else if ((pos2X > 90) && (pos2X < 150) && (pos2Y > 130) && (pos2Y < 190) && (w % 2 == 0) && (area_flag[4] == 0)) {
                    area_flag[4] = 2;
                } else if ((pos2X > 150) && (pos2X < 210) && (pos2Y > 130) && (pos2Y < 190) && (w % 2 == 0) && (area_flag[5] == 0)) {
                    area_flag[5] = 2;
                } else if ((pos2X > 30) && (pos2X < 90) && (pos2Y > 190) && (pos2Y < 250) && (w % 2 == 0) && (area_flag[6] == 0)) {
                    area_flag[6] = 2;
                } else if ((pos2X > 90) && (pos2X < 150) && (pos2Y > 190) && (pos2Y < 250) && (w % 2 == 0) && (area_flag[7] == 0)) {
                    area_flag[7] = 2;
                } else if ((pos2X > 150) && (pos2X < 210) && (pos2Y > 190) && (pos2Y < 250) && (w % 2 == 0) && (area_flag[8] == 0)) {
                    area_flag[8] = 2;
                }

                // 判斷是否有玩家獲勝，並設定相應的訊息
                if ((area_flag[0] == 1) && (area_flag[1] == 1) && (area_flag[2] == 1) && (message == ""))
                    message = "User1 win!!";
                else if ((area_flag[3] == 1) && (area_flag[4] == 1) && (area_flag[5] == 1) && (message == ""))
                    message = "User1 win!!";
                else if ((area_flag[6] == 1) && (area_flag[7] == 1) && (area_flag[8] == 1) && (message == ""))
                    message = "User1 win!!";
                else if ((area_flag[0] == 1) && (area_flag[3] == 1) && (area_flag[6] == 1) && (message == ""))
                    message = "User1 win!!";
                else if ((area_flag[1] == 1) && (area_flag[4] == 1) && (area_flag[7] == 1) && (message == ""))
                    message = "User1 win!!";
                else if ((area_flag[2] == 1) && (area_flag[5] == 1) && (area_flag[8] == 1) && (message == ""))
                    message = "User1 win!!";
                else if ((area_flag[0] == 1) && (area_flag[4] == 1) && (area_flag[8] == 1) && (message == ""))
                    message = "User1 win!!";
                else if ((area_flag[2] == 1) && (area_flag[4] == 1) && (area_flag[6] == 1) && (message == ""))
                    message = "User1 win!!";

                if ((area_flag[0] == 2) && (area_flag[1] == 2) && (area_flag[2] == 2) && (message == ""))
                    message = "User2 win!!";
                else if ((area_flag[3] == 2) && (area_flag[4] == 2) && (area_flag[5] == 2) && (message == ""))
                    message = "User2 win!!";
                else if ((area_flag[6] == 2) && (area_flag[7] == 2) && (area_flag[8] == 2) && (message == ""))
                    message = "User2 win!!";
                else if ((area_flag[0] == 2) && (area_flag[3] == 2) && (area_flag[6] == 2) && (message == ""))
                    message = "User2 win!!";
                else if ((area_flag[1] == 2) && (area_flag[4] == 2) && (area_flag[7] == 2) && (message == ""))
                    message = "User2 win!!";
                else if ((area_flag[2] == 2) && (area_flag[5] == 2) && (area_flag[8] == 2) && (message == ""))
                    message = "User2 win!!";
                else if ((area_flag[0] == 2) && (area_flag[4] == 2) && (area_flag[8] == 2) && (message == ""))
                    message = "User2 win!!";
                else if ((area_flag[2] == 2) && (area_flag[4] == 2) && (area_flag[6] == 2) && (message == ""))
                    message = "User2 win!!";

                // 重新繪製畫面
                repaint();

                // 同步處理
                // synchronized (this) {
                // notify(); // 通知等待中的執行緒可以繼續執行
                // wait(); // 讓目前執行緒進入等待狀態
                // }
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
    }

}





