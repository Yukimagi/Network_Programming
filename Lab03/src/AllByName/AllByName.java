package AllByName;  // 宣告 AllByName 套件

import java.net.*;  // 匯入 java.net 套件，提供網路相關的功能(集合套件)

public class AllByName {  // 定義 AllByName 類別

  public static void main(String[] args) { 

    try {//try查看以下操作是否有例外
      for (String name : args) {  // for迴圈，逐一處理傳入的參數
        // ppt p.9 (我依照ppt並使用run configurations)
        // Try the following websites www.csie.nuk.edu.tw & www.youtube.com
        // getAllByName(): lookup all the addresses of a host
        InetAddress[] addresses = InetAddress.getAllByName(name);  // 取得指定主機名稱的所有位址
        for (InetAddress addr : addresses) {  // for迴圈
          System.out.println(addr);//列印所有位址
        }
      }
    } catch (Exception ex) {  // 處理例外
      System.err.println(ex.getMessage());  // 輸出錯誤訊息
    }
  }
}
