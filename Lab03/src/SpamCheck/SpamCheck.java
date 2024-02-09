package SpamCheck;  // 宣告 SpamCheck 套件

import java.net.*;  // 匯入 java.net 套件，提供網路相關的功能(集合套件)

public class SpamCheck {  // 定義 SpamCheck 類別
  // ppt p.50~51 (我依照ppt並使用run configurations)
  // Try the following websites www.nuk.edu.tw & www.google.com

  public static final String BLACKHOLE = "sbl.spamhaus.org";  // 定義黑洞名稱

  public static void main(String[] args) throws UnknownHostException {  // 如有錯誤，拋出 UnknownHostException 例外
    for (String arg: args) {  // 迴圈，逐一處理傳入的參數
      if (isSpammer(arg)) {  // 呼叫 isSpammer 方法判斷是否為垃圾郵件發送者
        System.out.println(arg + " is a known spammer.");  // 輸出為垃圾郵件發送者
      } else {											   //否則
        System.out.println(arg + " appears legitimate.");  // 輸出看似合法
      }
    }
  }

  private static boolean isSpammer(String arg) {  // 判斷是否為垃圾郵件發送者的私有方法
    try {//try catch 方法
      InetAddress address = InetAddress.getByName(arg);  // 取得傳入參數的 InetAddress
      byte[] quad = address.getAddress();  // 取得 IP 位址的 byte 陣列
      									   //Returns the raw IP address of thisInetAddressobject
      
      String query = BLACKHOLE;  // 初始化查詢字串為黑洞名稱
      for (byte octet : quad) {  // 迴圈處理每個 IP 位址的 byte
        int unsignedByte = octet < 0 ? octet + 256 : octet;  // 將 byte 轉換為無符號整數(0~255，因此加256)
        query = unsignedByte + "." + query;  // 構建 DNS 查詢字串
      }
      InetAddress.getByName(query);  // 進行 DNS 查詢
      return true;  // 查詢成功，判斷為垃圾郵件發送者
    } catch (UnknownHostException e) {  // 處理 UnknownHostException 例外
      return false;  // 查詢失敗，判斷為非垃圾郵件發送者
    }
  }
}

/*Read IPv4 address list from the command line 
 * Send DNS query d.c.b.a.sbl.spamhaus.org 
 * for each IPv4 address of a.b.c.d 
 * The query succeeds if it is a spammer*/
