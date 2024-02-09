package ch28; // 指定程式碼所在的套件名稱

import java.awt.*; // 匯入處理圖形介面的類別
import java.awt.event.*; // 匯入處理事件的類別
import java.math.*; // 匯入處理數學運算的類別

import java.io.*; // 匯入處理輸入輸出的類別
import java.net.*; // 匯入處理網路相關的類別
import java.util.*; // 匯入處理集合的類別

public class work28_User2 extends Frame implements Runnable {
	static int player=0;
    Socket socket;
    static String servername;
    static int port;
    static String user;
    DataOutputStream  outstream;
    DataInputStream  instream;
    int pos1X, pos1Y, pos2X, pos2Y;
    int pos1X_send, pos1Y_send, pos2X_send, pos2Y_send, pos_rcv;
    int[] area_flag = new int[9];
    int i, w;
    String message="";

    Image img1, img2;
    Image bufferPage=null;

    public static void main(String args[]) {
    	//if (args.length < 2){
        //   System.out.println("USAGE: java work25_User1 [servername] [port]");	
        //   System.exit(1);
        //}

        //servername= args[0];
        servername= "localhost";	
        //port=Integer.parseInt(args[1]);
        port = 1235;
      work28_User2 ClientStart=new work28_User2();
    }

    
    public work28_User2() {
        super("work28_User2");
          setSize(250, 280);

        img1 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("Circle.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage
            (getClass().getResource("Cross.gif"));

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        setVisible(true);

        for (i=0; i<9; i++)
           area_flag[i] = 0;

     try{
         socket=new Socket(InetAddress.getByName(servername),port);
         outstream = new DataOutputStream(socket.getOutputStream());
         instream = new DataInputStream(socket.getInputStream()); 

         this.outstream = new DataOutputStream(outstream);
         this.instream = new DataInputStream(instream);

         new Thread(this).start();
      }
      catch (Exception e) {
            e.printStackTrace();
      }
    }

    public void processMouseEvent(MouseEvent e) {
    	// 當滑鼠按下且前一個玩家是1或尚未指定玩家時執行
        if(e.getID() == MouseEvent.MOUSE_PRESSED && (player==1||player==0)) {
          try{
        	// 取得滑鼠點擊的座標
            pos2X = e.getX();//x
            pos2Y = e.getY();//y
         // 將座標轉換成整數並組成傳送給伺服器的訊息
            pos2X_send = pos2X * 100 + 2;//對於user2的x會是2(乘100)
            pos2Y_send = pos2Y * 100 + 3;//對於user2的x會是3(乘100)
         // 傳送座標訊息至伺服器
            outstream.writeInt(pos2X_send);//傳x
            outstream.writeInt(pos2Y_send);//傳y

          }
          catch (Exception f) {//例外處理
            f.printStackTrace();//例外處理
          }
        }
    }

    public void update(Graphics g) {
         paint(g);
    }

    public void paint(Graphics g) {
        Graphics bufferg;
        if(bufferPage == null)
           bufferPage = createImage(250, 250);
        bufferg = bufferPage.getGraphics();

        bufferg.drawString(message, 20, 20);
        bufferg.drawLine(90,50,90,230);
        bufferg.drawLine(150,50,150,230);
        bufferg.drawLine(30,110,210,110);
        bufferg.drawLine(30,170,210,170);

        for (i=0; i<9; i++) {
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

        for (i=0; i<9; i++) {
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

    public void run() {
      while(true) {

        try {
             pos_rcv = instream.readInt();
             w = w+1;

             if (pos_rcv % 100 == 0) {
                 pos1X = (pos_rcv - 0) / 100;
              	player=1;
              }
              else if (pos_rcv % 100 == 1) { 
                 pos1Y = (pos_rcv - 1) / 100;
              }
              else if (pos_rcv % 100 == 2) { 
                 pos2X = (pos_rcv - 2) / 100;
                 player=2;
              }
              else if (pos_rcv % 100 == 3) { 
                 pos2Y = (pos_rcv - 3) / 100;
              }

            if((pos1X>30)&&(pos1X<90)&&(pos1Y>70)&&(pos1Y<130)&&(w%2==0)&&(area_flag[0]==0)) 
              {area_flag[0] = 1;}
            else if ((pos1X>90)&&(pos1X<150)&&(pos1Y>70)&&(pos1Y<130)&&(w%2==0)&&(area_flag[1]==0)) 
                    {area_flag[1] = 1;}
            else if ((pos1X>150)&&(pos1X<210)&&(pos1Y>70)&&(pos1Y<130)&&(w%2==0)&&(area_flag[2]==0)) 
                    {area_flag[2] = 1;}
            else if((pos1X>30)&&(pos1X<90)&&(pos1Y>130)&&(pos1Y<190)&&(w%2==0)&&(area_flag[3]==0)) 
                    {area_flag[3] = 1;}
            else if((pos1X>90)&&(pos1X<150)&&(pos1Y>130)&&(pos1Y<190)&&(w%2==0)&&(area_flag[4]==0)) 
                    {area_flag[4] = 1;}
            else if((pos1X>150)&&(pos1X<210)&&(pos1Y>130)&&(pos1Y<190)&&(w%2==0)&&(area_flag[5]==0)) 
                    {area_flag[5] = 1;}
            else if((pos1X>30)&&(pos1X<90)&&(pos1Y>190)&&(pos1Y<250)&&(w%2==0)&&(area_flag[6]==0)) 
                    {area_flag[6] = 1;}
            else if((pos1X>90)&&(pos1X<150)&&(pos1Y>190)&&(pos1Y<250)&&(w%2==0)&&(area_flag[7]==0)) 
                    {area_flag[7] = 1;}
            else if((pos1X>150)&&(pos1X<210)&&(pos1Y>190)&&(pos1Y<250)&&(w%2==0)&&(area_flag[8]==0)) 
                    {area_flag[8] = 1;}

            if((pos2X>30)&&(pos2X<90)&&(pos2Y>70)&&(pos2Y<130)&&(w%2==0)&&(area_flag[0]==0)) 
              {area_flag[0] = 2;}
            else if ((pos2X>90)&&(pos2X<150)&&(pos2Y>70)&&(pos2Y<130)&&(w%2==0)&&(area_flag[1]==0)) 
                    {area_flag[1] = 2;}
            else if ((pos2X>150)&&(pos2X<210)&&(pos2Y>70)&&(pos2Y<130)&&(w%2==0)&&(area_flag[2]==0)) 
                    {area_flag[2] = 2;}
            else if((pos2X>30)&&(pos2X<90)&&(pos2Y>130)&&(pos2Y<190)&&(w%2==0)&&(area_flag[3]==0)) 
                    {area_flag[3] = 2;}
            else if((pos2X>90)&&(pos2X<150)&&(pos2Y>130)&&(pos2Y<190)&&(w%2==0)&&(area_flag[4]==0)) 
                    {area_flag[4] = 2;}
            else if((pos2X>150)&&(pos2X<210)&&(pos2Y>130)&&(pos2Y<190)&&(w%2==0)&&(area_flag[5]==0)) 
                    {area_flag[5] = 2;}
            else if((pos2X>30)&&(pos2X<90)&&(pos2Y>190)&&(pos2Y<250)&&(w%2==0)&&(area_flag[6]==0)) 
                    {area_flag[6] = 2;}
            else if((pos2X>90)&&(pos2X<150)&&(pos2Y>190)&&(pos2Y<250)&&(w%2==0)&&(area_flag[7]==0)) 
                    {area_flag[7] = 2;}
            else if((pos2X>150)&&(pos2X<210)&&(pos2Y>190)&&(pos2Y<250)&&(w%2==0)&&(area_flag[8]==0)) 
                    {area_flag[8] = 2;}

            if ((area_flag[0]==1)&&(area_flag[1]==1)&&(area_flag[2]==1)&&(message==""))
               message = "User1 win!!";
            else if ((area_flag[3]==1)&&(area_flag[4]==1)&&(area_flag[5]==1)&&(message==""))
               message = "User1 win!!";
            else if ((area_flag[6]==1)&&(area_flag[7]==1)&&(area_flag[8]==1)&&(message==""))
               message = "User1 win!!";
            else if ((area_flag[0]==1)&&(area_flag[3]==1)&&(area_flag[6]==1)&&(message==""))
               message = "User1 win!!";
            else if ((area_flag[1]==1)&&(area_flag[4]==1)&&(area_flag[7]==1)&&(message==""))
               message = "User1 win!!";
            else if ((area_flag[2]==1)&&(area_flag[5]==1)&&(area_flag[8]==1)&&(message==""))
               message = "User1 win!!";
            else if ((area_flag[0]==1)&&(area_flag[4]==1)&&(area_flag[8]==1)&&(message==""))
               message = "User1 win!!";
            else if ((area_flag[2]==1)&&(area_flag[4]==1)&&(area_flag[6]==1)&&(message==""))
               message = "User1 win!!";

            if ((area_flag[0]==2)&&(area_flag[1]==2)&&(area_flag[2]==2)&&(message==""))
               message = "User2 win!!";
            else if ((area_flag[3]==2)&&(area_flag[4]==2)&&(area_flag[5]==2)&&(message==""))
               message = "User2 win!!";
            else if ((area_flag[6]==2)&&(area_flag[7]==2)&&(area_flag[8]==2)&&(message==""))
               message = "User2 win!!";
            else if ((area_flag[0]==2)&&(area_flag[3]==2)&&(area_flag[6]==2)&&(message==""))
               message = "User2 win!!";
            else if ((area_flag[1]==2)&&(area_flag[4]==2)&&(area_flag[7]==2)&&(message==""))
               message = "User2 win!!";
            else if ((area_flag[2]==2)&&(area_flag[5]==2)&&(area_flag[8]==2)&&(message==""))
               message = "User2 win!!";
            else if ((area_flag[0]==2)&&(area_flag[4]==2)&&(area_flag[8]==2)&&(message==""))
               message = "User2 win!!";
            else if ((area_flag[2]==2)&&(area_flag[4]==2)&&(area_flag[6]==2)&&(message==""))
               message = "User2 win!!";       

             repaint();

        } 
        catch (Exception f) {
            f.printStackTrace();
        }
      }
    }
}





